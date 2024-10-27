import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = sc.nextInt();
        
        int[] lis = new int[N];
        Arrays.fill(lis, 1);
        
        for (int i = 1; i < N; i++) {
        	for (int j = 0; j < i; j++) {
        		if (nums[j] < nums[i])
        			lis[i] = Math.max(lis[j] + 1, lis[i]);
        	}
        }
        System.out.println(Arrays.stream(lis).max().getAsInt());
        sc.close();
    }
}