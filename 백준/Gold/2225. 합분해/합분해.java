import java.util.*;

public class Main {
	static int N;
	static long table[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int MOD = 1000000000;
		N = sc.nextInt();
		int K = sc.nextInt();
		
		table = new long[201][201];
		Arrays.fill(table[0], 1);
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				table[i][j] = (table[i-1][j] + table[i][j-1]) % MOD;
			}
		}
		
		System.out.println(table[N][K]);
		sc.close();
	}
}

