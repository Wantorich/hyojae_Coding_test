import java.util.*;

public class Main {
	static int X;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 1; t <= tc; t++) {
        	X = sc.nextInt();
        	dp(X);
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

}