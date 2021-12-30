package model;

import java.util.Random;

//ヤッツィー処理クラス
public class YahtzeeLogic {
	
	//数字に対応して出目を作る
	public static int[] makeDice(int[] diceList, String diceNumber) {
		Random random = new Random();
		String[] nums = {"1", "2", "3", "4", "5"};
		
		for(int i = 0; i < 5; i++) {
			if(diceNumber.contains(nums[i])) {
				diceList[i] = random.nextInt(6) + 1;
			}
		}
		
		return diceList;
	}

}
