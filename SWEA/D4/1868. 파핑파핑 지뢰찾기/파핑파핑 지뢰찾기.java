import java.util.*;

public class Solution {
	static int N, map[][], result; 
	static char grid[][];
	static int [] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int [] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			grid = new char[N][N];
			map = new int[N][N];
			result = 0;
			
			sc.nextLine();
			for (int i = 0; i < N; i++) {
				String line = sc.nextLine();
				for (int j = 0; j < N; j++) {
					grid[i][j] = line.charAt(j);
				}
			}
			
			// 팔방탐색을 하면서 주변에 지뢰가 몇개있는지 저장함
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					char c = grid[i][j];
					if (c == '*') {
						// 지뢰이면 팔방탐색
						markBomb(i, j);
					}
				}
			}
			
//			for (int [] row : map) {
//				System.out.println(Arrays.toString(row));
//			}
			
			// 다시 보면서 .이고 지뢰가 주변에 없는 곳 먼저 터트림
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					char c = grid[i][j];
					if (c == '.' && map[i][j] == 0) {
						explodeBomb(i, j);
						result++;
					}
				}
			}
			
//			for (char [] row : grid) {
//				System.out.println(Arrays.toString(row));
//			}
			
			// 남은 칸 중 지뢰가 안터진 칸은 일일히 터트려줘야함
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					char c = grid[i][j];
					if (c == '.') {
						result++;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}

	private static void explodeBomb(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		grid[r][c] = '*'; // 폭탄 마킹
		
		while (!q.isEmpty()) {
			int [] p = q.poll();
			
			for (int i = 0; i < dr.length; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || grid[nr][nc] == '*') continue;
				
				
				if (grid[nr][nc] == '.' && map[nr][nc] == 0) {
					// 인접한 구간에도 폭탄이 주변에 없는 곳이 있다면
					q.offer(new int[] {nr, nc});
				}
                grid[nr][nc] = '*';
			}
		}
		
	}

	private static void markBomb(int r, int c) {
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || grid[nr][nc] == '*') continue;
			map[nr][nc]++;
		}
	}
}