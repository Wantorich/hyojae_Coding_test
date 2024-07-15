import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			int [] heights = new int[N];
			for (int i = 0; i < N; i++) heights[i] = sc.nextInt();
			
			int max_up = 0, max_down = 0;
			for (int i = 0; i < N-1; i++) {
				if (heights[i] < heights[i+1]) {
					max_up = Math.max(max_up, heights[i+1] - heights[i]);
				}
				else {
					max_down = Math.max(max_down, heights[i] - heights[i+1]);
				}
			}
			
			System.out.printf("#%d %d %d", t, max_up, max_down);
			System.out.println();
		}
		
		sc.close();
	}
	
}

/*


*/