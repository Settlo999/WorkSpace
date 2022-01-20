package dao;

//役テーブルを扱うクラス
public class RankDAO {
	
	//Singleton
	private static RankDAO rankDAO = new RankDAO();
	
	private RankDAO() {
	}
	
	public static RankDAO getInstance() {
		return rankDAO;
	}
}
