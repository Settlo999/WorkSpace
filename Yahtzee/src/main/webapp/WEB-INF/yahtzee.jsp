
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="javaBeans.GameDetail"%>
<%@page import="javaBeans.Dices"%>
    
<%
GameDetail gameDetail = (GameDetail) session.getAttribute("gameDetail");
Dices dices = (Dices) session.getAttribute("Dices");
int[] diceList = dices.getDiceList();
%>
    
<!-- ヤッツィー画面出力 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヨット</title>
</head>
<body>
<% if(gameDetail.getTurn() == 1) { %>
<p>ゲームスタート！</p><br>
<% } %>

<% for(int i = 0; i < 5; i++) { %>
<%= diceList[i] %>
<% } %>
<p>振り直す数字を選んでください<p>
<form action="/Yahtzee/Yahtzee" method="post">
	<input type="checkbox" name="remake" value="1">1
	<input type="checkbox" name="remake" value="2">2
	<input type="checkbox" name="remake" value="3">3
	<input type="checkbox" name="remake" value="4">4
	<input type="checkbox" name="remake" value="5">5
	<input type="submit" value="振りなおす">
</form>
</body>
</html>