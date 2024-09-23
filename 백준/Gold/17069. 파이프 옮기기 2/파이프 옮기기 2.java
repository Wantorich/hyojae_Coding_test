import java.util.*;

public class Main {
	static int N, grid[][], result;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) 
        	for (int j = 1; j <= N; j++) grid[i][j] = sc.nextInt();
        System.out.println(dp());
        sc.close();
    }

	private static long dp() {
		long dp[][][] = new long[N+1][N+1][3];
		dp[1][2][0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (grid[i][j] == 1) continue;
				if (grid[i][j-1] == 0)
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				if (grid[i-1][j-1] == 0 && grid[i][j-1] == 0 && grid[i-1][j] == 0)
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				if (grid[i-1][j] == 0)
					dp[i][j][2] = dp[i-1][j][2] + dp[i-1][j][1];
			}
		}
		return dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
	}
}

