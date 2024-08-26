import java.util.*;

public class Main {
	static int R, C, result;
	static char [][] grid;
	static boolean [] alpha = new boolean[26];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		grid = new char[R][C];
		
		sc.nextLine();
		for (int i = 0; i < R; i++) {
			String input = sc.nextLine();
			for (int j = 0; j < C; j++) {
				grid[i][j] = input.charAt(j);
			}
		}
		
		// 0,0에서 시작해서 최대한 많은곳을 탐색
		alpha[grid[0][0] - 'A'] = true; // 시작점 체크
		dfs(0, 0, 1);
		
		System.out.println(result);
		
		sc.close();
	}

	private static void dfs(int r, int c, int cnt) {
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || alpha[grid[nr][nc] - 'A']) continue;
			
			alpha[grid[nr][nc] - 'A'] = true;
			dfs(nr, nc, cnt+1);
			alpha[grid[nr][nc] - 'A'] = false;
		}
		
		result = Math.max(result, cnt);
	}

	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
}
