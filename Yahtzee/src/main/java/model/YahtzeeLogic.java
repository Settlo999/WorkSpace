package model;

import java.util.Random;

import dao.GameDAO;
import dao.GameDetailDAO;
import javaBeans.GameDetail;

/*
 * ヨット処理クラス ※Singleton
 */
public class YahtzeeLogic {
	
	//Singleton用のインスタンス
	private static YahtzeeLogic yahtzeeLogic = new YahtzeeLogic();
	
	private YahtzeeLogic() {
	}
	
	public static YahtzeeLogic getInstance() {
		return yahtzeeLogic;
	}
	
	/*
	 * ユーザーIDを受け取ってGameDAOに(ゲームID, ユーザーID)で追加させ、ゲームIDを取得して返す
	 * @param userId ユーザーID
	 * @return gameId ゲームID
	 */
	public int addGame(int userId) {
		GameDAO gameDao = GameDAO.getInstance();
		gameDao.create(userId);
		int gameId = gameDao.getGameId();
		
		return gameId;
	}
	
	/*
	 * ゲーム詳細を受け取って、GameDetailDAOに追加させる
	 * @param GameDetail gameDetail ゲーム詳細
	 */
	public void addGameDetail(GameDetail gameDetail) {
		GameDetailDAO gameDetailDAO = GameDetailDAO.getInstance();
		gameDetailDAO.create(gameDetail);
	}
	
	/*
	 * 初回の出目を作成して返す
	 * @return int[] izumeList 出目
	 */
	public int[] makeFirstIzume() {
		int[] izumeList = new int[5];
		
		Random random = new Random();
		for(int i = 0; i < 5; i++) {
			izumeList[i] = random.nextInt(6) + 1;
		}
		
		return izumeList;
	}
	
	/*
	 * 出目と数字の文字列を受け取って、出目を作り直して返す
	 * @param int[] izumeList 出目
	 * @param String[] numList 数字の文字列
	 * @return int[] izumeList 作り直した出目
	 */
	public int[] makeIzume(int[] izumeList, String[] numList) {
		//String[]からStringに
		String numsForMakeIzume = "";
		for(int i = 0; i < numList.length; i++) {
			numsForMakeIzume += numList[i];
		}
		
		String[] nums = {"1", "2", "3", "4", "5"};
		
		Random random = new Random();
		for(int i = 0; i < 5; i++) {
			if(numsForMakeIzume.contains(nums[i])) {
				izumeList[i] = random.nextInt(6) + 1;
			}
		}
		
		return izumeList;
	}
	
