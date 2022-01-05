import java.util.Random;

/**
 * テスト用
 */
public class LogicTest {

	public static void main(String[] args) {
		int[] diceList = {2, 3, 4, 5, 6};
		
		System.out.println(makeScore("one", diceList));
		System.out.println(makeScore("two", diceList));
		System.out.println(makeScore("three", diceList));
		System.out.println(makeScore("four", diceList));
		System.out.println(makeScore("five", diceList));
		System.out.println(makeScore("six", diceList));
		System.out.println(makeScore("chance", diceList));
		System.out.println(makeScore("threeCard", diceList));
		System.out.println(makeScore("fourCard", diceList));
		System.out.println(makeScore("fullHouse", diceList));
		System.out.println(makeScore("smallStraight", diceList));
		System.out.println(makeScore("largeStraight", diceList));
		System.out.println(makeScore("yahtzee", diceList));
		
	}
	
	//出目の配列と振りたい賽の番号を受け取って、出目を返す
	public static int[] makeDice(int[] dices, String diceNumber) {
		String[] nums = {"1", "2", "3", "4", "5"};
		Random random = new Random();
		
		for(int i = 0; i < 5; i++) {
			if(diceNumber.contains(nums[i])) {
				dices[i] = random.nextInt(6) + 1;
			}
		}
		
		return dices;
	}
	
	public static int makeScore(String rank, int[] izumeList) {
		//目毎のカウントの配列
		int[] numCounts = {0, 0, 0, 0, 0, 0};
		
		//カウントする
		for(int i = 0; i < izumeList.length; i++) {
			numCounts[izumeList[i] - 1]++;
		}
		
		//1～6の目それぞれの合計
		if("one".equals(rank)) {
			return 1 * numCounts[0];
		}
		else if("two".equals(rank)) {
			return 2 * numCounts[1];
		}
		else if("three".equals(rank)) {
			return 3 * numCounts[2];
		}
		else if("four".equals(rank)) {
			return 4 * numCounts[3];
		}
		else if("five".equals(rank)) {
			return 5 * numCounts[4];
		}
		else if("six".equals(rank)) {
			return 6 * numCounts[5];
		}
		//出目の和
		else if("chance".equals(rank)) {
			return 1 * numCounts[0] + 2 * numCounts[1] + 
					3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5];
		}
		//出目がAAAXXなら出目の和 ※これ以降の役は不成立 = 0点の処理も必須
		else if("threeCard".equals(rank)) {
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
		else if("fourCard".equals(rank)) {
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
		else if("fullHouse".equals(rank)) {
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
		else if("smallStraight".equals(rank)) {
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
		else if("largeStraight".equals(rank)) {
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
}
