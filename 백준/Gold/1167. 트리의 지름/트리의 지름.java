import java.util.*;
import java.io.*;

public class Main {
	static int maxLen, maxVertex;
	static List<int[]>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N+1];
		for (int i = 1; i < adjList.length; i++) 
			adjList[i] = new ArrayList<>();
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String token = st.nextToken();
			int from = Integer.parseInt(token); 
			int to, weight;
			while (true) {
				to = Integer.parseInt(st.nextToken());
				if (to == -1) break;
				weight = Integer.parseInt(st.nextToken());
				adjList[from].add(new int[] {to, weight});
			}
		}
		
		// 임의의 노드에서 가장 먼 곳 찾음
		dfs(1, 0, new boolean[N+1]);
		// 가장 긴 지름 찾음
		dfs(maxVertex, 0, new boolean[N+1]);
		System.out.println(maxLen);
	}

	private static void dfs(int v, int dis, boolean[] visit) {
		visit[v] = true;
		
		if (dis > maxLen) {
			maxLen = dis;
			maxVertex = v;
		}
		
		for (int[] connect : adjList[v]) {
			int next = connect[0];
			if (visit[next]) continue;
			dfs(next, dis + connect[1], visit);
		}
	}
}
