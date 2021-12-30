package javaBeans;

import java.io.Serializable;

//出目　int[]で保持
public class Dices implements Serializable {
	private int[] diceList;

	public Dices(int[] diceList) {
		this.diceList = diceList;
	}

	public int[] getDiceList() { return diceList; }

	public void setDiceList(int[] diceList) { this.diceList = diceList; }

}
