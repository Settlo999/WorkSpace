package model;

import java.util.Random;

//ヤッツィー処理クラス
public class YahtzeeLogic {
	
	//サイコロを振る
	public static int makeDice() {
		Random random = new Random();
		int dice = random.nextInt(6) + 1;
		return dice;
	}
}
