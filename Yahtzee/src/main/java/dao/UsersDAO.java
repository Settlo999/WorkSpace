package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//ユーザーテーブルを扱うクラス
public class UsersDAO {
	
	//DB接続テンプレ
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//登録済みのユーザーか
	public boolean isFind(User user) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "SELECT * FROM USERS WHERE NAME = ? AND PASS = ?";
			PreparedStatement PS = conn.prepareStatement(sql);
			//SQL文中の?にユーザー名とパスワードを設定
			PS.setString(1, user.getName());
			PS.setString(2, user.getPass());
			
			ResultSet RS = PS.executeQuery();
			
			//該当するユーザーが居たらtrueで返す
			if(RS.next()) {
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
	
	//ユーザーを追加
	public boolean create(User user) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			//連番のUSER_ID以外を指定
			String sql = "INSERT INTO USERS(NAME, PASS) VALUES(?, ?)";
			PreparedStatement PS = conn.prepareStatement(sql);
			PS.setString(1, user.getName());
			PS.setString(2, user.getPass());
			
			int result = PS.executeUpdate();
			
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