	/*
	 * 役IDと出目を受け取って、得点を返す
	 * @param String rankIdId 役ID
	 * @param int[] izumeList 出目
	 * @return int score 得点
	 */
	public int makeScore(String rankId, int[] izumeList) {
		//目毎のカウントの配列
		int[] numCounts = {0, 0, 0, 0, 0, 0};
		
		//カウントする
		for(int i = 0; i < izumeList.length; i++) {
			numCounts[izumeList[i] - 1]++;
		}
		
		//1～6の目それぞれの合計
		if("1".equals(rankId)) {
			return 1 * numCounts[0];
		}
		else if("2".equals(rankId)) {
			return 2 * numCounts[1];
		}
		else if("3".equals(rankId)) {
			return 3 * numCounts[2];
		}
		else if("4".equals(rankId)) {
			return 4 * numCounts[3];
		}
		else if("5".equals(rankId)) {
			return 5 * numCounts[4];
		}
		else if("6".equals(rankId)) {
			return 6 * numCounts[5];
		}
		//出目の和
		else if("7".equals(rankId)) {
			return 1 * numCounts[0] + 2 * numCounts[1] + 
					3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5];
		}
		//出目がAAAXXなら出目の和 ※これ以降の役は不成立 = 0点の処理も必須
		else if("8".equals(rankId)) {
			if(numCounts[0] >= 3 || numCounts[1] >= 3 || numCounts[2] >= 3 || 
					numCounts[3] >= 3 || numCounts[4] >= 3 || numCounts[5] >= 3) {
				
				return 1 * numCounts[0] + 2 * numCounts[1] + 
						3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5];
			}
			else {
				return 0;
			}
		}
		//出目がAAAAXなら出目の和
		else if("9".equals(rankId)) {
			if(numCounts[0] >= 4 || numCounts[1] >= 4 || numCounts[2] >= 4 ||
					numCounts[3] >= 4 || numCounts[4] >= 4 || numCounts[5] >= 4) {
				
				return 1 * numCounts[0] + 2 * numCounts[1] + 
						3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5]; 
			}
			else {
				return 0;
			}
		}
		//出目がAABBBかAAAAAなら25点
		else if("10".equals(rankId)) {
			boolean isFullHouse1 = (numCounts[0] == 0 || numCounts[0] == 2 || numCounts[0] == 3 || numCounts[0] == 5);
			boolean isFullHouse2 = (numCounts[1] == 0 || numCounts[1] == 2 || numCounts[1] == 3 || numCounts[1] == 5);
			boolean isFullHouse3 = (numCounts[2] == 0 || numCounts[2] == 2 || numCounts[2] == 3 || numCounts[2] == 5);
			boolean isFullHouse4 = (numCounts[3] == 0 || numCounts[3] == 2 || numCounts[3] == 3 || numCounts[3] == 5);
			boolean isFullHouse5 = (numCounts[4] == 0 || numCounts[4] == 2 || numCounts[4] == 3 || numCounts[4] == 5);
			boolean isFullHouse6 = (numCounts[5] == 0 || numCounts[5] == 2 || numCounts[5] == 3 ||numCounts[5] == 5);
			
			if (isFullHouse1 && isFullHouse2 && isFullHouse3
					&& isFullHouse4 && isFullHouse5 && isFullHouse6) {
				
				return 25;
			}
			else {
				return 0;
			}
		}
		//出目が1234Xか2345Xか3456Xなら30点
		else if("11".equals(rankId)) {
			boolean isSmallStraight1 = (numCounts[0] >= 1 && numCounts[1] >= 1 && numCounts[2] >= 1 && numCounts[3] >= 1);
			boolean isSmallStraight2 = (numCounts[1] >= 1 && numCounts[2] >= 1 && numCounts[3] >= 1 && numCounts[4] >= 1);
			boolean isSmallStraight3 = (numCounts[2] >= 1 && numCounts[3] >= 1 && numCounts[4] >= 1 && numCounts[5] >= 1);
			
			if(isSmallStraight1 || isSmallStraight2 || isSmallStraight3) {
				return 30;
			}
			else {
				return 0;
			}
		}
		//出目が12345か23456なら40点
		else if("12".equals(rankId)) {
			boolean isLargeStraight1 = (numCounts[0] == 1 && numCounts[1] == 1 && numCounts[2] == 1 && numCounts[3] == 1 && numCounts[4] == 1);
			boolean isLargeStraight2 = (numCounts[1] == 1 && numCounts[2] == 1 && numCounts[3] == 1 && numCounts[4] == 1 && numCounts[5] == 1);
			
			if(isLargeStraight1 || isLargeStraight2) {
				return 40;
			}
			else {
				return 0;
			}
		}
		//同じ出目が5つ以上なら50点
		else {
			if(numCounts[0] == 5 || numCounts[1] == 5 || numCounts[2] == 5 ||
					numCounts[3] == 5 || numCounts[4] == 5 || numCounts[5] == 5) {
				
				return 50;
			}
			else {
				return 0;
			}
		}
		
	}
	
	//出目を受け取って、役と得点をString[]で返す
	public static String[] suggest(int[] izumeList) {
		String[] suggestList = new String[13];
		
		suggestList[0] = String.valueOf(yahtzeeLogic.makeScore("1", izumeList)) + "点";
		suggestList[1] = String.valueOf(yahtzeeLogic.makeScore("2", izumeList)) + "点";
		suggestList[2] = String.valueOf(yahtzeeLogic.makeScore("3", izumeList)) + "点";
		suggestList[3] = String.valueOf(yahtzeeLogic.makeScore("4", izumeList)) + "点";
		suggestList[4] = String.valueOf(yahtzeeLogic.makeScore("5", izumeList)) + "点";
		suggestList[5] = String.valueOf(yahtzeeLogic.makeScore("6", izumeList)) + "点";
		suggestList[6] = String.valueOf(yahtzeeLogic.makeScore("7", izumeList)) + "点";
		suggestList[7] = String.valueOf(yahtzeeLogic.makeScore("8", izumeList)) + "点";
		suggestList[8] = String.valueOf(yahtzeeLogic.makeScore("9", izumeList)) + "点";
		suggestList[9] = String.valueOf(yahtzeeLogic.makeScore("10", izumeList)) + "点";
		suggestList[10] = String.valueOf(yahtzeeLogic.makeScore("11", izumeList)) + "点";
		suggestList[11] = String.valueOf(yahtzeeLogic.makeScore("12", izumeList)) + "点";
		suggestList[12] = String.valueOf(yahtzeeLogic.makeScore("13", izumeList)) + "点";
		
		return suggestList;
	}

}
