
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int [] scores, cals;
    static int result, R, C;
    static char [][] grid;
    static boolean [][] v;
    static int [] alpha;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= test_case; t++) {
        	String [] line = br.readLine().split(" ");
            R = Integer.parseInt(line[0]);
            C = Integer.parseInt(line[1]);
            grid = new char[R+1][C+1];
            v = new boolean[R+1][C+1];
            result = Integer.MIN_VALUE;
            
            for (int i = 1; i <= R; i++) {
            	char [] chars = br.readLine().toCharArray();
            	for (int j = 1; j <= C; j++) {
            		grid[i][j] = chars[j-1];
            	}
            }
            
            alpha = new int[26];
            alpha[grid[1][1] - 'A'] = 1;
            dfs(1, 1, 1);
            
            System.out.printf("#%d %d\n", t, result);
        }
    }

    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    
    static void dfs(int r, int c, int cnt) {
    	
    	for (int i = 0; i < dr.length; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		
    		if (nr <= 0 || nr > R || nc <= 0 || nc > C) continue;
    		
    		int index = grid[nr][nc] - 'A';
    		if (alpha[index] == 0) {
    			alpha[index] = 1;
    			dfs(nr, nc, cnt+1);
    			alpha[index] = 0;
    		}
    	}
    	result = Math.max(result, cnt);
    }
}
