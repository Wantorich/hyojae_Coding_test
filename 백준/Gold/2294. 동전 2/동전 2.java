import java.util.*;

public class Main {
	static int N, K;
	static long memo[];
	static int coins[];
	static long answer;
	static int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		coins = new int[N];
		memo = new long[K+1];
		Arrays.fill(memo, INF);
		
		for (int i = 0; i < N; i++) {
			int value = sc.nextInt();
			coins[i] = value;
		}
		
		coins = Arrays.stream(coins)
				.distinct()
				.filter(coin -> coin <= K)
				.toArray();
		Arrays.stream(coins).forEach(coin -> memo[coin] = 1);
//		System.out.println(Arrays.toString(coins));
		
		answer = solve(K);
		answer = answer >= INF ? -1 : answer;
		System.out.println(answer);
//		System.out.println(Arrays.toString(memo));
		sc.close();
	}

	private static long solve(int k) {
		if (k <= 0) return INF;
		
		if (memo[k] != INF)
			return memo[k];
		
		long result = INF;
		for (int i = 0; i < coins.length; i++) {
			result = Math.min(result, solve(k - coins[i]));
		}
		
		return memo[k] = result + 1;
	}
}

