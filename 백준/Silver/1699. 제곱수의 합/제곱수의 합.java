import java.util.*;

public class Main {
	static int [] memo;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        memo = new int[N+1];
//        System.out.println(recur(N));
        System.out.println(dp(N));
        sc.close();
    }

	private static int recur(int n) {
		if (memo[n] != 0) return memo[n];
		
		int sqrt = (int) Math.sqrt(n);
		if (sqrt * sqrt == n) {
			return memo[n] = 1;
		}
		else {
			int min = Integer.MAX_VALUE;
			while (sqrt >= 1) {
				min = Math.min(min, recur(n - sqrt * sqrt) + 1);
				sqrt--;
			}
			return memo[n] = min;
		}
	}
	
	private static int dp(int n) {
		int [] table = new int[n+1];
		Arrays.fill(table, Integer.MAX_VALUE);
		for (int num = 1; num <= n; num++) {
			int sqrt = (int) Math.sqrt(num);
			if (sqrt * sqrt == num) {
				table[num] = 1;
			}
			else {
				int min = Integer.MAX_VALUE;
				while (sqrt >= 1) {
					min = Math.min(min, table[num - sqrt * sqrt] + 1);
					sqrt--;
				}
				table[num] = min;
			}
		}
//		System.out.println(Arrays.toString(table));
		return table[n];
	}
}