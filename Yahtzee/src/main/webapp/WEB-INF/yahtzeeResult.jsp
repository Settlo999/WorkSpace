<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javaBeans.Scores" %>
<%@page import="javaBeans.Ranks" %>
<%@page import="javaBeans.GameDetail" %>

<%
Scores scores = (Scores) session.getAttribute("scores");
Ranks ranks = (Ranks) session.getAttribute("ranks");
GameDetail gameDetail = (GameDetail) session.getAttribute("gameDetail");
%>

<!-- ヤッツィー結果画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果です</title>
</head>
<body>
<p>ゲームID: <%= gameDetail.getGameId() %><p>
<p>総得点 <%= scores.getSumAll() %>点<p>
<p>
1の目: <%= ranks.getOne() %>点<br>
2の目: <%= ranks.getTwo() %>点<br>
3の目: <%= ranks.getThree() %>点<br>
4の目: <%= ranks.getFour() %>点<br>
5の目: <%= ranks.getFive() %>点<br>
6の目: <%= ranks.getSix() %>点<br>
チャンス: <%= ranks.getChance() %>点<br>
3カード: <%= ranks.getThreeCard() %>点<br>
4カード: <%= ranks.getFourCard() %>点<br>
フルハウス: <%= ranks.getFullHouse() %>点<br>
小さいストレート: <%= ranks.getSmallStraight() %>点<br>
大きいストレート: <%= ranks.getLargeStraight() %>点<br>
ヨット: <%= ranks.getYahtzee() %>点
<p>

<a href="/Yahtzee/Yahtzee">ゲームへ</a><br>
<a href="/Yahtzee/LogView">記録閲覧へ</a>

</body>
</html>