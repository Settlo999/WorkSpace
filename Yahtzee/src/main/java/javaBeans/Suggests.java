package javaBeans;

import java.io.Serializable;

/*
 * 点数表示クラス 点数(XX点)をString[]で保持
 */
public class Suggests implements Serializable {
	//点数の配列
	private String[] suggestList;
	
	public Suggests(String[] suggestList) {
		this.suggestList = suggestList;
	}
	
	public Suggests() {
		
	}



	public String[] getSuggestList() { return suggestList; }

	public void setSuggestList(String[] suggestList) { this.suggestList = suggestList; }
	
}
