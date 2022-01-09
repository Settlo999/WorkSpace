package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaBeans.GameDetail;
import javaBeans.Log;

//ゲーム詳細テーブルを扱うクラス
public class GameDetailDAO {
		
	//DB接続テンプレ
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	/*
	 * ゲーム詳細テーブルにターン数・出目・得点・役ID・ゲームIDを追加
	 * @param gameDetail ゲーム詳細
	 * @return boolean trueかfalse
	 */
	public boolean create(GameDetail gameDetail) {
		int turn = gameDetail.getTurn();
		
		String izume = "";
		int[] izumeList = gameDetail.getIzumeList();
		for(int i = 0; i < 5; i++) {
			izume += String.valueOf(izumeList[i]);
		}
		
		int score = gameDetail.getScore();
		int rankId = Integer.parseInt(gameDetail.getRankId());
		int gameId = gameDetail.getGameId();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "INSERT INTO GAME_DETAIL VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pS = conn.prepareStatement(sql);
			pS.setInt(1, turn);
			pS.setString(2, izume);
			pS.setInt(3, score);
			pS.setInt(4, rankId);
			pS.setInt(5, gameId);
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
	
	/*
	 * テーブルの内容を全て取得する
	 */
	public List<Log> getAll () {
		
		Connection conn = null;
		List<Log> logList = new ArrayList<Log>();
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "SELECT TURN, IZUME, SCORE, RANK_ID, GAME_ID FROM GAME_DETAIL";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int turn = rs.getInt("TURN");
				String izume = rs.getString("IZUME");
				int score = rs.getInt("SCORE");
				int rankId = rs.getInt("RANK_ID");
				int gameId = rs.getInt("GAME_ID");
				
				Log log = new Log();
				log.setTurn(turn);
				log.setIzume(izume);
				log.setScore(score);
				log.setRankId(rankId);
				log.setGameId(gameId);
				
				logList.add(log);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
		return logList;
	}
}
