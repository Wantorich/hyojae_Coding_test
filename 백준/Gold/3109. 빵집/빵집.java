import java.util.*;

public class Main {
	static int N, M, result;
	static char [][] grid;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		sc.nextLine();
		grid = new char[N][M];
		for (int i = 0; i < N; i++) {
			grid[i] = sc.nextLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			// 시작점은 i,0
			dfs(i, 0, i);
		}
		// 지나가면서 내가 지나간 좌표를 기억하고
		// 더 돌데가 없으면, 즉 다음 dfs를 호출하지 못하면 list에 잇는 값들을 모두 0으로 해줌
		
		System.out.println(result);
		sc.close();
	}

	static int [] dr = {-1, 0, 1};
	
	private static boolean dfs(int r, int c, int rowIdx) {
		if (c == M-1) {
			// 도착하면
			result++;
			return true;
		}
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + 1;
			
			if (nr < 0 || nr >= N || nc >= M || grid[nr][nc] == '#' || grid[nr][nc] == 'x') continue;
			
			// 파이프 설치해주고
			// 그곳에서 다시 탐색 시작
			
			grid[nr][nc] = '#';
			
			if (dfs(nr, nc, rowIdx)) return true;
		}
		return false;
	}
}
