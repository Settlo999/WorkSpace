<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String errorMsg = (String) request.getAttribute("errorMsg");
%>
    
<!-- トップ画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ</title>
</head>
<body>
<form action="/Yahtzee/Login" method="post">
ユーザー名:<input type="text" name="name"><br>
パスワード:<input type="text" name="pass"><br>
<input type="submit" value="ログイン">
</form>
<a href="/Yahtzee/Login">新規登録</a>
<% if(errorMsg != null) { %>
	<p><%= errorMsg %></p>
<% } %>
</body>
</html>