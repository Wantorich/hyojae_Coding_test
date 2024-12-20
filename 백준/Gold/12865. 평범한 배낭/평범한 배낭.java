import java.util.*;

public class Main {
	static int N, K, answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); K = sc.nextInt();
		List<int[]> products = new ArrayList<int[]>();
		products.add(new int[] {0, 0});
		for (int i = 0; i < N; i++) {
			int weight = sc.nextInt();
			int value = sc.nextInt();
			products.add(new int[] {weight, value});
		}
		
		int[][] dp = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			int[] product = products.get(i);
			int weight = product[0];
			int value = product[1];
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i-1][j];
				if (j - weight < 0) continue;
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j-weight] + value);
			}
		}
		
		answer = Arrays.stream(dp[N]).max().getAsInt();
		System.out.println(answer);
		sc.close();
	}
}
