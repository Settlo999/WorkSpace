<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javaBeans.User" %>

<% User loginUser = (User) session.getAttribute("loginUser"); %>
    
<!-- メイン画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メイン</title>
</head>
<body>
<p>ようこそ、<%= loginUser.getName() %>さん</p>
<a href="/Yahtzee/Yahtzee">ゲームへ</a><br>
<a href="/Yahtzee/LogView">記録閲覧へ</a>
</body>
</html>