import java.util.*;

public class Main {
	static int N, grid[][], result, distance[][];
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static boolean [][] visit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		while ((N = sc.nextInt()) != 0) {
			grid = new int[N][N];
			visit = new boolean[N][N];
			distance = new int[N][N];
			for (int [] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
			result = 0;
			for (int i = 0; i < N*N; i++) grid[i/N][i%N] = sc.nextInt();
			distance[0][0] = grid[0][0]; // 시작정점 거리 초기화
			dijkstra(0, 0);
			
//			for (int [] row : distance) System.out.println(Arrays.toString(row));
			System.out.printf("Problem %d: %d\n", ++index, distance[N-1][N-1]);
		}
		
		sc.close();
	}

	private static void dijkstra(int r, int c) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((arr1, arr2) -> Integer.compare(arr1[2], arr2[2]));
		q.offer(new int[] {r, c, distance[r][c]});
		
		while (!q.isEmpty()) {
			int [] next = q.poll();
			
			if (visit[next[0]][next[1]]) continue;
			
			visit[next[0]][next[1]] = true;
			
			for (int i = 0; i < dr.length; i++) {
				int nr = next[0] + dr[i];
				int nc = next[1] + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visit[nr][nc]) continue;
				
				if (grid[nr][nc] + distance[next[0]][next[1]] < distance[nr][nc]) {
					distance[nr][nc] = grid[nr][nc] + distance[next[0]][next[1]]; // 거리 업데이트
					q.offer(new int[] {nr, nc, distance[nr][nc]});
				}
			}
		}
	}
}

