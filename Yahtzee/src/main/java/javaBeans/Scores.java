package javaBeans;

import java.io.Serializable;

/*
 * 点数管理クラス 総得点・1～6の目の合計点をint、ﾎﾞｰﾅｽかをbooleanで保持
 */
public class Scores implements Serializable {
	
	//総得点
	private int sumAll;
	//1～6の目の合計点
	private int sumOneToSix;
	//ﾎﾞｰﾅｽか
	private boolean isBonus;
	
	public Scores(int sumAll, int sumOneToSix, boolean isBonus) {
		this.sumAll = sumAll;
		this.sumOneToSix = sumOneToSix;
		this.isBonus = isBonus;
	}

	public int getSumAll() { return sumAll; }
	
	public void setSumAll(int sumAll) { this.sumAll = sumAll; }
	
	public int getSumOneToSix() { return sumOneToSix; }
	
	public void setSumOneToSix(int sumOneToSix) { this.sumOneToSix = sumOneToSix; }

	public boolean getIsBonus() { return isBonus; }

	public void setIsBonus(boolean isBonus) { this.isBonus = isBonus; }
	
}
