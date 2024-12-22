import java.util.*;

public class Main {
	static int N, INF = Integer.MAX_VALUE/3;
//	static int[][] adjMatrix;
	static List<int[]>[] adjList;
	static int[] dis;
	static boolean[] connect;
	static Set<Integer> paramSet;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		O:for (int tc = 1; tc <= testCase; tc++) {
			N = sc.nextInt();
			int M = sc.nextInt();
			int W = sc.nextInt();
			
			adjList = new ArrayList[N+1];
			for (int i = 1; i <= N; i++)
				adjList[i] = new ArrayList<int[]>();
			
			int from, to, weight;
			for (int i = 1; i <= M+W; i++) {
				from = sc.nextInt();
				to = sc.nextInt();
				weight = sc.nextInt();
				
				if (i <= M) {
					adjList[from].add(new int[] {to, weight});
					adjList[to].add(new int[] {from, weight}); 
				} else {
					adjList[from].add(new int[] {to, -weight});
				}
			}
			
			dis = new int[N+1];
			Arrays.fill(dis, INF);
			connect = new boolean[N+1];
			int cnt = 0;
			
			for (int i = 1; i <= N; i++) {
				if (adjList[i].isEmpty() || connect[i]) continue;
				dis[i] = 0;
				dfs(i);
			}
			
			
			for (int k = 1; k <= N; k++) {
				cnt = 0;
				for (int i = 1; i <= N; i++) {
					for (int[] edge : adjList[i]) {
						from = i;
						to = edge[0];
						weight = edge[1];
						if (dis[to] > dis[from] + weight) {
							dis[to] = dis[from] + weight;
							cnt++;
						}
					}
				}
				if (cnt == 0) {
					sb.append("NO").append("\n");
					continue O;
				}
			}
			
			sb.append("YES").append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}

	private static void dfs(int v) {
		if (connect[v]) return;
		connect[v] = true;
		
		for (int[] edge : adjList[v]) {
			int next = edge[0];
			if (connect[next]) continue;
			dfs(next);
		}
	}
}
