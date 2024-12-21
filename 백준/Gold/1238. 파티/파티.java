import java.util.*;

public class Main {
	static int N, M, X;
	static int[] go, back;
	static List<int[]>[] adjList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); M = sc.nextInt(); X = sc.nextInt();
		adjList = new ArrayList[N+1];
		for (int i = 1; i < adjList.length; i++)
			adjList[i] = new ArrayList<>();
		
		int from, to, weight;
		for (int i = 0; i < M; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			weight = sc.nextInt();
			adjList[from].add(new int[] {to, weight});
		}
		
		go = new int[N+1];
		back = new int[N+1];
		
		// 다익스트라
		for (int i = 1; i <= N; i++) {
			if (i == X)
				back = dijkstra(i);
			else 
				go[i] = dijkstra(i)[X];
		}
		
		int answer = 0;
		for (int i = 1; i <= N; i++)
			answer = Math.max(answer, go[i] + back[i]);
		
//		System.out.println(Arrays.toString(go));
//		System.out.println(Arrays.toString(back));
		System.out.println(answer);
		
		sc.close();
	}
	
	// 정점 v에서 X까지의 최단거리
	private static int[] dijkstra(int v) {
		int[] dis = new int[N+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[v] = 0;
		boolean[] visit = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(a, b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {v, 0});
		
		while (!pq.isEmpty()) {
			int[] edge = pq.poll();
			
			if (edge[0] == X && v != X) break;
			visit[edge[0]] = true;
			
			for (int[] next : adjList[edge[0]]) {
				if (visit[next[0]]) continue;
				
				if (dis[next[0]] > dis[edge[0]] + next[1]) {
					dis[next[0]] = dis[edge[0]] + next[1];
					pq.offer(new int[] {next[0], dis[next[0]]});
				}
			}
		}
		
		return dis;
	}
}

