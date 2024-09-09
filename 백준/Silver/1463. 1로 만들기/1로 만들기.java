import java.util.*;

public class Main {
	static int result = Integer.MAX_VALUE;
	static int callCnt;
	static int memo[];
	static int X;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();
        memo = new int[X+1];
        
        dp();
        sc.close();
    }

	private static void dp() {
		int dp[] = new int[Math.max(X+1, 4)];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 1; dp[2] = 2; dp[3] = 2;
		for (int n = 4; n <= X; n++) {
			if (n % 3 == 0) dp[n] = Math.min(dp[n], dp[n/3] + 1);
			if (n % 2 == 0) dp[n] = Math.min(dp[n], dp[n/2] + 1);
			dp[n] = Math.min(dp[n], dp[n-1] + 1);
		}
		
//		for (int n : dp) {
//			System.out.print(n + " ");
//		}
//		System.out.println();
		
		System.out.println(dp[X]-1);
	}

	private static int cal(int x, int cnt) {
		callCnt++;
		
		if (x == 1) {
			result = Math.min(result, cnt);
			return 0;
		}

		if (memo[x] != 0) return memo[x];
		
		if (x % 3 == 0) return memo[x/3] = cal(x / 3, cnt+1);
		if (x % 2 == 0) return memo[x/2] = cal(x / 2, cnt+1);
		return memo[x-1] = cal(x-1, cnt+1);
	}
}

// 선택할 수 있는 경우의 수는 +1, *2 , *3
/*
1 -> 0
2 -> 1
3 -> 1
4 -> 2
5 -> 3
6 -> 2

if 3으로 나누어떨어지면 f(n) = f(n/3) + 1
2 -> f(n) = f(n/2) + 1;
else -> f(n) = f(n-1) + 1;
*/