import java.util.*;

public class Main {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long M = sc.nextInt();
        int [] trees = new int[N];
        for (int i = 0; i < N; i++) trees[i] = sc.nextInt();
        int maxLen = Arrays.stream(trees).max().getAsInt();
        
        long left = 0, right = maxLen, mid = 0;
        long answer = Integer.MIN_VALUE;
        while (left <= right) {
        	mid = (left + right) / 2;
        	
        	long result = 0;
        	for (int i = 0; i < N; i++) {
        		long remain = trees[i] - mid;
        		if (remain > 0) result += remain;
        	}
        	
        	if (result >= M) {
        		answer = Math.max(answer, mid);
        		left = mid + 1;
        	}
        	else right = mid - 1;
        }
        
        System.out.println(answer);
        sc.close();
    }
}
