package model;

import java.io.Serializable;

//ゲーム詳細(ターン数、出目、得点、役ID、ゲームID) sessionスコープに保存する用
public class GameDetail implements Serializable {
	
	private int turn;
	private String izume;
	private int score;
	private int rankId;
	private int gameId;
	
	public GameDetail(int turn, String izume, int score, int rankId, int gameId) {
		this.turn = turn;
		this.izume = izume;
		this.score = score;
		this.rankId = rankId;
		this.gameId = gameId;
	}
	
	public int getTurn() { return turn; }
	
	public void setTurn(int turn) { this.turn = turn; }
	
	public String getIzume() { return izume; }
	
	public void setIzume(String izume) { this.izume = izume; }
	
	public int getScore() { return score; }
	
	public void setScore(int score) { this.score = score; }
	
	public int getrankId() { return rankId; }
	
	public void setrankId(int rankId) { this.rankId = rankId; }
	
	public int getgameId() { return gameId; }
	
	public void setgameId(int gameId) { this.gameId = gameId; }

}
