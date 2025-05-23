import java.util.*;
import java.awt.Point;
import java.io.*;

class Edge {
	int to, weight;
	
	Edge (int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	
	int getWeight() {
		return this.weight;
	}
}

public class Main {
	static List<Edge>[] adjList;
	static int INF = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int E = Integer.parseInt(st.nextToken());
    	adjList = new ArrayList[N+1];
    	for (int i = 1; i <= N; i++)
    		adjList[i] = new ArrayList<>();
    	int from, to, weight;
    	for (int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		from = Integer.parseInt(st.nextToken());
    		to = Integer.parseInt(st.nextToken());
    		weight = Integer.parseInt(st.nextToken());
    		adjList[from].add(new Edge(to, weight));
    		adjList[to].add(new Edge(from, weight));
    	}
    	st = new StringTokenizer(br.readLine(), " ");
    	int v1 = Integer.parseInt(st.nextToken());
    	int v2 = Integer.parseInt(st.nextToken());
    	
    	/*
    	 * 임의의 두 정점 v1, v2를 통과해야하니까
    	 * 1 - v1 - v2 - N
    	 * 1 - v2 - v1 - N 둘중 최솟값
    	 * 
    	 * 그럼 시작정점에 따라 다르니 다익스트라를 메소드로 빼야겠네
    	 */
    	
    	long case1 = INF, case2 = INF;
    	
    	if (isConnected(1, v1) && isConnected(v1, v2) && isConnected(v2, N)) {
    		case1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
    	}
    	
    	if (isConnected(1, v2) && isConnected(v2, v1) && isConnected(v1, N)) {
    		case2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
    	}
    	
    	long answer = Math.min(case1, case2);
    	System.out.println(answer == INF ? -1 : answer);
    }
    
    static boolean isConnected(int start, int end) {
    	ArrayDeque<Edge> q = new ArrayDeque<>();
    	q.offer(new Edge(start, 0));
    	boolean[] visit = new boolean[adjList.length];
    	visit[start] = true;
    	
    	while (!q.isEmpty()) {
    		Edge curr = q.poll();
    		if (curr.to == end) 
    			return true;
    		for (Edge adj : adjList[curr.to]) {
    			if (!visit[adj.to]) {
    				visit[adj.to] = true;
    				q.offer(adj);
    			}
    		}
    	}
    	return false;
    }
    
    static long dijkstra(int start, int end) {
    	int[] dis = new int[adjList.length];
    	Arrays.fill(dis, INF);
    	dis[start] = 0;
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
    	pq.offer(new int[] {start, 0});
    	while (!pq.isEmpty()) {
    		int[] curr = pq.poll();
    		
    		if (curr[0] == end)
    			break;
    		
    		for (Edge adj : adjList[curr[0]]) {
    			if (dis[adj.to] >= dis[curr[0]] + adj.weight) {
    				dis[adj.to] = dis[curr[0]] + adj.weight;
    				pq.offer(new int[] {adj.to, dis[adj.to]});
    			}
    		}
    	}
    	return dis[end]; // 이게 MAXVALUE이면?
    }
}
