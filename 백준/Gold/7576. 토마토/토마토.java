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
		return "Point [r=" + r + ", c=" + c + "]";
	}
}

public class Main {
	static int R, C, remain = 0;
	static int[][] grid;
	static boolean[][] v;
	static Queue<Point> q = new ArrayDeque<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		grid = new int[R][C];
		v = new boolean[R][C];
		// v의 마지막 값의 0은 벽안밀고 간거, 1은 밀고 간경우
		
		for (int i = 0; i < R; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				grid[i][j] = Integer.parseInt(line[j]);
				if (grid[i][j] == 1) q.offer(new Point(i, j));
				else if (grid[i][j] == 0) remain++;
			}
		}
		
		int result = bfs();
		System.out.println(result);
	}

	

	private static int bfs() {
		if (remain == 0) return 0;
		
		int time = 0;
		while (!q.isEmpty() && remain > 0) {
			int qSize = q.size();
			for (int k = 0; k < qSize; k++) {
				Point p = q.poll();
				
				for (int i = 0; i < dr.length; i++) {
					int nr = p.r + dr[i];
					int nc = p.c + dc[i];
					
					if (nr < 0 || nr >= R || nc < 0 || nc >= C || Math.abs(grid[nr][nc]) == 1) continue;
					
					grid[nr][nc] = 1;
					q.offer(new Point(nr, nc));
					remain--;
				}
			}
			time++;
		}
		return remain == 0 ? time : -1;
	}

	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
}