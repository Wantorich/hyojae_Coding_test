import java.util.*;

public class Solution {
	// 1111을 비트마스킹으로 사용하자
	// 오른쪽부터 북 동 남 서
	static int [] dir = {0, 15, 10, 5, 12, 6, 3, 9};
	static int N, M, R, C, L;
	static int [][] road;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			
			road = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					road[i][j] = dir[sc.nextInt()];
				}
			}
			
			System.out.printf("#%d %d\n", t, bfs(R, C));
		}
		
		sc.close();
	}
	
	static int [] dr = {0, 1, 0, -1};
	static int [] dc = {-1, 0, 1, 0};
	// 서 남 동 북
	
	private static int bfs(int r, int c) {
		boolean [][] v = new boolean[N][M];
		int cnt = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		v[r][c] = true;
		
		for (int t = 1; t < L; t++) {
			int qSize = q.size();
			for (int j = 0; j < qSize; j++) {
				int [] p = q.poll();
				
				for (int i = 0; i < dr.length; i++) {
					int nr = p[0] + dr[i];
					int nc = p[1] + dc[i];
					
					if (nr < 0 || nr >= N || nc <0 || nc >= M || v[nr][nc]) continue;
					
					if (canMove(p[0], p[1], i, nr, nc)) {
						q.offer(new int[] {nr, nc});
						v[nr][nc] = true;
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

	private static boolean canMove(int r, int c, int d, int nr, int nc) {
		// d 0~3 , 1 10 100 1000
		boolean curr = (road[r][c] & (1 << d)) >= 1;
		boolean next = (road[nr][nc] & (1 << (d+2)%4)) >= 1;
		return curr && next;
	}
}
