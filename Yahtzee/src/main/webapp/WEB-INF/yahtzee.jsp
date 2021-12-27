
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="model.GameDetail"%>
    
<%
GameDetail gameDetail = (GameDetail) session.getAttribute("gameDetail");
%>
    
<!-- ヤッツィー画面出力 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヨット</title>
</head>
<body>
<form action="/Yahtzee/Yahtzee" method="post">
<input type="submit" value="振る">
</form>

<% if(gameDetail.getTurn() == 1) { %>
<p>ゲームスタート！</p><br>
<% } %>

</body>
</html>