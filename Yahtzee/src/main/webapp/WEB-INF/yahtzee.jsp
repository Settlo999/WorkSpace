
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="javaBeans.GameDetail"%>
<%@page import="javaBeans.Dices"%>
<%@page import="javaBeans.Ranks"%>
<%@page import="javaBeans.Scores"%>
<%@page import="javaBeans.Suggests"%>
    
<%
GameDetail gameDetail = (GameDetail) session.getAttribute("gameDetail");
Dices dices = (Dices) session.getAttribute("dices");
int[] izumeList = dices.getIzumeList();
int remakeDiceCount = dices.getRemakeDiceCount();
Ranks ranks = (Ranks) session.getAttribute("ranks");
Scores scores = (Scores) session.getAttribute("scores");
Suggests suggests = (Suggests) session.getAttribute("suggests");
String[] suggestList = suggests.getSuggestList();
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
<p>ゲームスタート！</p>
<% } %>

<p>
ターン<%= gameDetail.getTurn() %>/13 現在のスコア: <%= scores.getSumAll() %>点
	<% if(scores.getSumOneToSix() < 63) { %>
		(ﾎﾞｰﾅｽまで<%= 63 - scores.getSumOneToSix() %>点)
	<% } %>
	<% if(scores.getSumOneToSix() >= 63) { %>
		(ﾎﾞｰﾅｽ獲得)
	<% } %>
<p>

<p>
<% for(int i = 0; i < 5; i++) { %>
<%= izumeList[i] %>
<% } %>
<p>

<% if(remakeDiceCount != 2) { %>
<p>振り直す数字を選んでください(振り直しはあと<%= 2 - remakeDiceCount %>回)<p>
<form action="/Yahtzee/Yahtzee" method="post">
	<p><input type="checkbox" name="remake" value="1">1番目
	<input type="checkbox" name="remake" value="2">2番目
	<input type="checkbox" name="remake" value="3">3番目
	<input type="checkbox" name="remake" value="4">4番目
	<input type="checkbox" name="remake" value="5">5番目
	<input type="submit" value="振りなおす"><p><br>
</form>
<% } %>

<form action="/Yahtzee/Yahtzee" method="post">
	<% if(ranks.getOne() == null) { %>
	<p><input type="radio" name="receipt" value="one">1の目 <%= suggestList[0] %></p>
	<% } %>
	<% if(ranks.getTwo() == null) { %>
	<p><input type="radio" name="receipt" value="two">2の目 <%= suggestList[1] %></p>
	<% } %>
	<% if(ranks.getThree() == null) { %>
	<p><input type="radio" name="receipt" value="three">3の目 <%= suggestList[2] %></p>
	<% } %>
	<% if(ranks.getFour() == null) { %>
	<p><input type="radio" name="receipt" value="four">4の目 <%= suggestList[3] %></p>
	<% } %>
	<% if(ranks.getFive() == null) { %>
	<p><input type="radio" name="receipt" value="five">5の目 <%= suggestList[4] %></p>
	<% } %>
	<% if(ranks.getSix() == null) { %>
	<p><input type="radio" name="receipt" value="six">6の目 <%= suggestList[5] %></p>
	<% } %>
	<% if(ranks.getChance() == null) { %>
	<p><input type="radio" name="receipt" value="chance">チャンス <%= suggestList[6] %></p>
	<% } %>
	<% if(ranks.getThreeCard() == null) { %>
	<p><input type="radio" name="receipt" value="threeCard">3カード <%= suggestList[7] %></p>
	<% } %>
	<% if(ranks.getFourCard() == null) { %>
	<p><input type="radio" name="receipt" value="fourCard">4カード <%= suggestList[8] %></p>
	<% } %>
	<% if(ranks.getFullHouse() == null) { %>
	<p><input type="radio" name="receipt" value="fullHouse">フルハウス <%= suggestList[9] %></p>
	<% } %>
	<% if(ranks.getSmallStraight() == null) { %>
	<p><input type="radio" name="receipt" value="smallStraight">小さいストレート <%= suggestList[10] %></p>
	<% } %>
	<% if(ranks.getLargeStraight() == null) { %>
	<p><input type="radio" name="receipt" value="largeStraight">大きいストレート <%= suggestList[11] %></p>
	<% } %>
	<% if(ranks.getYahtzee() == null) { %>
	<p><input type="radio" name="receipt" value="yahtzee">ヨット <%= suggestList[12] %></p>
	<% } %>
	<input type="submit" value="記帳"><br>
</form>

</body>
</html>