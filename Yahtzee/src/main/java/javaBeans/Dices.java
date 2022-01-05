package javaBeans;

import java.io.Serializable;

/*
 * 賽クラス 出目(int[])と振り直しの回数(int)を保持
 */
public class Dices implements Serializable {
	
	//出目
	private int[] izumeList;
	//振り直しの回数
	private int remakeDiceCount;
	
	public Dices(int[] izumeList, int remakeDiceCount) { 
		this.izumeList = izumeList;
		this.remakeDiceCount = remakeDiceCount;
	}

	public int[] getIzumeList() { return izumeList; }

	public void setIzumeList(int[] izumeList) { this.izumeList = izumeList; }

	public int getRemakeDiceCount() { return remakeDiceCount; }

	public void setRemakeDiceCount(int remakeDiceCount) { this.remakeDiceCount = remakeDiceCount; }
	
}
