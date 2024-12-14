import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int table[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		final int MOD = 9901;
		table = new int[N+1][3];
		for (int i = 0; i < 3; i++)
			table[1][i] = 1;
		
		for (int i = 2; i <= N; i++) {
			int sum = Arrays.stream(table[i-1]).sum() % MOD;
			int partSum = (table[i-1][0] + table[i-1][1]) % MOD;
			table[i][0] = sum;
			table[i][1] = table[i][2] = partSum;
		}
		
		int answer = Arrays.stream(table[N]).sum() % MOD;
		System.out.println(answer);
		sc.close();
	}
}

