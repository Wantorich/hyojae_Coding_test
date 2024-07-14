import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int [] nums; int max_appear; int result;
		final int STUDENT_NUMBER = 1000;
		final int SCORE_RANGE = 100;
		
		int test_case = sc.nextInt();
		sc.nextLine();
		
		for (int T = 1; T <= test_case; T++) {
			sc.nextInt();
			if (sc.hasNextLine()) sc.nextLine();
			
			nums = new int[SCORE_RANGE+1];
			for (int i = 0; i < STUDENT_NUMBER; i++) {
				nums[sc.nextInt()] += 1;
			}
			if (sc.hasNextLine()) sc.nextLine();
			
			max_appear = 0;
			result = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] >= max_appear) {
					max_appear = nums[i];
					result = i;
				}
			}
			
			System.out.printf("#%d %d", T, result);
			System.out.println();
		}
	}

}

/*
 1000개 배열을 만들고 
 입력값 들어올때마다 해당 값 +1해줌
 그리고 배열 돌면서 최대값 찾음
 

*/