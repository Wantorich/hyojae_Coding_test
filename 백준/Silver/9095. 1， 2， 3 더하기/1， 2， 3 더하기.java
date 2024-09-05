import java.util.*;

public class Main {
	static int X;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 1; t <= tc; t++) {
        	X = sc.nextInt();
        	memo = new int[12];
        	dp2(X);
        	System.out.println(memo[X]);
        }
        
        sc.close();
    }

	private static void dp(int x) {
		int [] memo = new int[12];
		memo[1] = 1; memo[2] = 2; memo[3] = 4;
		for (int n = 4; n <= x; n++) {
			memo[n] = memo[n-1] + memo[n-2] + memo[n-3];
		}
		System.out.println(memo[x]);
	}
	
	static int [] memo;
	
	static int dp2(int x) {
		if (x < 0) return 0;
		else if (x <= 1) return memo[x] = 1;
		if (memo[x] != 0) return memo[x];
		
		return memo[x] = Math.max(memo[x], dp2(x-3) + dp2(x-2) + dp2(x-1));
	}
}

/*
1 -> (1) -> 1
2 -> (1, 1), (2) -> 2
3 -> (1, 1, 1), (1, 2), (2, 1), (3) -> 4
4 -> (1, 1, 1, 1), (1, 2, 1), (2, 1, 1), (1, 1, 2), (1, 3), (3, 1), (2, 2) -> 7
5 -> (1, 1, 1, 1, 1), (2, 1, 1, 1), (1, 2, 1, 1), (1, 1, 2, 1), (1, 1, 1, 2), (1, 1, 3)
(3, 1, 1), (1, 3, 1), (1, 2, 2), (2, 1, 2), (2, 2, 1), (3, 2), (2, 3) -> 13
*/