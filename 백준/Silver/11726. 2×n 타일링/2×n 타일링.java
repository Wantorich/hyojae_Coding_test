import java.util.*;

public class Main {
	static int N;
	static int memo[];
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        memo = new int[1001];
        memo[1] = 1;
        memo[2] = 2;
        dp();
        System.out.println(memo[N]);
        sc.close();
    }

	private static void dp() {
		for (int k = 3; k <= N; k++) {
			memo[k] = (memo[k-1] + memo[k-2]) % 10007;
		}
	}
}