<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="javaBeans.Log" %>

<%
List<Log> logList = (List<Log>) session.getAttribute("logList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ゲーム詳細閲覧</title>
</head>
<body>

<% for(int i = 0; i < logList.size(); i++) { %>
	ターン<%= logList.get(i).getTurn() %>　
	<%= logList.get(i).getIzume() %>　
	<%=	logList.get(i).getScore() %>点　
	<%= logList.get(i).getName() %><br>
<% } %>

<p><a href="/Yahtzee/LogView">戻る</a><p>

</body>
</html>