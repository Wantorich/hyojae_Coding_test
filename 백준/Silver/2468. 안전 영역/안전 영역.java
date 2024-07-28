import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int [][] grid, mov = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int result = Integer.MIN_VALUE;
	static boolean [][] v;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		grid = new int[N][N];
		v = new boolean[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(tokens[j]);
				min = Math.min(min, grid[i][j]);
				max = Math.max(max, grid[i][j]);
			}
		}
		
		for (int h = min-1; h <= max; h++) {
			for (boolean [] r : v) {
				Arrays.fill(r, false);
			}
			
			int area_cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] > h && !v[i][j]) {
						area_cnt++;
						dfs(i, j, h);
					}
				}
			}
			result = Math.max(result, area_cnt);
		}
		
		System.out.println(result);
	}
	
	static void dfs(int r, int c, int h) {
		if (0 > r || r >= N || 0 > c || c >= N || v[r][c] || grid[r][c] <= h) return;
		
		v[r][c] = true;
		
		for (int k = 0; k < mov.length; k++) {
			dfs(r+mov[k][0], c+mov[k][1], h);
		}
	}
}

