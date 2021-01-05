<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.javaex.dao.PhoneDao" %>
<%@ page import = "com.javaex.vo.PersonVo" %>

<%
	int personId = Integer.parseInt(request.getParameter("id"));
	
	PhoneDao phoneDao = new PhoneDao();
	PersonVo personVo = phoneDao.getPerson(personId);  //아이디를 주면 아이디에 해당하는 person 정보를 가져온다.
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>장세림 전화번호 수정화면</h1>
	
	<p>
		수정 화면 입니다.
		아래 항목을 수정하고 "수정" 버튼을 클릭하세요.
	</p>
	<form action="./update.jsp" method="get">
		이름(name): <input type = "text" name="name" value="<%=personVo.getName()%>"> <br>
		핸드폰(hp): <input type = "text" name="hp" value="<%=personVo.getHp()%>"> <br>
		회사(company): <input type = "text" name="company" value="<%=personVo.getCompany()%>"> <br>
		
		코드(id) 히든으로 처리: <input type="text" name="id" value="<%=personVo.getPersonId()%>"> <br>
		
		
		<button type="submit">수정</button>
	</form>
</body>
</html>