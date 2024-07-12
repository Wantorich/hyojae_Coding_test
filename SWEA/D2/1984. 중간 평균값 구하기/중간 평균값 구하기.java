
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		final int INPUT_NUM = 10;
		int [] nums; int sum; int result; int min; int max;
		int test_case = sc.nextInt();
		sc.nextLine();
		
		for (int T = 1; T <= test_case; T++) {
			sum =  0;
			min = 10000; max = 0;
			nums = new int[INPUT_NUM];
			
			for (int i = 0; i < INPUT_NUM; i++) {
				nums[i] = sc.nextInt();
				if (nums[i] < min) min = nums[i];
				if (nums[i] > max) max = nums[i];
			}
			if (sc.hasNextLine()) sc.nextLine();
			
			for (int num : nums) {
				if (num != min && num != max) {
					sum += num;
				}
			}
			
			result = (int) Math.round((double) sum / (INPUT_NUM-2)); 
			
			
			System.out.printf("#%d %d", T, result);
			System.out.println();
		}
	}

}

/*
 

*/