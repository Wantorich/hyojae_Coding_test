import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class Solution {
	static int [] cardA = new int[9];
	static int [] cardB = new int[9];
	static List<Integer> numList;
	static int win, lose;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			win = 0; lose = 0;
			numList = new ArrayList<Integer>();
			for (int i = 1; i <= 18; i++) numList.add(i);
            
			for (int i = 0; i < 9; i++) {
				int val = sc.nextInt();
				numList.remove((Integer) val);
				cardA[i] = val;
			}
			
			permutation(0, new int[9], new boolean[9]);
			System.out.printf("#%d %d %d\n", t, win, lose);
		}
		sc.close();
	}

	private static void permutation(int k, int[] sel, boolean[] v) {
		if (k == sel.length) {
			// 승부를 9라운드 진행하고 이기는지 지는지 확인
			if (game(sel)) win++;
			else lose++;
			return;
		}
		
		for (int i = 0; i < numList.size(); i++) {
			if (v[i]) continue;
			v[i] = true;
			sel[k] = numList.get(i);
			permutation(k+1, sel, v);
			v[i] = false;
		}
	}

	private static boolean game(int[] sel) {
		int a = 0, b = 0;
		for (int i = 0; i < 9; i++) {
			// 9라운드 진행
			if (cardA[i] > sel[i]) a += cardA[i] + sel[i];
			else if (cardA[i] < sel[i]) b += cardA[i] + sel[i];
		}
		
		return a > b ? true : false;
	}
}

