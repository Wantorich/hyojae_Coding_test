import java.util.*;

public class Solution {
	static int N, result, er, ec; 
	static int grid[][];
	static int [] dr = {-1, -1, 1, 1}; // 좌상단, 우상단, 우하단, 좌하단
	static int [] dc = {-1, 1, 1, -1};
	static boolean [][] v;
	static boolean [] dirUsed;
	static Set<Integer> historySet = new HashSet<Integer>();

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			grid = new int[N][N];
			v = new boolean[N][N];
			dirUsed = new boolean[4];
			result = -1;
			
			for (int i = 0; i < N*N; i++) grid[i/N][i%N] = sc.nextInt(); 
			
			// 모서리에선 탐색 시작하지 않아도 됌
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					er = i; ec = j;
					Arrays.fill(dirUsed, false); // 방향 방문배열 초기화
					historySet.clear();
					dfs(i, j, 0, -1);
					v[i][j] = false;
//					System.out.println();
				}
			}
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}
	
	// return하고 뺑뻉이 돌다가 다시 방문해서 숫자가 느는듯
	// 즉 사각형일때랑 뻉뻉이 일때랑 구분을 해줘야한다?

	private static void dfs(int r, int c, int cnt, int prevDir) {
		v[r][c] = true;
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || historySet.contains(grid[nr][nc])) continue;
			
			if (nr == er && nc == ec && cnt >= 3) { // 목적지 도착
				result = Math.max(result, cnt+1);
				return;
			}
			
			if (v[nr][nc] || (prevDir != -1 && dirUsed[i])) continue; 
			
			// 첫 시작이거나, 방향이 똑같거나, 90도 꺾이거나
			if (prevDir == -1 || i == (prevDir + 1) % 4 || prevDir == i) {
				if (prevDir != -1 && i != prevDir) { // 방향이 꺾이면 이전 방향은 더 안씀
					dirUsed[prevDir] = true;
				}
				historySet.add(grid[nr][nc]);
				dfs(nr, nc, cnt+1, i);
				v[nr][nc] = false;
				historySet.remove(grid[nr][nc]);
				if (prevDir != -1) { // 방향이 꺾이면 이전 방향은 더 안씀
					dirUsed[prevDir] = false;
				}
			}
		}
	}
}