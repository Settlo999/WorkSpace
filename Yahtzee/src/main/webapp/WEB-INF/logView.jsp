<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>


<%
List<String> gameIdAndScoreList = (List<String>) session.getAttribute("gameIdAndScoreList");
%>
    
<!-- 記録閲覧画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記録閲覧</title>
</head>
<body>

<% for(int i = 0; i < gameIdAndScoreList.size(); i++) { %>
	<%= gameIdAndScoreList.get(i) %><br>
<% } %>

<p>詳細を閲覧したいゲームのIDを入力してください<p>
<form action="/Yahtzee/LogView" method="post">
ゲームID:　<input type="text" name="gameId">
<input type="submit" value="検索">
</form>

</body>
</html>