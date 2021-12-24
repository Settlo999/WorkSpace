import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		System.out.println("ゲームスタート");
		//改行
		System.out.println(); 
		
		int[] dices = new int[5];
		
		for(int i = 0; i < 5; i++) {
			dices[i] = makeDice();
		}
		
		//出目
		System.out.println("現在の出目" + Arrays.toString(dices)); 
		System.out.println();
		
		System.out.println("振り直しは1から5までの数字を入力");
		System.out.println("終了はf");
		System.out.println();
		
		//振り直しのカウント
		int retryCount = 0; 
		for(int i = 0; i < 2; i++) {
			Scanner scanner = new Scanner(System.in);
			//振り直しか終了の判定用
			String retryOrFin = scanner.next(); 
			
			//judgeへ 定数=変数にするべし
			if("f".equals(retryOrFin)) { 
				break;
			}
			else {
				String[] nums = {"1", "2", "3", "4", "5"};
				//振り直し
				for(int j = 0; j < 5; j++) {
					if (retryOrFin.contains(nums[j])) {
						dices[j] = makeDice();
					}
				}
				
				System.out.println("現在の出目" + Arrays.toString(dices));
				//回数追加
				retryCount++; 
			}
			//振り直し2回目なら終了してjudgeへ
			if(retryCount == 2) { 
				break;
			}
		}
		judge(dices);
	}
	
	/*
	 * サイコロを振る
	 * @return dice 1から6
	 */
	public static int makeDice() {
		Random random = new Random();
		int dice = random.nextInt(6) + 1;
		return dice;
	}
	
        /*
         * 出目の配列を受け取って各スコアを表示する
         * @param int[] dices 出目の配列
         */
	public static void judge(int[] dices) {
		//目のカウント
		int[] numCounts = {0, 0, 0, 0, 0, 0};
		
		//それぞれカウント
		for(int i = 0; i < 5; i++) { 
			for(int j = 1; j <= 6; j++) {
				if (dices[i] == j) {
					numCounts[j - 1]++;
				}
			}
		}
		
		//1の目の合計
		if(numCounts[0] >= 1) {
			System.out.println("1の目: " + (1 * numCounts[0])); 
		}
		if(numCounts[1] >= 1) {
			System.out.println("2の目: " + (2 * numCounts[1]));
		}
		if(numCounts[2] >= 1) {
			System.out.println("3の目: " + (3 * numCounts[2]));
		}
		if(numCounts[3] >= 1) {
			System.out.println("4の目: " + (4 * numCounts[3]));
		}
		if(numCounts[4] >= 1) {
			System.out.println("5の目: " + (5 * numCounts[4]));
		}
		if(numCounts[5] >= 1) {
			System.out.println("6の目: " + (6 * numCounts[5]));
		}
		
		//出目の和
		System.out.println("チャンス: " + (1 * numCounts[0] + 2 * numCounts[1] + 
				3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5])); 
		
		//出目がAAAXXなら出目の和
		if(numCounts[0] >= 3 || numCounts[1] >= 3 || numCounts[2] >= 3 || 
				numCounts[3] >= 3 || numCounts[4] >= 3 || numCounts[5] >= 3) {
			System.out.println("3カード : " + (1 * numCounts[0] + 2 * numCounts[1] + 
					3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5])); 
		}
		
		//出目がAAAAXなら出目の和
		if(numCounts[0] >= 4 || numCounts[1] >= 4 || numCounts[2] >= 4 ||
				numCounts[3] >= 4 || numCounts[4] >= 4 || numCounts[5] >= 4) {
			System.out.println("4カード : " + (1 * numCounts[0] + 2 * numCounts[1] + 
					3 * numCounts[2] + 4 * numCounts[3] + 5 * numCounts[4] + 6 * numCounts[5])); 
		}
		
		//出目がAABBBかAAAAAなら25点
		boolean a = (numCounts[0] == 0 || numCounts[0] == 2 || numCounts[0] == 3 || numCounts[0] == 5);
		boolean b = (numCounts[1] == 0 || numCounts[1] == 2 || numCounts[1] == 3 || numCounts[1] == 5);
		boolean c = (numCounts[2] == 0 || numCounts[2] == 2 || numCounts[2] == 3 || numCounts[2] == 5);
		boolean d = (numCounts[3] == 0 || numCounts[3] == 2 || numCounts[3] == 3 || numCounts[3] == 5);
		boolean e = (numCounts[4] == 0 || numCounts[4] == 2 || numCounts[4] == 3 || numCounts[4] == 5);
		boolean f = (numCounts[5] == 0 || numCounts[5] == 2 || numCounts[5] == 3 ||numCounts[5] == 5);
		if (a && b && c && d && e && f) {
			System.out.println("フルハウス : " + 25);
		}
		
		//出目が1234Xか2345Xか3456Xなら30点
		boolean g = (numCounts[0] >= 1 && numCounts[1] >= 1 && numCounts[2] >= 1 && numCounts[3] >= 1);
		boolean h = (numCounts[1] >= 1 && numCounts[2] >= 1 && numCounts[3] >= 1 && numCounts[4] >= 1);
		boolean i = (numCounts[2] >= 1 && numCounts[3] >= 1 && numCounts[4] >= 1 && numCounts[5] >= 1);
		if(g || h || i) {
			System.out.println("Sスト : " + 30); 
		}
		
		//出目が12345か23456なら40点
		boolean j = (numCounts[0] == 1 && numCounts[1] == 1 && numCounts[2] == 1 && numCounts[3] == 1 && numCounts[4] == 1);
		boolean k = (numCounts[1] == 1 && numCounts[2] == 1 && numCounts[3] == 1 && numCounts[4] == 1 || numCounts[5] == 1);
		if(j || k) {
			System.out.println("Bスト : " + 40); 
		}
		
		//同じ出目が5つ以上なら50点
		if(numCounts[0] == 5 || numCounts[1] == 5 || numCounts[2] == 5 ||
				numCounts[3] == 5 || numCounts[4] == 5 || numCounts[5] == 5) {
			System.out.println("ヨット : " + 50); 
		}	
	}

}
