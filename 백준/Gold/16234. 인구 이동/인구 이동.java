import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int r, c, v;

	Point(int r, int c, int v) {
		this.r = r;
		this.c = c;
		this.v = v;
	}
}

public class Main {
	static int[][] grid;
	static boolean[][] visit;
	static int N, L, R;
	static boolean changed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);

		grid = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while (true) {
			changed = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(new Point(i, j, grid[i][j]));
				}
			}
			
			for (boolean [] row : visit) 
				Arrays.fill(row, false);
			
			if (!changed) {
//				for (int [] row : grid) {
//					for (int val : row) {
//						System.out.print(val + " ");
//					}
//					System.out.println();
//				}
				System.out.println(time);
				break;
			}
			time++;
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean inrange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}


	static void bfs(Point point) {
		if (visit[point.r][point.c]) return;
		
		Queue<Point> bfsQ = new ArrayDeque<Point>();
		List<Point> p_list = new ArrayList<Point>();
		bfsQ.offer(point);
		int sum = point.v;
		visit[point.r][point.c] = true; 
		p_list.add(point);
		int cnt = 1;

		while (!bfsQ.isEmpty()) {
			Point p = bfsQ.poll();
			int nr, nc;
			for (int i = 0; i < dr.length; i++) {
				nr = p.r + dr[i];
				nc = p.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visit[nr][nc])
					continue;

				Point np = new Point(nr, nc, grid[nr][nc]);
				int diff = Math.abs(p.v - np.v);
				if (L <= diff && diff <= R) { // in range
					sum += np.v;
					visit[nr][nc] = true;
					bfsQ.offer(np);
					p_list.add(np);
					cnt++;
					changed = true;
				}
			}
		}
		
		int average = sum / cnt;
		p_list.stream().forEach(p -> grid[p.r][p.c] = average);
	}
}