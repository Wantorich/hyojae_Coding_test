import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int memo[], nums[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		memo = new int[N+1];
		nums = new int[N+1];
		for (int i = 1; i <= N; i++)
			nums[i] = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			int max = nums[i];
			for (int k = i-1; k >= i/2; k--) {
				int comb = memo[k] + memo[i-k];
				max = Math.max(max, comb);
			}
			memo[i] = max;
		}
		
		System.out.println(memo[N]);
		sc.close();
	}
}

