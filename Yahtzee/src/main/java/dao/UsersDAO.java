package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaBeans.User;

/*
 * ユーザーテーブルを扱うDAO ※Singleton
 */
public class UsersDAO {
	
	//Singleton
	private static UsersDAO usersDAO = new UsersDAO();
	
	private UsersDAO() {
	}
	
	public static UsersDAO getInstance() {
		return usersDAO;
	}
	
	//DB接続テンプレ
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	/*
	 * ユーザーを受け取ってそのユーザーが登録済みなら、IDをユーザーテーブルから取得してユーザーにsetし、その成否を返す
	 * @param User user ユーザー
	 * @return trueかfalse 成否
	 */
	public boolean isFind(User user) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "SELECT * FROM USERS WHERE NAME = ? AND PASS = ?";
			PreparedStatement pS = conn.prepareStatement(sql);
			//SQL文中の?にユーザー名とパスワードを設定
			pS.setString(1, user.getName());
			pS.setString(2, user.getPass());
			
			ResultSet rS = pS.executeQuery();
			
			//該当するユーザーが居たらuserにユーザーIDをsetしてtrueで返す
			if(rS.next()) {
				int userId = rS.getInt("USER_ID");
				user.setUserId(userId);
				return true;
			}
			else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * ユーザーを受けとって、ユーザーテーブルに追加し、その成否を返す
	 * @param User user ユーザー
	 * @return trueかfalse 成否
	 */
	public boolean create(User user) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			//連番のUSER_ID以外を指定
			String sql = "INSERT INTO USERS(NAME, PASS) VALUES(?, ?)";
			PreparedStatement pS = conn.prepareStatement(sql);
			pS.setString(1, user.getName());
			pS.setString(2, user.getPass());
			
			int result = pS.executeUpdate();
			
			//SQL文が成功していれば1
			if(result != 1) {
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
