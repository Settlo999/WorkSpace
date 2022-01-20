import java.util.List;

import javaBeans.Log;
import model.LogViewLogic;

/**
 * テスト用
 */
public class LogicTest {

	public static void main(String[] args) {
		
		
		
		LogViewLogic logViewLogic = LogViewLogic.getInstance();
		
		List<Log> logList = logViewLogic.getLog("294");
		
		for(int i = 0; i < logList.size(); i++) {
			System.out.print("ターン" + logList.get(i).getTurn());
			System.out.print(" " + logList.get(i).getIzume());
			System.out.print(" " + logList.get(i).getScore() + "点");
			System.out.println(" " + logList.get(i).getName());
		}
		
//		List<Integer> gameIdList = new ArrayList<Integer>();
//		gameIdList.add(281);
//		gameIdList.add(282);
//		gameIdList.add(294);
		
		
		
//		List<String> gameAndScoreList = gameDetailDAO.getGameAndScore(gameIdList);
		
//		for(int i= 0; i < gameAndScoreList.size(); i++) {
//		System.out.println(gameAndScoreList.get(i));
//		}
		
//		List<Log> logList = gameDetailDAO.getAll();
		
//		for(int i = 0; i < logList.size(); i++) {
//			Log log = logList.get(i);
//			System.out.print("ターン" + log.getTurn() + ": ");
//			System.out.print(log.getIzume() + " ");
//			System.out.print(log.getRank() + " ");
//			System.out.println(log.getScore() + "点");
//			System.out.println("ゲームID: " + log.getGameId());
//		}
		
//		GameDAO gameDAO = GameDAO.getInstance();
		
//		List<Integer> gameIdList = gameDAO.findGame(5);
//		
//		for(int i= 0; i < gameIdList.size(); i++) {
//			System.out.println(gameIdList.get(i));
//		}
	}
}
