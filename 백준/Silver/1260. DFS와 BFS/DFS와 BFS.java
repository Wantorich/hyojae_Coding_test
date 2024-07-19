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
	static int [][] ll;
	static boolean[] isvisited;
	static int N;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		ll = new int[N+1][N+1];
		isvisited = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			ll[s][e] = 1;
			ll[e][s] = 1;
		}
		
		dfs(V);
		
		System.out.println();
		Arrays.fill(isvisited, false);
		
		bfs(V);
		
	}
	
	static void dfs(int v) {
		isvisited[v] = true;
		System.out.print(v + " ");
		
		for (int j = 1; j < N+1; j++) {
			if (ll[v][j] == 1 && !isvisited[j]) {
				dfs(j);
			}
		}
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList();
		queue.offer(v);
		isvisited[v] = true;
		
		while (!queue.isEmpty()) {
			int nv = queue.poll();
			System.out.print(nv + " ");
			
			for (int j = 1; j < N+1; j++) {
				int tmp = ll[nv][j];
				if (tmp == 1 && !isvisited[j]) {
					queue.offer(j);
					isvisited[j] = true;
				}
			}
		}
		
	}

}

/*
linked list로 관리하는게 맞는듯
 

*/