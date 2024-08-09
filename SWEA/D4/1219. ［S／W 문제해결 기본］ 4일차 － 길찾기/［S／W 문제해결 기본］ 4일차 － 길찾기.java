import java.util.*;
import java.io.*;

public class Solution {
	static int N, max;
	static int[][] grid;
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = 10;
		
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			grid = new int[100][2];
			flag = false;
			String[] inputs = br.readLine().split(" ");
			
			for (int i = 0; i < inputs.length; i+=2) {
				int a = Integer.parseInt(inputs[i]);
				int b = Integer.parseInt(inputs[i+1]);
				if (grid[a][0] == 0) {
					grid[a][0] = b;
				} else {
					grid[a][1] = b;
				}
			}
			
			dfs(0);
			int result = flag ? 1 : 0;
			System.out.printf("#%d %d\n", t, result);
		}
	}


	private static void dfs(int n) {
		if (n == 99 || flag) {
			flag = true;
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			if (grid[n][i] != 0) {
				dfs(grid[n][i]);
			}
		}
		
	}
}