
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="javaBeans.GameDetail"%>
<%@page import="javaBeans.Dice"%>
<%@page import="javaBeans.Ranks"%>
<%@page import="javaBeans.Scores"%>
<%@page import="javaBeans.Suggests"%>
    
<%
GameDetail gameDetail = (GameDetail) session.getAttribute("gameDetail");
Dice dice = (Dice) session.getAttribute("dice");
int[] izumeList = dice.getIzumeList();
int remakeDiceCount = dice.getRemakeDiceCount();
Ranks ranks = (Ranks) session.getAttribute("ranks");
Scores scores = (Scores) session.getAttribute("scores");
Suggests suggests = (Suggests) session.getAttribute("suggests");
String[] suggestList = suggests.getSuggestList();
%>
    
<!-- ヤッツィー画面 -->
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
	<% if(ranks.getOne() == 99) { %>
	<p><input type="radio" name="receipt" value="1">1の目 <%= suggestList[0] %></p>
	<% } %>
	<% if(ranks.getTwo() == 99) { %>
	<p><input type="radio" name="receipt" value="2">2の目 <%= suggestList[1] %></p>
	<% } %>
	<% if(ranks.getThree() == 99) { %>
	<p><input type="radio" name="receipt" value="3">3の目 <%= suggestList[2] %></p>
	<% } %>
	<% if(ranks.getFour() == 99) { %>
	<p><input type="radio" name="receipt" value="4">4の目 <%= suggestList[3] %></p>
	<% } %>
	<% if(ranks.getFive() == 99) { %>
	<p><input type="radio" name="receipt" value="5">5の目 <%= suggestList[4] %></p>
	<% } %>
	<% if(ranks.getSix() == 99) { %>
	<p><input type="radio" name="receipt" value="6">6の目 <%= suggestList[5] %></p>
	<% } %>
	<% if(ranks.getChance() == 99) { %>
	<p><input type="radio" name="receipt" value="7">チャンス <%= suggestList[6] %></p>
	<% } %>
	<% if(ranks.getThreeCard() == 99) { %>
	<p><input type="radio" name="receipt" value="8">3カード <%= suggestList[7] %></p>
	<% } %>
	<% if(ranks.getFourCard() == 99) { %>
	<p><input type="radio" name="receipt" value="9">4カード <%= suggestList[8] %></p>
	<% } %>
	<% if(ranks.getFullHouse() == 99) { %>
	<p><input type="radio" name="receipt" value="10">フルハウス <%= suggestList[9] %></p>
	<% } %>
	<% if(ranks.getSmallStraight() == 99) { %>
	<p><input type="radio" name="receipt" value="11">小さいストレート <%= suggestList[10] %></p>
	<% } %>
	<% if(ranks.getLargeStraight() == 99) { %>
	<p><input type="radio" name="receipt" value="12">大きいストレート <%= suggestList[11] %></p>
	<% } %>
	<% if(ranks.getYahtzee() == 99) { %>
	<p><input type="radio" name="receipt" value="13">ヨット <%= suggestList[12] %></p>
	<% } %>
	<p><input type="submit" value="記帳"><p>
</form>

<% if(ranks.getOne() != 99) { %>
	1の目: <%= ranks.getOne() %>点<br>
<% } %>
<% if(ranks.getTwo() != 99) { %>
	2の目: <%= ranks.getTwo() %>点<br>
<% } %>
<% if(ranks.getThree() != 99) { %>
	3の目: <%= ranks.getThree() %>点<br>
<% } %>
<% if(ranks.getFour() != 99) { %>
	4の目: <%= ranks.getFour() %>点<br>
<% } %>
<% if(ranks.getFive() != 99) { %>
	5の目: <%= ranks.getFive() %>点<br>
<% } %>
<% if(ranks.getSix() != 99) { %>
	6の目: <%= ranks.getSix() %>点<br>
<% } %>
<% if(ranks.getChance() != 99) { %>
	チャンス: <%= ranks.getChance() %>点<br>
<% } %>
<% if(ranks.getThreeCard() != 99) { %>
	3カード: <%= ranks.getThreeCard() %>点<br>
<% } %>
<% if(ranks.getFourCard() != 99) { %>
	4カード: <%= ranks.getFourCard() %>点<br>
<% } %>
<% if(ranks.getFullHouse() != 99) { %>
	フルハウス: <%= ranks.getFullHouse() %>点<br>
<% } %>
<% if(ranks.getSmallStraight() != 99) { %>
	小さいストレート: <%= ranks.getSmallStraight() %>点<br>
<% } %>
<% if(ranks.getLargeStraight() != 99) { %>
	大きいストレート: <%= ranks.getLargeStraight() %>点<br>
<% } %>
<% if(ranks.getYahtzee() != 99) { %>
	ヨット: <%= ranks.getYahtzee() %>点
<% } %>

</body>
</html>