import java.util.*;
import java.io.*;

public class Main {
	static char [][] grid = new char[5][5];
	static boolean [][] visit = new boolean[5][5];
	static int result = 0;
	static List<String> list = new ArrayList<String>();
	static int [][] points = new int[5*5][2];
	static int [][] nGrid;
	static int total = 0;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			grid[i] = sc.nextLine().toCharArray();
		}
		
		int k = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++, k++) {
				points[k] = new int[] {i,j};
			}
		}
		combination(new int[7][2], 0, 0);
		
		System.out.println(result);
		sc.close();
	}
	
	// dfs로 안되는데? 조합인가
	
	static void combination(int [][] sel, int k, int idx) {
		if (idx > points.length) return;
		
		if (k == 7 || idx == points.length) {
			if (k == 7 && points.length == idx) return;
			char [] str = new char[7];
			int s_idx = 0;
			
			nGrid = new int[5][5];
			for (int [] p : sel) {
				str[s_idx++] = grid[p[0]][p[1]];
				nGrid[p[0]][p[1]] = 1;
			}
			
			long s_num = String.valueOf(str).chars().filter(ch -> ch == 'S').count();
			
			visit = new boolean[5][5];
			int cal_num = bfs(sel[0][0], sel[0][1]);
			if (s_num >= 4 && cal_num == 7) {
				result++;
			} else  {
				if (s_num >= 0) {
				}
			}
			return;
		}
		
		sel[k] = points[idx];
		combination(sel, k+1, idx+1);
		combination(sel, k, idx+1);
	}
	
	private static int bfs(int r, int c) {
		Queue<int []> q = new ArrayDeque<int []>();
		q.offer(new int[] {r,c});
		visit[r][c] = true;
		
		int cnt = 1;
		while (!q.isEmpty()) {
			int [] point = q.poll();
			
			for (int i = 0; i < dr.length; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visit[nr][nc] || nGrid[nr][nc] == 0) continue;
				
				if (nGrid[nr][nc] == 1) {
					visit[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					cnt++;
				}
			}
		}
		return cnt;
	}
	static int [] dr = {0, 1, 0, -1};
	static int [] dc = {1, 0, -1, 0};
}