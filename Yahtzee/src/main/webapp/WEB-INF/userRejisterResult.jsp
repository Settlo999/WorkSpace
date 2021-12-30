<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javaBeans.User" %>  

<% User newUser = (User) session.getAttribute("newUser"); %>
    
<!-- ユーザー登録結果画面　-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録に成功しました</title>
</head>
<body>
<p>ユーザーID : <%= newUser.getName() %>
<p>パスワード : <%= newUser.getPass() %>
<a href="/Yahtzee/">TOPへ</a>
</body>
</html>