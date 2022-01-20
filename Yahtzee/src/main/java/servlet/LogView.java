package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaBeans.Log;
import javaBeans.User;
import model.LogViewLogic;

/*
 * 記録閲覧用のコントローラー
 */
@WebServlet("/LogView")
public class LogView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		
		//セッションスコープからログイン中のユーザーのIDを取得
		User user = (User) ses.getAttribute("loginUser");
		int userId = user.getUserId();
		
		//ユーザーがプレイしたゲームの一覧を取得
		LogViewLogic logViewLogic = LogViewLogic.getInstance();
		List<Integer> gameIdList = logViewLogic.getGameIdList(userId);
		
		//ゲームとゲーム毎の総得点の一覧を取得し、セッションスコープに保存
		List<String> gameIdAndScoreList = logViewLogic.getGameAndScore(gameIdList);
		ses.setAttribute("gameIdAndScoreList", gameIdAndScoreList);
		
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/logView.jsp");
		d.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		
		//フォームから送信された情報を取得
		request.setCharacterEncoding("UTF-8");
		String gameId = request.getParameter("gameId");
		
		//ゲーム詳細の一覧を取得
		LogViewLogic logViewLogic = LogViewLogic.getInstance();
		List<Log> logList = logViewLogic.getLog(gameId);
		
		ses.setAttribute("logList", logList);
		
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/gameDetailView.jsp");
		d.forward(request, response);
	}
}
