<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ユーザー登録画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h1>ユーザーIDとパスワードを入力してアカウントを作成してください</h1>
<form action="/Yahtzee/Login" method="post">
ユーザー名:<input type="text" name="name"><br>
パスワード:<input type="text" name="pass">
<input type="hidden" name="isFirst" value="yes">
<input type="submit" value="登録">
</form>
</body>
</html>