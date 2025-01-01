import java.util.*;

public class Main {
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int INF = Integer.MAX_VALUE;
		N = sc.nextInt();
		M = sc.nextInt();
		
		List<Edge>[] adjList = new ArrayList[N+1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		long[] dis = new long[N+1];
		Arrays.fill(dis, INF);
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			adjList[from].add(new Edge(to, weight));
		}
		
		int start = sc.nextInt();
		int end = sc.nextInt();
		
		dis[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
		pq.offer(new Edge(start, 0));
		
		boolean[] visit = new boolean[N+1];
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if (visit[curr.to]) continue;
			visit[curr.to] = true;
			
			for (Edge next : adjList[curr.to]) {
				if (dis[next.to] > dis[curr.to] + next.weight) {
					dis[next.to] = dis[curr.to] + next.weight;
					pq.offer(new Edge(next.to, dis[next.to]));
				}
			}
		}
		
		System.out.println(dis[end]);
		sc.close();
	}
}

class Edge {
	int to;
	long weight;
	
	Edge(int to, long weight) {
		this.to = to;
		this.weight = weight;
	}
	
	long getWeight() {return this.weight;}
}
