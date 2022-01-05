package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GameDAO;
import javaBeans.Dices;
import javaBeans.GameDetail;
import javaBeans.Ranks;
import javaBeans.Scores;
import javaBeans.Suggests;
import javaBeans.User;
import model.YahtzeeLogic;

/*
 * ヨット処理用のコントローラー
 */
@WebServlet("/Yahtzee")
public class Yahtzee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		
		//セッションスコープからログイン中のユーザーのIDを取得
		User user = (User) ses.getAttribute("loginUser");
		int userId = user.getUserId();
		
		//ゲームテーブルにゲームID（連番）・ユーザーIDを追加、ゲームIDを取得
		GameDAO gameDao = new GameDAO();
		gameDao.create(userId);
		int gameId = gameDao.getGameId();
		
		//ターン(1)とゲームIDだけでゲーム詳細クラスを作成、セッションスコープに保存 ※出目、スコア、役IDは後に追加
		GameDetail gameDetail = new GameDetail(1, "0", 0, 0, gameId);
		ses.setAttribute("gameDetail", gameDetail);
		
		//記帳管理クラスを作成してセッションスコープに保存
		Ranks ranks = new Ranks();
		ses.setAttribute("ranks", ranks);
		
		//点数管理クラスを作成してセッションスコープに保存
		Scores scores = new Scores(0, 0, false);
		ses.setAttribute("scores", scores);
		
		//初回の出目と振り直しの回数(0)で賽クラスを作成してセッションスコープに保存
		int[] izumeList = new int[5];
		izumeList = YahtzeeLogic.makeIzume(izumeList, "12345");
		Dices dices = new Dices(izumeList, 0);
		ses.setAttribute("dices", dices);
		
		//初回の出目を基に点数表示クラスを作成してセッションスコープに保存
		String[] suggestList = YahtzeeLogic.suggest(izumeList);
		Suggests suggests = new Suggests(suggestList);
		ses.setAttribute("suggests", suggests);
		
		//yahtzee.jspへ
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzee.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//振り直し用の番号の配列(String[])をjspのフォームから取得
		String[] numsList = request.getParameterValues("remake");
		
		//記帳用の役名(String)をjspのフォームから取得
		String rank = request.getParameter("receipt");
		
		HttpSession ses = request.getSession();
		
		//出目をセッションスコープから取得
		Dices dices = (Dices) ses.getAttribute("dices");
		int[] izumeList = dices.getIzumeList();
		
		//点数表示をセッションスコープから取得
		Suggests suggests = (Suggests) ses.getAttribute("suggests");
		
		//振り直し
		if(numsList != null) {
			
			//makeDice用にString[]からStringに
			String numsForMakeIzume = "";
			for(int i = 0; i < numsList.length; i++) {
				numsForMakeIzume += numsList[i];
			}
			
			//出目を更新
			YahtzeeLogic.makeIzume(izumeList, numsForMakeIzume);
			dices.setIzumeList(izumeList);
			
			//点数予測を更新
			suggests.setSuggestList(YahtzeeLogic.suggest(izumeList));
			
			//振り直しの回数を+1
			dices.setRemakeDiceCount(dices.getRemakeDiceCount() + 1);
		}
		
		//記帳
		if(rank != null) {
			
			//諸々用意
			Ranks ranks = (Ranks) ses.getAttribute("ranks");
			Scores scores = (Scores) ses.getAttribute("scores");
			GameDetail gameDetail = (GameDetail) ses.getAttribute("gameDetail");
//			GameDetailDAO gameDetailDAO = new GameDetailDAO();
			
			//点数取得
			int score = YahtzeeLogic.makeScore(rank, izumeList);
			
			if("one".equals(rank)) {
				//1～6の目のﾎﾞｰﾅｽ計算用に加算
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setOne("");
//				gameDetail.setRankId(1);
			}
			if("two".equals(rank)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setTwo("");
			}
			if("three".equals(rank)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setThree("");
			}
			if("four".equals(rank)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setFour("");
			}
			if("five".equals(rank)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setFive("");
			}
			if("six".equals(rank)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setSix("");
			}
			if("chance".equals(rank)) {
				ranks.setChance("");
			}
			if("threeCard".equals(rank)) {
				ranks.setThreeCard("");
			}
			if("fourCard".equals(rank)) {
				ranks.setFourCard("");
			}
			if("fullHouse".equals(rank)) {
				ranks.setFullHouse("");
			}
			if("smallStraight".equals(rank)) {
				ranks.setSmallStraight("");
			}
			if("largeStraight".equals(rank)) {
				ranks.setLargeStraight("");
			}
			if("yahtzee".equals(rank)) {
				ranks.setYahtzee("");
			}
			
			//総得点に加算
			scores.setSumAll(scores.getSumAll() + score);
			
			//1～6の目の合計が63点以上なら、一度だけ35点を加算
			if(scores.getSumOneToSix() >= 63 && scores.getIsBonus() == false) {
				scores.setSumAll(scores.getSumAll() + 35);
				scores.setIsBonus(true);
			}
			
			//String[]からStringに
//			String izume = "";
//			for(int i = 0; i < izumeList.length; i++) {
//				izume += izumeList[i];
//			}
			
//			gameDetail.setIzume(izume);
//			gameDetail.setScore(score);
			
			//dao処理(未)
			
			//次のターンの出目を作成、振り直し回数リセット
			dices.setIzumeList(YahtzeeLogic.makeIzume(izumeList, "12345"));
			dices.setRemakeDiceCount(0);
			
			//点数予測を作成
			suggests.setSuggestList(YahtzeeLogic.suggest(izumeList));
			
			//ターン数を+1
			gameDetail.setTurn(gameDetail.getTurn() + 1);
		}
		
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzee.jsp");
		d.forward(request, response);
	}

}
