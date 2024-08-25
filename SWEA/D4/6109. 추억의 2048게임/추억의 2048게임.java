import java.util.*;

public class Solution {
	static int N, grid[][];
	static String dir;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			dir = sc.next();
			
			grid = new int[N][N];
			for (int i = 0; i < N*N; i++) grid[i/N][i%N] = sc.nextInt();
			
			// 시작점, 방향, merge, swap 함수
			// 방향은 -> 0, 아래는 1
			switch (dir) {
				case "left" : moveLeft(); break;
				case "right" : moveRight(); break;
				case "up" : moveUp(); break;
				case "down" : moveDown(); break;
			}
			
			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(grid[i][j] + " ");
				}
				System.out.println();
			}
		}	
		sc.close();
	}

	private static void moveUp() {
		for (int i = 0; i < N; i++) {
			int r = 0, c = i;
			int nr = r+1;
			
			while (r < N) {
				nr = r+1;
				if (grid[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					r++;
					continue;
				}
				
				while (nr < N && grid[nr][c] == 0) nr++;
				
				if (nr < N) {
					// 바꿀수 있는 경우에만
					if (grid[r][c] == grid[nr][c]) { // merge
						grid[r][c] = grid[r][c] << 1;
						grid[nr][c] = 0;
					}
				}
				
				int swapR = r;
				while (swapR > 0 && grid[swapR-1][c] == 0) {
					swap(swapR, c, swapR-1, c);
					swapR--;
				}
				r++;
			}
		}
	}
	
	private static void moveDown() {
		for (int i = 0; i < N; i++) {
			int r = N-1, c = i;
			int nr = r-1;
			
			while (r >= 0) {
				nr = r-1;
				if (grid[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					r--;
					continue;
				}
				
				while (nr >= 0 && grid[nr][c] == 0) nr--;
				
				if (nr >= 0) {
					// 바꿀수 있는 경우에만
					if (grid[r][c] == grid[nr][c]) { // merge
						grid[r][c] = grid[r][c] << 1;
						grid[nr][c] = 0;
					}
				}
				
				int swapR = r;
				while (swapR < N-1 && grid[swapR+1][c] == 0) {
					swap(swapR, c, swapR+1, c);
					swapR++;
				}
				r--;
			}
		}
	}
	
	private static void moveRight() {
		for (int i = 0; i < N; i++) {
			int r = i, c = N-1;
			int nc = c - 1;
			
			while (c >= 0) {
				nc = c - 1;
				if (grid[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					c--;
					continue;
				}
				
				while (nc >= 0 && grid[r][nc] == 0) nc--;
				
				if (nc >= 0) {
					// 바꿀수 있는 경우에만
					if (grid[r][c] == grid[r][nc]) { // merge
						grid[r][c] = grid[r][c] << 1;
						grid[r][nc] = 0;
					}
				}
				
				int swapC = c;
				while (swapC < N-1 && grid[r][swapC+1] == 0) {
					swap(r, swapC, r, swapC+1);
					swapC++;
				}
				c--;
			}
		}
	}
	
	private static void moveLeft() {
		for (int i = 0; i < N; i++) {
			int r = i, c = 0;
			int nc = c + 1;
			
			while (c < N) {
				nc = c + 1;
				if (grid[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					c++;
					continue;
				}
				
				while (nc < N && grid[r][nc] == 0) nc++;
				
				if (nc < N) {
					// 바꿀수 있는 경우에만
					if (grid[r][c] == grid[r][nc]) { // merge
						grid[r][c] = grid[r][c] << 1;
						grid[r][nc] = 0;
					}
				}
				
				int swapC = c;
				while (swapC > 0 && grid[r][swapC-1] == 0) {
					swap(r, swapC, r, swapC-1);
					swapC--;
				}
				c++;
			}
		}
	}
	

	private static void swap(int sr, int sc, int r, int c) {
		int temp = grid[sr][sc];
		grid[sr][sc] = grid[r][c];
		grid[r][c] = temp;
	}
}