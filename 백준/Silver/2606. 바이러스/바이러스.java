import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int [][] grid;
	static boolean [] isvisited;
	static int N, M, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		grid = new int [N+1][N+1];
		isvisited = new boolean [N+1];
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			grid[a][b] = 1;
			grid[b][a] = 1;
		}
		
		bfs(1);
		System.out.println(result);
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(v);
		isvisited[v] = true;
		
		while (!queue.isEmpty()) {
			int n = queue.poll();
			
			for (int j = 1; j <= N; j++) {
				if (grid[n][j] == 1 && !isvisited[j]) {
					queue.offer(j);
					isvisited[j] = true;
					result++;
				}
			}
		}
	}
	
}

