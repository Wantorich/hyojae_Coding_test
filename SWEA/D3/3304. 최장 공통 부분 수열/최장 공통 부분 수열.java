import java.util.*;

public class Solution {
	static char[] charArr1, charArr2;
	static int memo[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			charArr1 = sc.next().toCharArray();
			charArr2 = sc.next().toCharArray();
			memo = new int[charArr1.length][charArr2.length];
//			int result = recur(charArr1.length-1, charArr2.length-1);
			int result = dp();
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}

	private static int recur(int m, int n) {
		if (m < 0 || n < 0) return 0;
		if (memo[m][n] != 0) return memo[m][n];
		
		if (charArr1[m] == charArr2[n]) return memo[m][n] = recur(m-1, n-1) + 1;
		else return memo[m][n] = Math.max(recur(m-1, n), recur(m, n-1));
	}
	
	private static int dp() {
		int dp[][] = new int[charArr1.length+1][charArr2.length+1];
		
		for (int i = 1; i <= charArr1.length; i++) {
			for (int j = 1; j <= charArr2.length; j++) {
				if (charArr1[i-1] == charArr2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
//		for (int [] row : dp) System.out.println(Arrays.toString(row));
		return dp[charArr1.length][charArr2.length];
	}
}
