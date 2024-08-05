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
	static int[][] grid;
	static boolean [][] v;
	static int result = 0, R, C;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		for (int t = 0; t < test_case; t++) {
			String[] line = br.readLine().split(" ");
			C = Integer.parseInt(line[0]);
			R = Integer.parseInt(line[1]);
			int K = Integer.parseInt(line[2]);
			grid = new int[R][C];
			v = new boolean[R][C];
			result = 0;
			Queue<Point> q = new ArrayDeque<Point>();
			
			for (int k = 0; k < K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				grid[r][c] = 1;
				q.offer(new Point(r,c));
			}
			
			while (!q.isEmpty()) {
				Point p = q.poll();
				if (v[p.r][p.c]) continue;
				v[p.r][p.c] = true; 
				dfs(p.r, p.c);
				result++;
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	private static void dfs(int r, int c) {
		
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || v[nr][nc] || grid[nr][nc] == 0) continue;
		
			v[nr][nc] = true;
			dfs(nr, nc);
		}
	}
}
