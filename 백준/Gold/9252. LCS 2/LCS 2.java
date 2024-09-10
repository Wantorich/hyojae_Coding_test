import java.util.*;

public class Main {
	static char[] charArr1, charArr2;
	static int memo[][];
	static String resultStr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        charArr1 = sc.next().toCharArray();
        charArr2 = sc.next().toCharArray();
        memo = new int[charArr1.length][charArr2.length];
        int result = dp();
        System.out.println(result);
        System.out.println(resultStr);
		sc.close();
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
		
		// 문자열 출력
		StringBuilder sb = new StringBuilder();
		int r = charArr1.length, c = charArr2.length;
		while (dp[r][c] > 0) {
			if (dp[r][c] == dp[r-1][c]) r = r-1;
			else if (dp[r][c] == dp[r][c-1]) c = c-1;
			else if (dp[r-1][c-1] == dp[r][c] - 1) {
				sb.append(charArr1[r-1]);
				r = r-1; c = c-1;
			}
		}
//		System.out.println(sb.reverse().toString());
		resultStr = sb.reverse().toString();
		
		return dp[charArr1.length][charArr2.length];
	}
}
