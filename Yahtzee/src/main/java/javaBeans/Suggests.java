package javaBeans;

import java.io.Serializable;

/*
 * 点数表示クラス
 */
public class Suggests implements Serializable {
	//点数の配列
	private String[] suggestList;
	
	public Suggests(String[] suggestList) {
		this.suggestList = suggestList;
	}

	public String[] getSuggestList() { return suggestList; }

	public void setSuggestList(String[] suggestList) { this.suggestList = suggestList; }
	
}
