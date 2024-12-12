import java.util.*;
import java.io.*;

public class Main {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int NUM_CNT = 10;
		int MOD = 10007;
		int[][] table = new int[N+1][NUM_CNT+1];
		table[1][10] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = NUM_CNT-1; j >= 0; j--) {
				table[i][j] = (table[i-1][j] + table[i][j+1]) % MOD;
			}
		}
		
		int answer = Arrays.stream(table[N]).limit(NUM_CNT).sum() % MOD;
		System.out.println(answer);
		sc.close();
	}
}

