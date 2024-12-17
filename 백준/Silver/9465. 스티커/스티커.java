import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();

		for (int tc = 0; tc < testCase; tc++) {
			int N = sc.nextInt();
			int[][] stickers = new int[2][N];
			int[][] dp = new int[2][N];
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < N; j++)
					stickers[i][j] = sc.nextInt();
			
			dp[0][0] = stickers[0][0];
			dp[1][0] = stickers[1][0];
			
			if (N == 1) {
				System.out.println(Math.max(dp[0][0], dp[1][0]));
				continue;
			}
			
			dp[0][1] = dp[1][0] + stickers[0][1];
			dp[1][1] = dp[0][0] + stickers[1][1];
			
			// n이 2 이상일때만
			for (int i = 2; i < N; i++) {
				dp[0][i] = getMax(dp[1][i-1], dp[0][i-2], dp[1][i-2]) + stickers[0][i]; 
				dp[1][i] = getMax(dp[0][i-1], dp[0][i-2], dp[1][i-2]) + stickers[1][i]; 
			}
			
			System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));
		}
		sc.close();
	}
	
	static int getMax(int a, int b, int c) {
		int temp = Math.max(a, b);
		return Math.max(temp, c);
	}
}