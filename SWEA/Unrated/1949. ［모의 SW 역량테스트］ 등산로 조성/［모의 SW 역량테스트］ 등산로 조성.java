import java.util.*;
import java.io.*;

class Point {
	int r, c;
	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Solution {
    static int result, N, K, max;
    static int [][] grid;
    static boolean [][] v;
    static Queue<Point> q;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= test_case; t++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            grid = new int[N][N];
            v = new boolean[N][N];
            max = Integer.MIN_VALUE;
            q = new ArrayDeque<Point>();
            result = 0;
            
            for (int i = 0; i < N; i++) {
            	String [] chars = br.readLine().split(" ");
            	for (int j = 0; j < N; j++) {
            		grid[i][j] = Integer.parseInt(chars[j]);
            		if (max < grid[i][j]) {
            			q.clear();
            			max = grid[i][j];
            			q.offer(new Point(i, j));
            		}
            		else if (max == grid[i][j]) {
            			q.offer(new Point(i, j));
            		}
            	}
            }
            
            while (!q.isEmpty()) {
            	Point p = q.poll();
            	v[p.r][p.c] = true;
            	dfs(p.r, p.c, 1, 1);
            	v[p.r][p.c] = false; 
            }
            
            System.out.printf("#%d %d\n", t, result);
        }
    }

    private static void dfs(int r, int c, int k, int dis) {
    	if (result < dis) result = dis;
    	if (grid[r][c] == 0) return;
    	
    	for (int i = 0; i < dr.length; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		
    		if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
    		
    		int h_diff = grid[r][c] - grid[nr][nc];
    		
    		
    		if (h_diff > 0) {
    			v[nr][nc] = true;
    			dfs(nr, nc, k, dis+1);
    			v[nr][nc] = false;
    		}
    		
			if (k > 0) {
				for (int h = 1; h <= K; h++) {
					if (h_diff + h > 0 && grid[nr][nc] - h >= 0) { // 깎고 이동가능
						grid[nr][nc] -= h;
						v[nr][nc] = true;
						dfs(nr, nc, k-1, dis+1);
						v[nr][nc] = false;
						grid[nr][nc] += h;
					}
				}
			}
    	}
	}

	static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
}
