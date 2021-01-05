<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "com.javaex.dao.PhoneDao" %>

<%
	int personId = Integer.parseInt(request.getParameter("id"));

	PhoneDao phoneDao = new PhoneDao();
	phoneDao.personDelete(personId);
	
	response.sendRedirect("./list.jsp");
	
%>

