import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static int [][] grid;
	static int [][] isvisited;
	static int N, M, result;
	static Queue<int []> queue;
	static int[][] mov = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new int[N][M];
		isvisited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		queue = new LinkedList<int []>();
		isvisited[0][0] = 1;
		bfs(new int[] {0,0});
		
		System.out.println(result);
	}
	
	static void bfs(int [] pos) {
		queue.offer(new int[] {pos[0], pos[1]});
		
		while (!queue.isEmpty()) {
			int [] temp = queue.poll();
			int r = temp[0];
			int c = temp[1];
			
			if (r == N-1 && c == M-1) {
				result = isvisited[r][c];
				return;
			}
			
			for (int i = 0; i < mov.length; i++) {
				int nr = r + mov[i][0];
				int nc = c + mov[i][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if (grid[nr][nc] == 1 && isvisited[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
					isvisited[nr][nc] = isvisited[r][c] + 1;
				}
			}
		}
	}
	
}

