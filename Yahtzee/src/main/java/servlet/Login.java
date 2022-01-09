package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaBeans.User;
import model.LoginLogic;
import model.UserRejisterLogic;

/*
 * ログイン処理用のコントローラー
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//新規登録からgetされたらユーザー登録画面にフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/userRejister.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームから送信された情報を取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		//通常ログインor新規登録のチェック用変数。通常ログインならisFirst = null
		String isFirst = request.getParameter("isFirst");
		
		User user = new User(name, pass);
		
		//通常ログインなら
		if(isFirst == null) {
			LoginLogic loginLogic = new LoginLogic();
			boolean isLogin = loginLogic.execute(user);
			
			if(isLogin) {
				HttpSession ses = request.getSession();
				ses.setAttribute("loginUser", user);
				
				RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/main.jsp");
				d.forward(request, response);
			}
			else {
				request.setAttribute("errorMsg", "ログインに失敗しました。再度やり直してください。");
				
				RequestDispatcher d = request.getRequestDispatcher("index.jsp");
				d.forward(request, response);
			}	
		}
		else {
			UserRejisterLogic userRejisterLogic = new UserRejisterLogic();
			boolean isRejistered = userRejisterLogic.rejister(user);
			
			//登録成功なら
			if(isRejistered) {
				HttpSession ses = request.getSession();
				ses.setAttribute("newUser", user);
				
				RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/userRejisterResult.jsp");
				d.forward(request, response);
			}
			else {
				request.setAttribute("errorMsg", "ユーザー登録に失敗しました。再度やり直してください。");
				
				RequestDispatcher d = request.getRequestDispatcher("index.jsp");
				d.forward(request, response);
			}
		}
	}

}
