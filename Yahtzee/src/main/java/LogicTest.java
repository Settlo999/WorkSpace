/**
 * テスト用
 */
public class LogicTest {

	public static void main(String[] args) {
		
		int[] izumeList = {1, 2, 3, 4, 5};
		String izume = "";
		
		for(int i = 0; i < 5; i++) {
			izume += String.valueOf(izumeList[i]);
		}
		
		System.out.println(izume);
	}
}
