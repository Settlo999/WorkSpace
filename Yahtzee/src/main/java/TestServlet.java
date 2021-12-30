

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaBeans.TestJavaBeans;

/**
 * テスト用
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d = request.getRequestDispatcher("test.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String[] nums = request.getParameterValues("remake");
		String numsForMakeDice = "";
		for(int i = 0; i < nums.length; i++) {
			numsForMakeDice += nums[i];
		}
		
		TestJavaBeans testJavaBeans = new TestJavaBeans(numsForMakeDice);
		
		HttpSession ses = request.getSession();
		ses.setAttribute("testJavaBeans", testJavaBeans);
		
		RequestDispatcher d = request.getRequestDispatcher("test.jsp");
		d.forward(request, response);
	}

}
