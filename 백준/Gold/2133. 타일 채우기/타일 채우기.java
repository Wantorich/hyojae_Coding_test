import java.util.*;

public class Main {
	static int N;
	static int memo[];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		memo = new int[33];
		int[] subsum = new int[33];
		memo[0] = 1; memo[2] = 3; 
		subsum[0] = 1; subsum[2] = 4;
		
		for (int i = 4; i <= N; i += 2) {
			memo[i] = 2 * subsum[i-4] + 3 * memo[i-2];
			subsum[i] = subsum[i-2] + memo[i];
		}
		
		System.out.println(memo[N]);
//		System.out.println(Arrays.toString(memo));
//		System.out.println(Arrays.toString(subsum));
		sc.close();
	}
}

