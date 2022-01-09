import java.util.List;

import dao.GameDetailDAO;
import javaBeans.Log;

/**
 * テスト用
 */
public class LogicTest {

	public static void main(String[] args) {
		
		GameDetailDAO gameDetailDAO = new GameDetailDAO();
		List<Log> logList = gameDetailDAO.getAll();
		
		for(int i = 0; i < logList.size(); i++) {
			Log log = logList.get(i);
			System.out.println("ターン: " + log.getTurn());
			System.out.println("出目: " + log.getIzume());
			System.out.println("得点: " + log.getScore());
			System.out.println("役ID: " + log.getRankId());
//			System.out.println("ゲームID: " + log.getGameId());
			
		}
	}
}
