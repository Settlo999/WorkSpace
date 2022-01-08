package javaBeans;

import java.io.Serializable;

/*
 * 賽クラス
 */
public class Dices implements Serializable {
	
	//出目
	private int[] izumeList;
	//振り直しの回数
	private int remakeDiceCount = 0;
	
	public Dices(int[] izumeList) {
		this.izumeList = izumeList;
	}

	public int[] getIzumeList() { return izumeList; }

	public void setIzumeList(int[] izumeList) { this.izumeList = izumeList; }

	public int getRemakeDiceCount() { return remakeDiceCount; }

	public void setRemakeDiceCount(int remakeDiceCount) { this.remakeDiceCount = remakeDiceCount; }
	
}
