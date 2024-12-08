import java.util.*;
import java.io.*;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[1002];
		Arrays.fill(dp, -1);
		dp[0] = 0; dp[1] = 1; dp[2] = 3;
		int answer = tile(0, N);
		System.out.println(answer);
		sc.close();
	}

	private static int tile(int a, int b) {
		if (a > b) return 0;
		
		int ret = dp[b-a];
		if (ret != -1) return ret;

		return dp[b-a] = (tile(a+1, b) + tile(a+2, b) * 2) % 10007; 
	}
}