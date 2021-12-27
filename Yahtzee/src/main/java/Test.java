import model.GameDetail;

public class Test {

	public static void main(String[] args) {
		
		GameDetail gameDetail = new GameDetail(1, "0", 0, 0, 0);
		
		System.out.println(gameDetail.getgameId());
		System.out.println(gameDetail.getIzume());
		System.out.println(gameDetail.getrankId());
		System.out.println(gameDetail.getScore());
		System.out.println(gameDetail.getTurn());
	}

}
