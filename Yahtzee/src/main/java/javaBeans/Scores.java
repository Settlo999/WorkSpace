package javaBeans;

import java.io.Serializable;

/*
 * 点数クラス
 */
public class Scores implements Serializable {
	
	//総得点
	private int sumAll = 0;
	//1～6の目の合計点
	private int sumOneToSix = 0;
	//ﾎﾞｰﾅｽか
	private boolean isBonus = false;

	public int getSumAll() { return sumAll; }
	
	public void setSumAll(int sumAll) { this.sumAll = sumAll; }
	
	public int getSumOneToSix() { return sumOneToSix; }
	
	public void setSumOneToSix(int sumOneToSix) { this.sumOneToSix = sumOneToSix; }

	public boolean getIsBonus() { return isBonus; }

	public void setIsBonus(boolean isBonus) { this.isBonus = isBonus; }
	
}
