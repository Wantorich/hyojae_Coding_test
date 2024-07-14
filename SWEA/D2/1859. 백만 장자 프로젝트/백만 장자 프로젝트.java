
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		sc.nextLine();
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			sc.nextLine();
			int [] prices = new int [N];
			int max = 0;
			int max_idx = 0;
			
			for (int i = 0; i < N; i++) {
				prices[i] = sc.nextInt();
				if (prices[i] > max) {
					max = prices[i];
					max_idx = i;
				}
			}
			if (sc.hasNextLine()) sc.nextLine();
			
			long result = 0;
			int j = 0;
			
			while (j < N) {
				for (int i = j; i < max_idx; i++) {
					result += max - prices[i];
				}
				
				j = max_idx+1;
				max = 0;
				
				for (int i = j; i < N; i++) {
					if (prices[i] >= max) {
						max = prices[i];
						max_idx = i;
					}
				}
			}
			System.out.printf("#%d %d", t, result);
			System.out.println();
		}
		
		
		sc.close();
	}

}

/*
최대값을 구함 그 인덱스 전까지는 전부 그떄팜
그 다음 인덱스 시작

*/