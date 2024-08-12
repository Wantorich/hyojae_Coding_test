import java.util.*;
import java.io.*;

class Point {
	int r, c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int R, C, cnt;
	static int[][] grid;
	static boolean[][] v;
	static Queue<Point> q = new ArrayDeque<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			if (R == 0 && C == 0) break;
			
			grid = new int[R][C];
			v = new boolean[R][C];
			cnt = 0;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] == 1) q.offer(new Point(i,j));
				}
			}
			
			// 1은 땅, 0은 바다 -> 섬 개수 세기
			while (!q.isEmpty()) {
				Point p = q.poll();
				if (v[p.r][p.c]) continue;
				
				v[p.r][p.c] = true; 
				dfs(p.r, p.c);
				cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c) {
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || v[nr][nc] || grid[nr][nc] == 0) continue;
			
			v[nr][nc] = true;
			dfs(nr, nc);
		}
	}

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

}