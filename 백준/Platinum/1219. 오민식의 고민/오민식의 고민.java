import java.util.*;

public class Main {
	static boolean reachFlag = false;
	static boolean[] visit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int INF = Integer.MIN_VALUE / 3;
		int N = sc.nextInt();
		int start = sc.nextInt();
		int end = sc.nextInt();
		int M = sc.nextInt();
		
		// 출발, 도착, 비용
		List<int[]> edgeList = new ArrayList<>();
		int[] earn = new int[N];
		long[] dis = new long[N];
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			edgeList.add(new int[] {from, to, -weight});
		}
		
		for (int i = 0; i < N; i++)
			earn[i] = sc.nextInt();
		
		Arrays.fill(dis, INF);
		dis[start] = earn[start];
		
		// check can reach or not
		visit = new boolean[N];
		dfs(start, end, edgeList);
		if (!reachFlag) {
			System.out.println("gg");
			return;
		}
		
		// Bellman-ford
		for (int k = 1; k < N; k++) {
			for (int[] edge : edgeList) {
				int from = edge[0];
				int to = edge[1];
				int weight = edge[2] + earn[to];
				
				if (dis[from] == INF || dis[to] >= dis[from] + weight) 
					continue;
				
				dis[to] = dis[from] + weight;
			}
		}
		
		// Negative Cycle check
		for (int[] edge : edgeList) {
			int from = edge[0];
			int to = edge[1];
			int weight = edge[2] + earn[to];
			
			if (dis[from] == INF || dis[to] >= dis[from] + weight) 
				continue;
			
			reachFlag = false;
			Arrays.fill(visit, false);
			dfs(from, end, edgeList);
			
			if (reachFlag) {
				System.out.println("Gee");
				return;
			}
		}
		
		System.out.println(dis[end]);
		sc.close();
	}

	private static void dfs(int v, int end, List<int[]> edgeList) {
		if (visit[v]) return;
		visit[v] = true;
		if (v == end) {
			reachFlag = true;
			return;
		}
		
		for (int[] edge : edgeList) {
			if (edge[0] != v || visit[edge[1]])
				continue;
			
			dfs(edge[1], end, edgeList);
		}
	}
}
