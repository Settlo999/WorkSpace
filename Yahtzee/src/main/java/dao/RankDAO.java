package dao;

/*
 * 役テーブルを扱う ※Singleton
 */
public class RankDAO {
	
	//Singleton
	private static RankDAO rankDAO = new RankDAO();
	
	private RankDAO() {
	}
	
	public static RankDAO getInstance() {
		return rankDAO;
	}
}
