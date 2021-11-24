import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("ゲームスタート");
		System.out.println(); //改行
		
		int d1 = makeDice(); //ダイス1～5
		int d2 = makeDice();
		int d3 = makeDice();
		int d4 = makeDice();
		int d5 = makeDice();
		int[] dices = {d1, d2, d3, d4, d5}; //配列に
		Arrays.sort(dices); //昇順に
		
		System.out.println("現在の出目" + Arrays.toString(dices)); //出目
		System.out.println();
		
		System.out.println("振り直しは1から5までの数字を入力");
		System.out.println("終了はf");
		System.out.println();
		
		int retryCount = 0; //振り直しのカウント
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String rertyOrFin = scanner.next(); //振り直しか終了の判定用
	
			if(rertyOrFin.equals("f")) { //judgeへ
				break;
			}
			else {
				if (rertyOrFin.contains("1")) { //1番目だけ振り直し
					dices[0] = makeDice();
				}
				if (rertyOrFin.contains("2")) {
					dices[1] = makeDice();
				}
				if (rertyOrFin.contains("3")) {
					dices[2] = makeDice();
				}
				if (rertyOrFin.contains("4")) {
					dices[3] = makeDice();
				}
				if (rertyOrFin.contains("5")) {
					dices[4] = makeDice();
				}
				
				Arrays.sort(dices); //整列し直し
				System.out.println("現在の出目" + Arrays.toString(dices));
				retryCount++; //回数追加
			}
			
			if(retryCount == 2) { //振り直し2回目なら終了してjudgeへ
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
		int oneCount = 0; //1の目のカウント
		int twoCount = 0;
		int threeCount = 0;
		int fourCount = 0;
		int fiveCount = 0;
		int sixCount = 0;
			
		for(int i = 0; i < dices.length; i++) { //それぞれカウント
			if(dices[i] == 1) {
				oneCount++;
			}
			if(dices[i] == 2) {
				twoCount++;
			}
			if(dices[i] == 3) {
				threeCount++;
			}
			if(dices[i] == 4) {
				fourCount++;
			}
			if(dices[i] == 5) {
				fiveCount++;
			}
			if(dices[i] == 6) {
				sixCount++;
			}
		}
		
		if(oneCount >= 1) {
			System.out.println("1の目: " + (1 * oneCount)); //1の目の合計
		}
		if(twoCount >= 1) {
			System.out.println("2の目: " + (2 * twoCount));
		}
		if(threeCount >= 1) {
			System.out.println("3の目: " + (3 * threeCount));
		}
		if(fourCount >= 1) {
			System.out.println("4の目: " + (4 * fourCount));
		}
		if(fiveCount >= 1) {
			System.out.println("5の目: " + (5 * fiveCount));
		}
		if(sixCount >= 1) {
			System.out.println("6の目: " + (6 * sixCount));
		}
		
		System.out.println("チャンス: " + (1 * oneCount + 2 * twoCount + 
				3 * threeCount + 4 * fourCount + 5 * fiveCount + 6 * sixCount)); //出目の和
		
		if(oneCount >= 3 || twoCount >= 3 || threeCount >= 3 || 
				fourCount >= 3 || fiveCount >= 3 || sixCount >= 3) {
			System.out.println("3カード : " + (1 * oneCount + 2 * twoCount + 
					3 * threeCount + 4 * fourCount + 5 * fiveCount + 6 * sixCount)); //同じ出目が3つ以上なら出目の和
		}
		
		if(oneCount >= 4 || twoCount >= 4 || threeCount >= 4 ||
				fourCount >= 4 || fiveCount >= 4 || sixCount >= 4) {
			System.out.println("4カード : " + (1 * oneCount + 2 * twoCount + 
					3 * threeCount + 4 * fourCount + 5 * fiveCount + 6 * sixCount)); //同じ出目が4つ以上なら出目の和
		}
		
		if(oneCount == 0 || oneCount == 2 || oneCount == 3) {
			if(twoCount == 0 || twoCount == 2 || twoCount == 3) {
				if(threeCount == 0 || threeCount == 2 || threeCount == 3) {
					if(fourCount == 0 || fourCount == 2 || fourCount == 3) {
						if(fiveCount == 0 || fiveCount == 2 || fiveCount == 3) {
							if(sixCount == 0 || sixCount == 2 || sixCount == 3) {
								System.out.println("フルハウス : " + 25); //出目がAABBBかAAABBなら25点
							}
						}
					}
				}
			}
		}
		
		if((oneCount >= 1 && twoCount >= 1 && threeCount >= 1 && fourCount >= 1) ||
			(twoCount >= 1 && threeCount >= 1 && fourCount >= 1 && fiveCount >= 1) ||
			(threeCount >= 1 && fourCount >= 1 && fiveCount >= 1 && sixCount >= 1)) {
			System.out.println("Sスト : " + 30); //1234か2345か3456の出目なら30点
		}
		
		if((oneCount == 1 && twoCount == 1 && threeCount == 1 && fourCount == 1 && fiveCount == 1) ||
				(twoCount == 1 && threeCount == 1 && fourCount == 1 && fiveCount == 1 || sixCount == 1)) {
				System.out.println("Bスト : " + 40); //12345か23456の出目なら40点
			}
		
		if(oneCount == 5 || twoCount == 5 || threeCount == 5 ||
				fourCount == 5 || fiveCount == 5 || sixCount == 5) {
			System.out.println("フルハウス : " + 25); //AAAAAもフルハウス扱いで25点
			System.out.println("ヨット : " + 50); //同じ出目が5つ以上なら50点
		}	
	}

}
