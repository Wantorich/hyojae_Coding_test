import java.util.*;
import java.io.*;


// 3차 방문배열의 3번째 인자는 1개 true/false로 벽을 깬적이있으면 true로 계속 덮어써준다
// true인 상태로 벽을 만나면 탐색 그만
// 안만나면 계속 false인 상태
// 한 지점에서 벽을 밀고온애랑 안밀고온애가 있다면 안밀고온애로 갱신 
// false 인데 true로 
class Point {
	int r, c, d;
	Point(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + ", d=" + d + "]";
	}
	
}


public class Main {
	static int R, C, cnt = Integer.MAX_VALUE;
	static int[][] grid;
	static boolean[][][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new int[R][C];
		v = new boolean[R][C][2];
		// v의 마지막 값의 0은 벽안밀고 간거, 1은 밀고 간경우
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				grid[i][j] = line.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);
		cnt = cnt == Integer.MAX_VALUE ? -1 : cnt;
		System.out.println(cnt);
	}

	

	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c, 1));
		v[r][c][0] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			if (p.r == R-1 && p.c == C-1) {
				cnt = Math.min(cnt, p.d);
				continue;
			}
			
			for (int i = 0; i < dr.length; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || (v[nr][nc][0] && !v[nr][nc][1])) continue;
				
				if (grid[nr][nc] == 0) {
					// 0,1 인 경우와 1,0인 경우
					// 1,0인 경우는 1,0으로 바꿔주고
					// 0,1인 경우는 0,1로 써준다
					if (!v[nr][nc][0] && !v[nr][nc][1]) {
						// 처음 방문하는 경우
						v[nr][nc][0] = v[p.r][p.c][0];
						v[nr][nc][1] = v[p.r][p.c][1];
						q.offer(new Point(nr, nc, p.d+1));
					} else {
						// 이미 방문한 경우
						// 0,1 에서 1,0으로 업데이트
						if (v[p.r][p.c][0]) {
							v[nr][nc][0] = true;
							v[nr][nc][1] = false;
							q.offer(new Point(nr, nc, p.d+1));
						}
					}
				}
				else if (grid[nr][nc] == 1) {
					if (!v[p.r][p.c][1]) {
						v[nr][nc][1] = true;
						q.offer(new Point(nr, nc, p.d+1));
					}
				}
			}
		}
	}
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
}