import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	sc.nextLine();
    	int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int[] dp = new int[N];
    	dp[0] = nums[0];
    	for (int i = 1; i < nums.length; i++) {
    		dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
    	}
    	int answer = Arrays.stream(dp).max().getAsInt();
    	System.out.println(answer);
    	sc.close();
    }
}