import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int [][] grid = new int[N][M];
			
			int win_cnt = 0, max_correct = 0;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) {
					grid[i][j] = sc.nextInt();
					sum += grid[i][j];
				}
				if (sum > max_correct) {
					max_correct = sum;
					win_cnt = 1;
				}
				else if (sum == max_correct) {
					win_cnt++;
				}
			}
			
			System.out.printf("#%d %d %d", t, win_cnt, max_correct);
			System.out.println();
		}
		
		sc.close();
	}
	
}

/*


*/