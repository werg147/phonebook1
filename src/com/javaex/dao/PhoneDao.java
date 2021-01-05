package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {

	// 필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자 --디폴트 생략
	// 생성자gs

	// 일반메소드

	// DB접속
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 등록
	public int personInsert(PersonVo personVo) {

		getConnection();

		int count = 0;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into phonedb ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// 수정
	public int personUpdate(PersonVo personVo) {
		
		getConnection();
		
		int count = 0;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; 
			query += " update phonedb ";
			query += " set name = ? , ";
			query += "     hp = ? , ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, personVo.getName()); 
			pstmt.setString(2, personVo.getHp()); 
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId()); 

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] " + count + " 건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// 삭제
	public int personDelete(int personId) {

		getConnection();

		int count = 0;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from phonedb ";
			query += " where person_id = ? ";

			// 테스트
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao]" + count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}
	
	// 검색
	public List<PersonVo> serchList(String keyword) {
			
			List<PersonVo> personList = new ArrayList<PersonVo>();

			getConnection();
			
			keyword = "%" + keyword + "%";

			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " select person_id, ";
				query += " 		  name, ";
				query += " 		  hp, ";
				query += "        company ";
				query += " from phonedb ";
				query += " where person_id like ? ";
				query += " or name like ? ";
				query += " or hp like ? ";
				query += " or company like ? ";
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.setString(3, keyword);
				pstmt.setString(4, keyword);
				
				rs = pstmt.executeQuery();

				// 4.결과처리 List<PersonVo>로 구성
				while (rs.next()) {
					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");

					PersonVo vo = new PersonVo(personId, name, hp, company);
					personList.add(vo);
					
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();
			
			return personList;

		}

	// 리스트 가져오기
	public List<PersonVo> getPersonList() {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from phonedb ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리 List<PersonVo>로 구성
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo vo = new PersonVo(personId, name, hp, company);
				personList.add(vo);
				
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return personList;
	}
	
	//사람 1명 정보 가져오기
	public PersonVo getPerson(int personId) {
		
		getConnection();
		
		PersonVo personVo = null;
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "         company ";
			query += " from phonedb ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int personID = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				personVo = new PersonVo(personID, name, hp, company);
			}
			

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		
		
		
		close();
		return personVo;
	}

		

}
