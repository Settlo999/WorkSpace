import java.util.Random;

/**
 * テスト用
 */
public class LogicTest {

	public static void main(String[] args) {
		
	}
	
	//数字に対応して出目を作る
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
}
