package javaBeans;

import java.io.Serializable;

/*
 * 記帳クラス
 */
public class Ranks implements Serializable {
	
	//1の目の得点
	private int one = 99;
	//2の目
	private int two = 99;
	//3の目
	private int three = 99;
	//4の目
	private int four = 99;
	//5の目
	private int five = 99;
	//6の目
	private int six = 99;
	//チャンス
	private int chance = 99;
	//3カード
	private int threeCard = 99;
	//4カード
	private int fourCard = 99;
	//フルハウス
	private int fullHouse = 99;
	//小さいストレート
	private int smallStraight = 99;
	//大きいストレート
	private int largeStraight = 99;
	//ヨット
	private int yahtzee = 99;
	
	public int getOne() { return one; }
	
	public void setOne(int one) { this.one = one; }
	
	public int getTwo() { return two; }
	
	public void setTwo(int two) { this.two = two; }
	
	public int getThree() { return three; }
	
	public void setThree(int three) { this.three = three; }
	
	public int getFour() { return four; }
	
	public void setFour(int four) { this.four = four; }
	
	public int getFive() { return five; }
	
	public void setFive(int five) { this.five = five; }
	
	public int getSix() { return six; }
	
	public void setSix(int six) { this.six = six; }
	
	public int getChance() { return chance; }
	
	public void setChance(int chance) { this.chance = chance; }
	
	public int getThreeCard() { return threeCard; }
	
	public void setThreeCard(int threeCard) { this.threeCard = threeCard; }
	
	public int getFourCard() { return fourCard; }
	
	public void setFourCard(int fourCard) { this.fourCard = fourCard; }
	
	public int getFullHouse() { return fullHouse; }
	
	public void setFullHouse(int fullHouse) { this.fullHouse = fullHouse; }
	
	public int getSmallStraight() { return smallStraight; }
	
	public void setSmallStraight(int smallStraight) { this.smallStraight = smallStraight; }
	
	public int getLargeStraight() { return largeStraight; }
	
	public void setLargeStraight(int largeStraight) { this.largeStraight = largeStraight; }
	
	public int getYahtzee() { return yahtzee; }
	
	public void setYahtzee(int yahtzee) { this.yahtzee = yahtzee; }
	
}
