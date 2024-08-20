import java.util.*;

public class Solution {
	static int N, M, result;
	static int[][] grid;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			grid = new int[N][N];
			result = Integer.MIN_VALUE;
			
			for (int i = 0; i < N*N; i++) grid[i/N][i%N] = sc.nextInt();
			
			// 한 변의 길이를 모두 커버할 수 있는 range로 시작 범위를 설정함
			// 아마 이게 최적값이 맞는것같은데 증명은 못하겠음
            // 수정)) 최적의 시작값이 어딘지를 어떻게 찾아야할지 사실 잘 모르겠음
            // 시작 범위를 너무 크게 설정하면 시간초과가 남
			int range = N + 1; 
			for (int k = range; k >= 1; k--) {
				for (int i = 0; i < N*N; i++) {
					// 모든 정점에 대해서 범위가 K인 BFS 실행
					// 각 BFS에서는 범위내의 집을 찾고
					// 이득이 되는지를 확인한후 이득이 되면 최대값과 비교후 갱신
					bfs(i/N, i%N, k); // range K로 탐색할 수 있는 경우의 수 최대를 이 루프에서 돔
				}
				// result가 k-1개로 탐색할 수 있는 최대값보다 높다면 바로 답임
				// range k-1개에서의 비용이 그곳에서 나올 수 있는 집의 최대개수인데
				// range K에서 이보다 많은 집을 찾았다면 밑에서 더 돌릴필요가 없기때문
				if (result > (k-1) * (k-1) + (k-2) * (k-2)) break;
			}
			
			System.out.printf("#%d %d\n", t, result);
		}	
		
		
		sc.close();
	}

	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	private static void bfs(int r, int c, int k) {
		boolean [][] v = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r,c});
		int homeCnt = 0;
		v[r][c] = true;
		
		for (int l = 1; l <= k; l++) {
			// K번 만큼 파동을 넓힘, range가 K
			int qSize = q.size();
			for (int j = 0; j < qSize; j++) { 
				// Qsize만큼만 돌림, 큐가 빌때까지가 아니고, 큐에 담겨있는것 만큼만 
				// 이게 한 파동만 펼쳐지는 것을 보장해줌
				int [] p = q.poll();
				
				if (grid[p[0]][p[1]] == 1) homeCnt++; // 집 개수 세기
				
				
				for (int i = 0; i < dr.length; i++) {
					int nr = p[0] + dr[i];
					int nc = p[1] + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
					
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		// 총 이득(집 개수) - 비용 > 0 보다 큰지 확인하고
		// 이득인 경우에는 집 개수를 결과값과 비교해서 최대값 갱신
		int cost = k*k + (k-1)*(k-1);
		
		if (homeCnt * M >= cost) result = Math.max(result, homeCnt);
	}
}

