package javaBeans;

import java.io.Serializable;

/*
 * 記録クラス
 */
public class Log implements Serializable {
	
	//ターン数
	private int turn;
	//出目
	private String izume;
	//得点
	private int score;
	//役名
	private String name;
	
	public int getTurn() { return turn; }
	
	public void setTurn(int turn) { this.turn = turn; }
	
	public String getIzume() { return izume; }
	
	public void setIzume(String izume) { this.izume = izume; }
	
	public int getScore() { return score; }
	
	public void setScore(int score) { this.score = score; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
}
