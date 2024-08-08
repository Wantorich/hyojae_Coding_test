import java.util.*;
import java.io.*;

class Point {
	int r, c, d;

	Point(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}

	@Override
	public String toString() {
		return String.format("Point [r=%s, c=%s, d=%s]", r, c, d);
	}
}

public class Solution {
	static int N, max = Integer.MIN_VALUE;
	static Point end_point = new Point(0, 0, 0);
	static int[][] grid;
	static boolean[][] v;
	static Queue<Point> q;
	static List<Point> result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			grid = new int[N][N];
			v = new boolean[N][N];
			max = Integer.MIN_VALUE;
			q = new ArrayDeque<Point>();
			result = new ArrayList<Point>();

			for (int i = 0; i < N; i++) {
				String[] chars = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(chars[j]);
				}
			}

			// 모든 격자에서 출발점을 바꾸면서 경우의 수 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					v = new boolean[N][N];
					v[i][j] = true;
					bfs(i, j);
//					result = Math.max(result, max);
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (Point p : result) {
//				System.out.println(p);
				if (grid[p.r][p.c] < min) {
					min = grid[p.r][p.c];
					end_point.r = p.r;
					end_point.c = p.c;
				}
			}

			System.out.printf("#%d %d %d\n", t, grid[end_point.r][end_point.c], max);
		}
	}

	private static void bfs(int r, int c) {
		q.offer(new Point(r, c, 1));

		while (!q.isEmpty()) {
			Point p = q.poll();

			if (p.d > max) {
				max = p.d;
				result.clear();
				result.add(new Point(r, c, p.d));
			} else if (p.d == max) {
				result.add(new Point(r, c, p.d));
			}

			for (int i = 0; i < dr.length; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc] || (grid[nr][nc] - grid[p.r][p.c] != 1))
					continue;

				v[nr][nc] = true;
				q.offer(new Point(nr, nc, p.d + 1));
			}
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
}