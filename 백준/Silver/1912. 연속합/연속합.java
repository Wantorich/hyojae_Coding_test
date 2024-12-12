import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] dp, nums;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		dp = new int[N+1];
		for (int i = nums.length-1; i >= 0; i--) {
			dp[i] = Math.max(nums[i], nums[i] + dp[i+1]);
		}
		
		int answer = Arrays.stream(dp).limit(N).max().getAsInt();
		System.out.println(answer);
		sc.close();
	}
}

