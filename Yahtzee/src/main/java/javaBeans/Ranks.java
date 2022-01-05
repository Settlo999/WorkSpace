package javaBeans;

import java.io.Serializable;

/*
 * 記帳管理クラス 記帳状況をnullかStringで保持
 */
public class Ranks implements Serializable {
	
	//1の目
	private String one;
	//2の目
	private String two;
	//3の目
	private String three;
	//4の目
	private String four;
	//5の目
	private String five;
	//6の目
	private String six;
	//チャンス
	private String chance;
	//3カード
	private String threeCard;
	//4カード
	private String fourCard;
	//フルハウス
	private String fullHouse;
	//小さいストレート
	private String smallStraight;
	//大きいストレート
	private String largeStraight;
	//ヨット
	private String yahtzee;
	
	public String getOne() { return one; }
	
	public void setOne(String one) { this.one = one; }
	
	public String getTwo() { return two; }
	
	public void setTwo(String two) { this.two = two; }
	
	public String getThree() { return three; }
	
	public void setThree(String three) { this.three = three; }
	
	public String getFour() { return four; }
	
	public void setFour(String four) { this.four = four; }
	
	public String getFive() { return five; }
	
	public void setFive(String five) { this.five = five; }
	
	public String getSix() { return six; }
	
	public void setSix(String six) { this.six = six; }
	
	public String getChance() { return chance; }
	
	public void setChance(String chance) { this.chance = chance; }
	
	public String getThreeCard() { return threeCard; }
	
	public void setThreeCard(String threeCard) { this.threeCard = threeCard; }
	
	public String getFourCard() { return fourCard; }
	
	public void setFourCard(String fourCard) { this.fourCard = fourCard; }
	
	public String getFullHouse() { return fullHouse; }
	
	public void setFullHouse(String fullHouse) { this.fullHouse = fullHouse; }
	
	public String getSmallStraight() { return smallStraight; }
	
	public void setSmallStraight(String smallStraight) { this.smallStraight = smallStraight; }
	
	public String getLargeStraight() { return largeStraight; }
	
	public void setLargeStraight(String largeStraight) { this.largeStraight = largeStraight; }
	
	public String getYahtzee() { return yahtzee; }
	
	public void setYahtzee(String yahtzee) { this.yahtzee = yahtzee; }
	
}
