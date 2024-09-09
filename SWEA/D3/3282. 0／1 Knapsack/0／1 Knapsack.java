import java.util.*;

public class Solution {
	static int N, K, weights[], values[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			weights = new int[N+1];
			values = new int[N+1];
			memo = new int[N+1][K+1];
			
			
			
			for (int i = 1; i <= N; i++) {
				weights[i] = sc.nextInt();
				values[i] = sc.nextInt();
			}
			
//			System.out.println(recur(1, 0));
//			System.out.println(memoRecur(1, 0));
			System.out.printf("#%d %d\n", t, dp());
//			for (int[] row : memo) {
//				System.out.println(Arrays.toString(row));
//			}
		}
		sc.close();
	}
	
	static int memo[][];
	
	private static int recur(int idx, int weightSum) {
		// 현재 선택할 수 있는것은 전거에서 그냥 오던지, 뽑고 오던지
		if (weightSum > K) return 0;
		if (idx > N) return 0;
		
		if (weightSum + weights[idx] > K) return recur(idx+1, weightSum);
		
		return Math.max(recur(idx+1, weightSum),
				recur(idx+1, weightSum + weights[idx]) +values[idx]);
	}
	
	private static int memoRecur(int idx, int weightSum) {
		// 현재 선택할 수 있는것은 전거에서 그냥 오던지, 뽑고 오던지
		if (weightSum > K) return 0;
		if (idx > N) return 0;
		
		if (memo[idx][weightSum] != 0) return memo[idx][weightSum];
		
		if (weightSum + weights[idx] > K) return memo[idx][weightSum] = recur(idx+1, weightSum);
		
		return memo[idx][weightSum] = Math.max(recur(idx+1, weightSum),
				recur(idx+1, weightSum + weights[idx]) + values[idx]);
	}
	
	private static int dp() {
		int [][] dpTable = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				// 물건을 넣을 수 있으면
				if (weights[i] <= j) dpTable[i][j] = Math.max(dpTable[i-1][j-weights[i]] + values[i], dpTable[i-1][j]);
				else dpTable[i][j] = dpTable[i-1][j];
			}
		}
		
//		for (int [] row : dpTable) System.out.println(Arrays.toString(row));
		return dpTable[N][K];
	}
}
