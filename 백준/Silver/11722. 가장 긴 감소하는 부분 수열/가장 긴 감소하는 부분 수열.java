import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] nums = new int[N+1];
        for (int i = 1; i < nums.length; i++) 
            nums[i] = sc.nextInt();

        int [] minArr = new int[N+1];
        int len = 0;
        O:for (int i = 1; i < nums.length; i++) {
        	for (int j = 0; j < len; j++) {
        		if (minArr[j] <= nums[i]) {
        			minArr[j] = nums[i];
        			continue O;
        		}
        	}
    		minArr[len++] = nums[i];
        }
        System.out.println(len);
        sc.close();
    }
}