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
	//役ID
	private int rankId;
	//ゲームID
	private int gameId;
	
	public int getTurn() { return turn; }
	
	public void setTurn(int turn) { this.turn = turn; }
	
	public String getIzume() { return izume; }
	
	public void setIzume(String izume) { this.izume = izume; }
	
	public int getScore() { return score; }
	
	public void setScore(int score) { this.score = score; }
	
	public int getRankId() { return rankId; }
	
	public void setRankId(int rankId) { this.rankId = rankId; }
	
	public int getGameId() { return gameId; }
	
	public void setGameId(int gameId) { this.gameId = gameId; }
	
}
