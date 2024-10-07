import java.util.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
        	int N = sc.nextInt();
        	int [] nums = new int[N+1];
        	for (int i = 1; i < nums.length; i++) 
        		nums[i] = sc.nextInt();
        	int[] lis = new int[N+1];
        	Arrays.fill(lis, 1);
        	for (int i = 1; i < lis.length; i++) {
        		for (int j = 1; j < i; j++) {
        			if (nums[j] <= nums[i] && lis[j] + 1 > lis[i]) {
        				lis[i] = lis[j] + 1;
        			}
        		}
        	}
        	int max = Arrays.stream(lis).max().getAsInt();
        	sb.append("#" + t + " " + max + "\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}