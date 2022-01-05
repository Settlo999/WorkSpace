package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javaBeans.GameDetail;

//ゲーム詳細テーブルを扱うクラス
public class GameDetailDAO {
		
	//DB接続テンプレ
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//ゲーム詳細テーブルにターン数・出目・得点・役ID・ゲームIDを追加
	public boolean create(GameDetail gameDetail) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "INSERT INTO GAME_DETAIL VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pS = conn.prepareStatement(sql);
			pS.setInt(1, 1);
			int result = pS.executeUpdate();
			
			//追加処理が完了したかチェック。※SQL文が成功していればresult = 1
			if(result != 1) {
				return false;
			}
			
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
