import java.io.*;
import java.util.*;

public class Solution {
	static int N, adjMatrix[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			adjMatrix = new int[N+1][N+1];
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}
			
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (gtBFS(i) + ltBFS(i) == N-1) ans++;
			}
			
			System.out.println("#"+t+" " + ans);
		}
	}

	private static int gtBFS(int start) {
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean [] visit = new boolean[N+1];
		
		q.offer(start);
		visit[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= N; i++) {
				if (!visit[i] && adjMatrix[cur][i] != 0) {
					visit[i] = true;
					q.offer(i);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int ltBFS(int start) {
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean [] visit = new boolean[N+1];
		
		q.offer(start);
		visit[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= N; i++) {
				if (!visit[i] && adjMatrix[i][cur] != 0) {
					visit[i] = true;
					q.offer(i);
					cnt++;
				}
			}
		}
		return cnt;
	}
}
