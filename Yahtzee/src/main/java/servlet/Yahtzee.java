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
import javaBeans.User;
import model.YahtzeeLogic;

//ヨット処理用のコントローラー
@WebServlet("/Yahtzee")
public class Yahtzee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		
		//ログイン中のユーザーをセッションスコープから取得、そのIDも取得
		User loginUser = (User) ses.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		//ゲームテーブルにゲームID（連番）・ユーザーIDを追加、ゲームIDを取得
		GameDAO gameDao = new GameDAO();
		gameDao.create(userId);
		int gameId = gameDao.getGameId();
		
		//ターン(1)とゲームIDだけでゲーム詳細を仮作成、セッションスコープに保存 ※出目、スコア、役IDは後に追加
		GameDetail gameDetail = new GameDetail(1, "0", 0, 0, gameId);
		ses.setAttribute("gameDetail", gameDetail);
		
		//初回の出目を作成してセッションスコープに保存
		YahtzeeLogic yahtzeeLogic = new YahtzeeLogic();
		int[] diceList = new int[5];
		yahtzeeLogic.makeDice(diceList, "12345");
		Dices dices = new Dices(diceList);
		ses.setAttribute("Dices", dices);
		
		//yahtzee.jspへ
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzee.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//振り直し用の番号の配列をリクエストから取得
		String[] nums = request.getParameterValues("remake");
		
		//振り直し
		if(nums != null) {
			//makeDice用にString[]からStringに
			String numsForMakeDice = "";
			for(int i = 0; i < nums.length; i++) {
				numsForMakeDice += nums[i];
			}
			
			//出目を取得
			HttpSession ses = request.getSession();
			Dices dices = (Dices) ses.getAttribute("Dices");
			int[] diceList = dices.getDiceList();
			
			//出目を更新してセッションスコープに保存
			YahtzeeLogic yahtzeeLogic = new YahtzeeLogic();
			yahtzeeLogic.makeDice(diceList, numsForMakeDice);
			dices.setDiceList(diceList);
			ses.setAttribute("Dices", dices);
			
			RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzee.jsp");
			d.forward(request, response);
		}
		
	}

}
