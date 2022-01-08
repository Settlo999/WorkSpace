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
import dao.GameDetailDAO;
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
		
		//ゲーム詳細クラスを作成、ゲームIDをセットしてセッションスコープに保存 ※出目、スコア、役IDはdoPostで追加
		GameDetail gameDetail = new GameDetail();
		gameDetail.setGameId(gameId);
		ses.setAttribute("gameDetail", gameDetail);
		
		//記帳クラスを作成してセッションスコープに保存
		Ranks ranks = new Ranks();
		ses.setAttribute("ranks", ranks);
		
		//点数クラスを作成してセッションスコープに保存
		Scores scores = new Scores();
		ses.setAttribute("scores", scores);
		
		//初回の出目で賽クラスを作成、セッションスコープに保存
		int [] izumeList = YahtzeeLogic.makeFirstIzume();
		Dices dices = new Dices(izumeList);
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
		String[] numList = request.getParameterValues("remake");
		
		//記帳用の役ID(String)をjspのフォームから取得
		String rankId = request.getParameter("receipt");
		
		HttpSession ses = request.getSession();
		
		//出目をセッションスコープから取得
		Dices dices = (Dices) ses.getAttribute("dices");
		int[] izumeList = dices.getIzumeList();
		
		//点数表示をセッションスコープから取得
		Suggests suggests = (Suggests) ses.getAttribute("suggests");
		
		//振り直し
		if(numList != null) {
			//出目を更新
			izumeList = YahtzeeLogic.makeIzume(izumeList, numList);
			dices.setIzumeList(izumeList);
			
			//点数予測を更新
			suggests.setSuggestList(YahtzeeLogic.suggest(izumeList));
			
			//振り直しの回数を+1
			dices.setRemakeDiceCount(dices.getRemakeDiceCount() + 1);
		}
		
		//記帳
		if(rankId != null) {
			//諸々用意
			Ranks ranks = (Ranks) ses.getAttribute("ranks");
			Scores scores = (Scores) ses.getAttribute("scores");
			GameDetail gameDetail = (GameDetail) ses.getAttribute("gameDetail");
			GameDetailDAO gameDetailDAO = new GameDetailDAO();
			
			//点数取得
			int score = YahtzeeLogic.makeScore(rankId, izumeList);
			
			if("1".equals(rankId)) {
				//1～6の目のﾎﾞｰﾅｽ計算用に加算
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setOne(score);
			}
			if("2".equals(rankId)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setTwo(score);
			}
			if("3".equals(rankId)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setThree(score);
			}
			if("4".equals(rankId)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setFour(score);
			}
			if("5".equals(rankId)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setFive(score);
			}
			if("6".equals(rankId)) {
				scores.setSumOneToSix(scores.getSumOneToSix() + score);
				ranks.setSix(score);
			}
			if("7".equals(rankId)) {
				ranks.setChance(score);
			}
			if("8".equals(rankId)) {
				ranks.setThreeCard(score);
			}
			if("9".equals(rankId)) {
				ranks.setFourCard(score);
			}
			if("10".equals(rankId)) {
				ranks.setFullHouse(score);
			}
			if("11".equals(rankId)) {
				ranks.setSmallStraight(score);
			}
			if("12".equals(rankId)) {
				ranks.setLargeStraight(score);
			}
			if("13".equals(rankId)) {
				ranks.setYahtzee(score);
			}
			
			//総得点に加算
			scores.setSumAll(scores.getSumAll() + score);
			
			//1～6の目の合計が63点以上なら、一度だけ35点を加算
			if(scores.getSumOneToSix() >= 63 && scores.getIsBonus() == false) {
				scores.setSumAll(scores.getSumAll() + 35);
				scores.setIsBonus(true);
			}
			
			//dao処理
			gameDetail.setIzumeList(izumeList);
			gameDetail.setScore(score);
			gameDetail.setRankId(rankId);
			
			gameDetailDAO.create(gameDetail);
						
			//次のターンの出目を作成、振り直し回数リセット
			izumeList = YahtzeeLogic.makeFirstIzume();
			dices.setIzumeList(izumeList);
			dices.setRemakeDiceCount(0);
			
			//点数予測を作成
			suggests.setSuggestList(YahtzeeLogic.suggest(izumeList));
			
			//ターン数を+1
			gameDetail.setTurn(gameDetail.getTurn() + 1);
			
			if(gameDetail.getTurn() == 14) {
				RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzeeResult.jsp");
				d.forward(request, response);
			}
		}
		
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzee.jsp");
		d.forward(request, response);
	}

}
