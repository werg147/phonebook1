<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.javaex.dao.PhoneDao" %>
<%@ page import = "com.javaex.vo.PersonVo"%>

<%
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String company = request.getParameter("company");
	int personId = Integer.parseInt(request.getParameter("id"));

	PersonVo personVo = new PersonVo(personId, name, hp, company);
	
	PhoneDao phoneDao = new PhoneDao();
	phoneDao.personUpdate(personVo);
	
	response.sendRedirect("./list.jsp");

%>
