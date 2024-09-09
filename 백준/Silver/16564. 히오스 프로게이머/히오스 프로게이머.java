import java.util.*;

public class Main {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int [] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = sc.nextInt();
        
        long left = 1, right = Integer.MAX_VALUE, mid = 0;
        long result = 0;
        while (left <= right) {
        	mid = (left + right) / 2;
        	
        	long cnt = 0;
        	for (int i = 0; i < N; i++) {
        		if (mid > nums[i]) cnt += mid - nums[i];
        	}
        	
        	if (cnt <= K) {
        		left = mid + 1;
        		result = Math.max(result, mid);
        	}
        	else right = mid - 1;
        }
        
        System.out.println(result);
        sc.close();
    }
}
