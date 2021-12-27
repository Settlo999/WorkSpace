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
import model.GameDetail;
import model.User;

//ヨット処理用のコントローラー
@WebServlet("/Yahtzee")
public class Yahtzee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		User loginUser = (User) ses.getAttribute("loginUser");
		
		GameDAO gameDao = new GameDAO();
		int userId = loginUser.getUserId();
		gameDao.create(userId);
		int gameId = gameDao.getGameId();
		
		//ターン(1)とゲームIDだけでゲーム詳細を仮作成
		GameDetail gameDetail = new GameDetail(1, "0", 0, 0, gameId);
		ses.setAttribute("gameDetail", gameDetail);
		
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/yahtzee.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
