import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int r, c, val;

	Point(int r, int c, int val) {
		this(r, c);
		this.val = val;
	}

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int[][] grid;
	static boolean[][] visit;
	static int N, M, tomato_cnt = 0;
	static Queue<Point> pq;
	static Queue<Point> tmp = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		grid = new int[N][M];
		visit = new boolean[N][M];
		pq = new LinkedList<Point>();

		for (int k = 0; k < N; k++) {
			line = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				switch (grid[k][i] = Integer.parseInt(line[i])) {
				case 0:
					break;
				default:
					pq.add(new Point(k, i, grid[k][i]));
				}
			}
		}

		int time = 0, result = 0, qSize;

		while (!pq.isEmpty()) {
			int cnt = bfs(pq.peek());
			if (cnt != pq.size()) {
				result = time;
				break;
			}

			qSize = pq.size();

			for (int i = 0; i < qSize; i++) {
				Point p = pq.poll();
				contagious(p.r, p.c, p.val);
			}
			
			for (Point p : tmp) {
				grid[p.r][p.c] = p.val;
				if (p.val != 0) pq.offer(p);
			}
			
			tmp.clear();

			time++;
		}

		System.out.println(result);
	}

	static int bfs(Point point) {
		int cnt = 1;
		Queue<Point> q = new LinkedList<>();
		q.offer(point);
		visit[point.r][point.c] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < dr.length; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || grid[nr][nc] == 0)
					continue;
				
				visit[nr][nc] = true;
				q.offer(new Point(nr, nc));
				cnt++;
			}
		}
		
		for (boolean [] row : visit) {
			Arrays.fill(row, false);
		}

		return cnt;
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static void contagious(int r, int c, int v) {
		int nr, nc, sea_cnt = 0;

		for (int i = 0; i < dr.length; i++) {
			nr = r + dr[i];
			nc = c + dc[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			
			if (grid[nr][nc] == 0) sea_cnt++;
		}
		tmp.offer(new Point(r, c, Math.max(0, grid[r][c] - sea_cnt)));

	}
}