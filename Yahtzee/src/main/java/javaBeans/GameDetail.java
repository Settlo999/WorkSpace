package javaBeans;

import java.io.Serializable;

//ゲーム詳細クラス ※DB保存用
public class GameDetail implements Serializable {
	
	//ターン数
	private int turn = 1;
	//出目
	private int[] izumeList;
	//得点
	private int score;
	//役ID
	private String rankId;
	//ゲームID
	private int gameId;
	
	public int getTurn() { return turn; }
	
	public void setTurn(int turn) { this.turn = turn; }
	
	public int[] getIzumeList() { return izumeList; }
	
	public void setIzumeList(int[] izume) { this.izumeList = izume; }
	
	public int getScore() { return score; }
	
	public void setScore(int score) { this.score = score; }
	
	public String getRankId() { return rankId; }
	
	public void setRankId(String rankId) { this.rankId = rankId; }
	
	public int getGameId() { return gameId; }
	
	public void setGameId(int gameId) { this.gameId = gameId; }

}
