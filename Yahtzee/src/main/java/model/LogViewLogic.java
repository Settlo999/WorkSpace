package model;

import java.util.List;

import dao.GameDAO;
import dao.GameDetailDAO;
import javaBeans.Log;

//記録閲覧処理
public class LogViewLogic {
	
	//Singleton
	private static LogViewLogic logViewLogic = new LogViewLogic();
	
	private LogViewLogic() {
	}
	
	public static LogViewLogic getInstance() {
		return logViewLogic;
	}
	
	/*
	 * ユーザーIDを受け取ってGameDAOにプレイしたゲームを検索させ、ゲームIDを取得してリストで返す
	 * @param userId ユーザーID
	 * @return gameIdList ゲームIDのリスト
	 */
	public List<Integer> getGameIdList(int userId){
		GameDAO gameDAO = GameDAO.getInstance();
		List<Integer> gameIdList = gameDAO.findGame(userId);
		
		return gameIdList;
	}
	
	/*
	 * ゲームIDのリストを受け取ってGameDetailDAOにゲーム毎の総得点を取得させ、リストにして返す
	 */
	public List<String> getGameAndScore(List<Integer> gameIdList) {
		GameDetailDAO gameDetailDAO = GameDetailDAO.getInstance();
		List<String> gameAndScoreList = gameDetailDAO.getGameAndScore(gameIdList);
		
		return gameAndScoreList;
	}
	
	/*
	 * ゲームIDを受け取って、gameDetailDAOにゲームの詳細の一覧を取得させて返す
	 * @param int gameId ゲームID
	 * @return List<Log> ゲームの詳細の一覧
	 */
	public List<Log> getLog(String gameId) {
		GameDetailDAO gameDetailDAO = GameDetailDAO.getInstance();
		List<Log> logList = gameDetailDAO.getLog(gameId);
		
		return logList;
	}
	
}
