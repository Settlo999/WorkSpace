package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//ゲームテーブルを扱うクラス
public class GameDAO {
	
	//ゲームID
	private int gameId;
	
	//DB接続テンプレ
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//ゲームテーブルにゲームID・ユーザーIDを追加後、ゲームIDをフィールドに保持
	public boolean create(int userId) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			//GAME_IDは連番のため指定せず
			String sql = "INSERT INTO GAME(USER_ID) VALUES(?)";
			PreparedStatement pS = conn.prepareStatement(sql);
			pS.setInt(1, userId);
			int result = pS.executeUpdate();
			
			//追加処理が完了したかチェック。※SQL文が成功していればresult = 1
			if(result != 1) {
				return false;
			}
			
			//1番新しいゲームのIDだけ取得
			sql = "SELECT * FROM GAME WHERE USER_ID = ? ORDER BY GAME_ID DESC";
			pS = conn.prepareStatement(sql);
			pS.setInt(1, userId);
			
			ResultSet rS = pS.executeQuery();
			
			if(rS.next()) {
				int gameId = rS.getInt("GAME_ID");
				setGameId(gameId);
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
	
	public int getGameId() { return gameId; }
	
	public void setGameId(int gameId) { this.gameId = gameId; }
	
}
