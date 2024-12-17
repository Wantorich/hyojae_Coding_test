import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] costs = new int[N + 1][3];
		int[][] memo = new int[N + 1][3];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < 3; j++)
				costs[i][j] = sc.nextInt();

		for (int i = 0; i < 3; i++)
			memo[0][i] = costs[0][i];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				memo[i][j] = costs[i][j] 
						+ Math.min(memo[i - 1][(j + 1) % 3], memo[i - 1][(j + 2) % 3]);
			}
		}
		
		System.out.println(Arrays.stream(memo[N-1]).min().getAsInt());
		sc.close();
	}
}
