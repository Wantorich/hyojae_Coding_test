import java.util.*;

public class Main {
	static int N, M;
	static long memo[][];
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 1; t <= tc; t++) {
        	N = sc.nextInt();
        	M = sc.nextInt();
        	memo = new long[M+1][N+1];
        	System.out.println(dp(M, N));
        }
        sc.close();
    }

	private static long dp(int n, int c) {
		if (n < c) return 0;
		if (n == c || c == 0) return memo[n][c] = 1;
		if (memo[n][c] != 0) return memo[n][c];
		return memo[n][c] = dp(n-1, c-1) + dp(n-1, c);
	}
}