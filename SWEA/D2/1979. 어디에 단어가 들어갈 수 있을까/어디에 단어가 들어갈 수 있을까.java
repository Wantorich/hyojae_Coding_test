
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		sc.nextLine();
		
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			sc.nextLine();
			
			int [][] grid = new int [N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = sc.nextInt();
				}
				sc.nextLine();
			}
			
			int result = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] == 1 && (j-1 < 0 || grid[i][j-1] == 0) && j+K-1 < N) { // 가로로 세기
						boolean cond = true;
						for (int l = j+1; l < j+K; l++) { // K-1개 검사
							if (grid[i][l] == 0) {
								cond = false;
//								break;
							}
						}
						if (j+K < N && grid[i][j+K] == 1) { // 1이 4개 이상
							cond = false;
						}
						if (cond) {
							result += 1;
						}
					}
					
					
					if (grid[i][j] == 1 && (i-1 < 0 || grid[i-1][j] == 0) && i+K-1 < N) { // 세로로 세기
						boolean cond = true;
						for (int l = i+1; l < i+K; l++) { // K-1개 검사
							if (grid[l][j] == 0) {
								cond = false;
								break;
							}
						}
						if (i+K < N && grid[i+K][j] == 1) { // 1이 4개 이상
							cond = false;
						}
						if (cond) {
							result += 1;
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

/*

*/