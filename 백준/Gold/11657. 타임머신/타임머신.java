import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int INF = Integer.MAX_VALUE / 3;
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		List<int[]>[] adjList = new ArrayList[N+1];
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			adjList[from].add(new int[] {to, weight});
		}
		
		long[] dis = new long[N+1];
		Arrays.fill(dis, INF);
		dis[1] = 0;
		
		for (int k = 1; k < N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int[] edge : adjList[i]) {
					int from = i;
					int to = edge[0];
					int weight = edge[1];
					
					// 출발지 거리가 INF라면 애초에 유효한 갱신이 나오기 어려움
					if (dis[from] == INF) continue;
					
					if (dis[to] > dis[from] + weight) 
						dis[to] = dis[from] + weight;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int[] edge : adjList[i]) {
				int from = i;
				int to = edge[0];
				int weight = edge[1];
				
				if (dis[from] == INF) continue;
				
				if (dis[to] > dis[from] + weight) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Arrays.stream(dis)
			.skip(2)
			.map(d -> d == INF ? -1 : d)
			.forEach(d -> sb.append(d).append("\n"));
		
		System.out.println(sb.toString());
		sc.close();
	}
}
