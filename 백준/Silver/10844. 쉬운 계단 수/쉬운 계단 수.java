import java.util.*;

public class Main {
	static int N;
	static int table[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		final int NUM_CNT = 10;
		final int MOD = 1000000000;
		table = new int[N+1][NUM_CNT+2];
		for (int j = 2; j <= NUM_CNT; j++)
			table[1][j] = 1;
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= NUM_CNT; j++) {
				table[i][j] = (table[i-1][j-1] + table[i-1][j+1]) % MOD;
			}
		}

		int answer = 0;
		for (int j = 1; j <= NUM_CNT; j++) 
			answer = (answer + table[N][j]) % MOD;
		System.out.println(answer);
		sc.close();
	}
}

