import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, distance[][];
	static char grid[][];
	static Queue<int[]> seaQ;

	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			String[] tmp = br.readLine().split(" ");
			N = Integer.parseInt(tmp[0]);
			M = Integer.parseInt(tmp[1]);
			grid = new char[N][M];
			distance = new int[N][M];
			int result = 0;

			for (int[] row : distance) {
				Arrays.fill(row, Integer.MAX_VALUE);
			}

			seaQ = new LinkedList<int[]>();
//			sc.nextLine();
			for (int i = 0; i < N; i++) {
				grid[i] = br.readLine().toCharArray();
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j] == 'W') {
						seaQ.offer(new int[] { i, j });
						distance[i][j] = 0;
					}
						
				}
			}
			
			int firstSea[] = seaQ.peek();
			bfs(firstSea[0], firstSea[1]);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					result += distance[i][j];
				}
			}

			System.out.printf("#%d %d\n", t, result);

		}
//		sc.close();
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void bfs(int r, int c) { // 물에서 땅들까지의 거리들을 업데이트함
		while (!seaQ.isEmpty()) {
			int[] pos = seaQ.poll();

			for (int i = 0; i < dr.length; i++) {
				int nr = pos[0] + dr[i];
				int nc = pos[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (grid[nr][nc] == 'L') {
					// 거리 업데이트
					if (distance[pos[0]][pos[1]] + 1 < distance[nr][nc]) {
						distance[nr][nc] = distance[pos[0]][pos[1]] + 1;
						seaQ.offer(new int[] { nr, nc });
					}
				}
			}
		}
	}
}