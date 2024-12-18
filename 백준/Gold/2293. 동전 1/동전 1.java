import java.util.*;

public class Main {
	static int N, K, memo[][];
	static Integer coins[];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		coins = new Integer[N];
		memo = new int[N+1][K+1];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		
		for (int i = 0; i < N; i++) {
			int value = sc.nextInt();
			coins[i] = value;
		}
		
		Arrays.sort(coins, Comparator.reverseOrder());
		
		answer = solve(0, K);
		System.out.println(answer);
//		System.out.println(Arrays.toString(memo));
		
		sc.close();
	}

	private static int solve(int index, int number) {
		if (number == 0) return 1;
		if (index == N) return 0;
		if (memo[index][number] != -1) 
			return memo[index][number];
		
		int result = 0;
		
		for (int count = 0;; count++) {
			int subtract = count * coins[index];
			if (subtract > number) break;
			int nextVal = number - subtract;
			result += solve(index+1, nextVal);
		}
		
		return memo[index][number] = result;
	}
}

