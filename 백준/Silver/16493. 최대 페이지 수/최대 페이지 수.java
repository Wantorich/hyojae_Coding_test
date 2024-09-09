import java.util.*;

public class Main {
	static int days[], pages[], N, M, result = Integer.MIN_VALUE;
	static int [][]memo;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        
        days = new int[M+1];
        pages = new int[M+1];
        memo = new int[M+1][N+1];
        
        for (int i = 1; i <= M; i++) {
        	days[i] = sc.nextInt();
        	pages[i] = sc.nextInt();
        }
        
        System.out.println(recur(M, N));
//        for (int [] row : memo) System.out.println(Arrays.toString(row));
        sc.close();
    }

	private static int recur(int k, int day) {
		if (day < 0) return Integer.MIN_VALUE;
		if (k == 0) return 0;
		if (memo[k][day] != 0) return memo[k][day];
		return memo[k][day] = Math.max(recur(k-1, day-days[k]) + pages[k], recur(k-1, day));
	}
}

