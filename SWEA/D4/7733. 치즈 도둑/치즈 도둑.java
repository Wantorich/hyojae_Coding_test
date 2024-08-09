import java.util.*;
import java.io.*;

class Point {
	int r, c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return String.format("Point [r=%s, c=%s]", r, c);
	}
}

public class Solution {
	static int N, max;
	static int[][] grid;
	static boolean[][] v;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			grid = new int[N][N];
			v = new boolean[N][N];
			max = Integer.MIN_VALUE;
			List<Point>[] pList = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				pList[i] = new ArrayList<Point>();
			}

			for (int i = 0; i < N; i++) {
				String[] chars = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(chars[j]);
					pList[grid[i][j]].add(new Point(i, j));
				}
			}
			
			int time = 0;
			while (++time <= 100) {
				for (Point p : pList[time]) {
					v[p.r][p.c] = true; 
					grid[p.r][p.c] = -1; 
				}
				
				v = new boolean[N][N];
				
				int cnt = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!v[i][j] && grid[i][j] != -1) {
							v[i][j] = true;
							dfs(i, j);
							cnt++;
						}
					}
				}
				max = Math.max(max, cnt);
			}

			// 모든 격자에서 출발점을 바꾸면서 경우의 수 탐색
			max = max == 0 ? 1 : max;
			System.out.printf("#%d %d\n", t, max);
		}
	}


	private static void dfs(int r, int c) {
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc] || grid[nr][nc] == -1)
				continue;
			
			v[nr][nc] = true;
			dfs(nr, nc);
		}
	}


	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
}