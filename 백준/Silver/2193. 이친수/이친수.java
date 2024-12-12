import java.util.*;
import java.io.*;

public class Main {
	static long[] memo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		memo = new long[N+1];
		Arrays.fill(memo, -1);
		memo[0] = 0; memo[1] = 1;
		System.out.println(pn(N));
		sc.close();
	}

	private static long pn(int k) {
		if (memo[k] != -1) return memo[k];
		return memo[k] = pn(k-1) + pn(k-2);
	}
}

