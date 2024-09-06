import java.util.*;

public class Main {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int [] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = sc.nextInt();
        int maxLen = Arrays.stream(nums).max().getAsInt();
        
        long left = 1, right = maxLen, mid = 0;
        long result = 0;
        while (left <= right) {
        	mid = (left + right) / 2;
        	
        	long cnt = 0;
        	for (long n : nums) cnt += n / mid;
        	
        	if (cnt >= K) {
        		result = Math.max(result, mid);
        		left = mid + 1;
        	}
        	
        	else right = mid - 1;
        }
        System.out.println(result);
        sc.close();
    }
}

