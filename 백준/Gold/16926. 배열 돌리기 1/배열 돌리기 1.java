import java.util.*;

public class Main {
	static int N, M, R, grid[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();

		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				grid[i][j] = sc.nextInt();
		}
		
		int wholeRotate = Math.min(N, M) / 2;
		for (int rotate = 0; rotate < R; rotate++) {
			int r = 0, c = 0;
			for (int t = 0; t < wholeRotate; t++) {
				// 전체 회전
				// 시작점은 0,0 -> 1,1 / r+1, c+1
				int initialVal = grid[r][c];
				int colEnd = M - t - 1;
				int rowEnd = N - t - 1;
				
				// 오른쪽으로 돌면서 다음값을 현재값에 저장함
				for (int j = t; j < colEnd; j++) {
					grid[r][j] = grid[r][j+1];
				}
				
				// 아래방향
				for (int i = t; i < rowEnd; i++) {
					grid[i][colEnd] = grid[i+1][colEnd];
				}
				
				// 왼쪽 방향
				for (int j = colEnd; j > t; j--) {
					grid[rowEnd][j] = grid[rowEnd][j-1];
				}
				
				// 위쪽 방향
				for (int i = rowEnd; i > t; i--) {
					grid[i][t] = grid[i-1][t];
				}
				
				// 처음 저장한 값 넣어줌
				grid[r+1][c] = initialVal;
				
				// 두번째 회전은 N-2, M-2 격자임
				r++;
				c++;
			}
			
		}
		for (int [] row : grid) {
			for (int val : row) System.out.print(val + " ");
			System.out.println();
		}
		sc.close();
	}
}