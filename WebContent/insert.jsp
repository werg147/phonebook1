<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.PersonVo" %>
<%@ page import = "com.javaex.dao.PhoneDao" %>

<% 
//http://localhost:8088/phonebook1/insert.jsp?name=%EC%9E%A5%EC%84%B8%EB%A6%BC&hp=010-1111-1111&company=02-2222-2222
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String company = request.getParameter("company");

	PersonVo personVo = new PersonVo(name, hp, company);
	
	PhoneDao phoneDao = new PhoneDao();
	//저장
	phoneDao.personInsert(personVo);
	
	//리스트
	List<PersonVo> personList = phoneDao.getPersonList();
	
	
	//response.sendRedirect("./list.jsp");

%>
