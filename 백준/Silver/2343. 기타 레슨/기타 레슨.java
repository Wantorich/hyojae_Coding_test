import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long [] nums = new long[N+2];
        long [] lengths = new long[M];
        int cnt = 0;
        
        for (int i = 1; i <= N; i++) nums[i] = sc.nextInt();
        long maxSize = Arrays.stream(nums).sum();
        long result = maxSize;
        
        long left = 1, right = maxSize, mid = 0, sum = 0;
        O:while (left <= right) {
        	mid = (left + right) / 2;
        	sum = 0;
        	for (int i = 1; i <= N+1; i++) {
        		sum += nums[i];
        		if (sum > mid) {
        			lengths[cnt++] = sum - nums[i];
        			sum = nums[i];
        		}
        		
        		if (cnt == M) {
					// 크기가 너무 작음
					left = mid + 1;
					cnt = 0;
					continue O;
        		}
        	}
        	
        	long value = Arrays.stream(lengths).max().getAsLong();
        	if (value <= mid) {
        		result = Math.min(result, mid);
        		right = mid - 1;
        	} else {
        		// mid 범위보다 큰 수를 담았음
        		left = mid + 1;
        	}
        	cnt = 0;
        }
        
        System.out.println(result);
        sc.close();
    }
}