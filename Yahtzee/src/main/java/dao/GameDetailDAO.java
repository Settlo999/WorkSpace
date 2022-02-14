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

/*
 * ゲーム詳細テーブルを扱うDAO ※Singleton
 */
public class GameDetailDAO {
	
	//Singleton
	private static GameDetailDAO gameDetailDAO = new GameDetailDAO();
	
	private GameDetailDAO() {
	}
	
	public static GameDetailDAO getInstance() {
		return gameDetailDAO;
	}
	
	//DB接続テンプレ
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	/*
	 * ゲーム詳細テーブルにターン数・出目・得点・役ID・ゲームIDを追加して成否を返す
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		return true;
	}
	
	/*
	 * ゲームIDのリストを受け取って、ゲーム毎の総得点と結び付けてリストで返す
	 * @param List<Integer> gameIdList  ゲームIDのリスト
	 * @return gameAndScoreList ゲームIDと総得点の組み合わせのリスト
	 */
	public List<String> getGameAndScore(List<Integer> gameIdList) {
		List<String> gameAndScoreList = new ArrayList<String>();
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			for (int i = 0; i < gameIdList.size(); i++) {
				String gameId = String.valueOf(gameIdList.get(i));
				
				String sql = "SELECT SUM(SCORE) FROM GAME_DETAIL WHERE GAME_ID = " + gameId;
				
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					int sumScore = resultSet.getInt("SUM(SCORE)");
					
					String sqlForBonus = "SELECT SUM(SCORE) FROM GAME_DETAIL WHERE GAME_ID = " + gameId + 
							" AND RANK_ID <= 6";
					PreparedStatement pSForBonus = conn.prepareStatement(sqlForBonus);
					ResultSet rSForBonus = pSForBonus.executeQuery();
					
					int sumOneToSix = 0;
					while (rSForBonus.next()) {
						sumOneToSix = rSForBonus.getInt("SUM(SCORE)");
					}
					
					if(sumOneToSix >= 63) {
						sumScore += 35;
					}
					
					gameAndScoreList.add(gameId + ": " + String.valueOf(sumScore) + "点");
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		return gameAndScoreList;
	}
	
	/*
	 * ゲームIDを受け取り、そのゲームの詳細を一覧で返す
	 * @param int gameId ゲームID
	 * @return List<Log> ゲームの詳細一覧
	 */
	public List<Log> getLog (String gameId) {
		List<Log> logList = new ArrayList<Log>();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "SELECT TURN, IZUME, SCORE, NAME FROM GAME_DETAIL " +
			"JOIN RANK ON GAME_DETAIL.RANK_ID = RANK.RANK_ID " +
			"WHERE GAME_ID = " + gameId;
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int turn = resultSet.getInt("TURN");
				String izume = resultSet.getString("IZUME");
				int score = resultSet.getInt("SCORE");
				String name = resultSet.getString("NAME");
				
				Log log = new Log();
				log.setTurn(turn);
				log.setIzume(izume);
				log.setScore(score);
				log.setName(name);
				
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
