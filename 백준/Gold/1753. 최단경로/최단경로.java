import java.util.*;

public class Main {
	static int V, E, start, distance[];
	static Map<Integer, int[]> adjMap;
	static List<int[]>[] adjList;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		start = sc.nextInt();
		
		adjMap = new HashMap<Integer, int[]>();
		adjList = new ArrayList[V+1];
		
		for (int i = 1; i <= V; i++) adjList[i] = new ArrayList<int[]>();
		
		for (int e = 0; e < E; e++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			adjList[from].add(new int[] {to, weight});
		}
		
		
		dijkstra();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(distance[i]).append("\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}

	private static void dijkstra() {
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean [] visit = new boolean[V+1];
		distance[start] = 0;
		
		PriorityQueue<int []> pq = new PriorityQueue<int[]>((arr1, arr2) -> Integer.compare(arr1[1], arr2[1]));
		pq.offer(new int[] {start, 0});
		int edgeCnt = 0;
		
		while (!pq.isEmpty()) {
			int [] nodeInfo = pq.poll();
			int curr = nodeInfo[0];
			int currDis = nodeInfo[1];
			
			if (visit[curr]) continue;
			
			visit[curr] = true;
			if (edgeCnt == V-1) break;
			
			for (int [] vertex : adjList[curr]) {
				int next = vertex[0];
				int weight = vertex[1];
				
				if (!visit[next] && currDis + weight < distance[next]) {
					distance[next] = currDis + weight;
					pq.offer(new int[] {next, distance[next]});
				}
			}
		}
	}
}

