import java.util.*;

public class Solution {
	static int N, B, result;
	static int[] heights;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			B = sc.nextInt();
			
			heights = new int[N];
			for (int i = 0; i < N; i++) heights[i] = sc.nextInt();
			
			result = Integer.MAX_VALUE;
			recursive(0, new boolean[N]);
			System.out.printf("#%d %d\n", t, result);
		}	
		sc.close();
	}

	private static void recursive(int k, boolean[] sel) {
		if (k == sel.length) {
			int sum = 0;
			for (int i = 0; i < sel.length; i++) {
				sum += sel[i] ? heights[i] : 0;
			}
			
			// 차이 확인
            if (sum >= B)
				result = Math.min(result, Math.abs(sum - B));
			return;
		}
		
		sel[k] = true;
		recursive(k+1, sel);
		
		sel[k] = false;
		recursive(k+1, sel);
	}
}