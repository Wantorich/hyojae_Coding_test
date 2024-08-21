import java.util.*;

public class Main {
	static int N, grid[][], result = Integer.MIN_VALUE;
	static String dir;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int test_case = sc.nextInt();

		N = sc.nextInt();

		grid = new int[N][N];
		for (int i = 0; i < N * N; i++)
			grid[i / N][i % N] = sc.nextInt();


		recursive(0, grid);

		System.out.println(result);
		sc.close();
	}
	
	private static int[][] cloneArr(int [][] src) {
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++)
			tmp[i] = src[i].clone();
		return tmp;
	}

	private static void recursive(int k, int[][] map) {
		
		if (k == 6) {
//			System.out.println("---result---");
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			return;
		}
		
		int max = getMax(map);
		result = Math.max(result, max);
		
		recursive(k + 1, moveUp(cloneArr(map)));
		recursive(k + 1, moveDown(cloneArr(map)));
		recursive(k + 1, moveLeft(cloneArr(map)));
		recursive(k + 1, moveRight(cloneArr(map)));
		
	}

	private static int getMax(int[][] map) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}

		return max;
	}

	private static int[][] moveUp(int[][] map) {

		for (int i = 0; i < N; i++) {
			int r = 0, c = i;
			int nr = r + 1;

			while (r < N) {
				nr = r + 1;
				if (map[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					r++;
					continue;
				}

				while (nr < N && map[nr][c] == 0)
					nr++;

				if (nr < N) {
					// 바꿀수 있는 경우에만
					if (map[r][c] == map[nr][c]) { // merge
						map[r][c] = map[r][c] << 1;
						map[nr][c] = 0;
					}
				}

				int swapR = r;
				while (swapR > 0 && map[swapR - 1][c] == 0) {
					swap(swapR, c, swapR - 1, c, map);
					swapR--;
				}
				r++;
			}
		}

		return map;
	}

	private static int[][] moveDown(int[][] map) {

		for (int i = 0; i < N; i++) {
			int r = N - 1, c = i;
			int nr = r - 1;

			while (r >= 0) {
				nr = r - 1;
				if (map[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					r--;
					continue;
				}

				while (nr >= 0 && map[nr][c] == 0)
					nr--;

				if (nr >= 0) {
					// 바꿀수 있는 경우에만
					if (map[r][c] == map[nr][c]) { // merge
						map[r][c] = map[r][c] << 1;
						map[nr][c] = 0;
					}
				}

				int swapR = r;
				while (swapR < N - 1 && map[swapR + 1][c] == 0) {
					swap(swapR, c, swapR + 1, c, map);
					swapR++;
				}
				r--;
			}
		}

		return map;
	}

	private static int[][] moveRight(int[][] map) {

		for (int i = 0; i < N; i++) {
			int r = i, c = N - 1;
			int nc = c - 1;

			while (c >= 0) {
				nc = c - 1;
				if (map[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					c--;
					continue;
				}

				while (nc >= 0 && map[r][nc] == 0)
					nc--;

				if (nc >= 0) {
					// 바꿀수 있는 경우에만
					if (map[r][c] == map[r][nc]) { // merge
						map[r][c] = map[r][c] << 1;
						map[r][nc] = 0;
					}
				}

				int swapC = c;
				while (swapC < N - 1 && map[r][swapC + 1] == 0) {
					swap(r, swapC, r, swapC + 1, map);
					swapC++;
				}
				c--;
			}
		}

		return map;
	}

	private static int[][] moveLeft(int[][] map) {

		for (int i = 0; i < N; i++) {
			int r = i, c = 0;
			int nc = c + 1;

			while (c < N) {
				nc = c + 1;
				if (map[r][c] == 0) { // 현재 값이 0이면 다음 값으로 넘김
					c++;
					continue;
				}

				while (nc < N && map[r][nc] == 0)
					nc++;

				if (nc < N) {
					// 바꿀수 있는 경우에만
					if (map[r][c] == map[r][nc]) { // merge
						map[r][c] = map[r][c] << 1;
						map[r][nc] = 0;
					}
				}

				int swapC = c;
				while (swapC > 0 && map[r][swapC - 1] == 0) {
					swap(r, swapC, r, swapC - 1, map);
					swapC--;
				}
				c++;
			}
		}

		return map;
	}

	private static void swap(int sr, int sc, int r, int c, int[][] map) {
		int temp = map[sr][sc];
		map[sr][sc] = map[r][c];
		map[r][c] = temp;
	}
}