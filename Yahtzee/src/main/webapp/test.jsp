<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="javaBeans.TestJavaBeans" %>
  
<%
TestJavaBeans testJavaBeans = (TestJavaBeans) session.getAttribute("testJavaBeans");
%>

<!-- テスト用 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% if(testJavaBeans != null) { %>
<%= testJavaBeans.getNumsForMakeDice() %>
<% } %>

<form action="/Yahtzee/TestServlet" method="post">
	<input type="checkbox" name="remake" value="1">1
	<input type="checkbox" name="remake" value="2">2
	<input type="checkbox" name="remake" value="3">3
	<input type="checkbox" name="remake" value="4">4
	<input type="checkbox" name="remake" value="5">5
	<input type="submit" value="振りなおす">
</form>
</body>
</html>