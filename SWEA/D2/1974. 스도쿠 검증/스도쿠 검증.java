import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		sc.nextLine();
		int N = 9;
		
		// 가로로 9번, 세로로 9번, 정사각형으로 9번
		
		for (int t = 1; t <= test_case; t++) {
			int [][] grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = sc.nextInt();
				}
				sc.nextLine();
			}
			
			int result = 1;
			
			boolean [] checked = new boolean[N+1];
			OuterLoop : for (int i = 0; i < N; i++) {
				Arrays.fill(checked, false);
				for (int j = 0; j < N; j++) {
					int idx = grid[i][j];
					if (checked[idx]) {
						result = 0;
						break OuterLoop;
					}
					checked[idx] = true;
				}
			}

			OuterLoop : for (int j = 0; j < N; j++) {
				Arrays.fill(checked, false);
				for (int i = 0; i < N; i++) {
					int idx = grid[i][j];
					if (checked[idx]) {
						result = 0;
						break OuterLoop;
					}
					checked[idx] = true;
				}
			}
			
			OuterLoop : for (int i = 0; i < N; i += 3) {
				for (int j = 0; j < N; j += 3) {
					Arrays.fill(checked, false);
					for (int k = i; k < i+3; k++) {
						for (int l = j; l < j+3; l++) {
							int idx = grid[k][l];
							if (checked[idx]) {
								result = 0;
								break OuterLoop;
							}
							checked[idx] = true;
						}
					}
				}
			}
			
			System.out.printf("#%d %d", t, result);
			System.out.println();
		}
		
		sc.close();
	}

}
