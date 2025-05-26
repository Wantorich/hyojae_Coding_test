import java.util.*;
import java.awt.Point;
import java.io.*;

class Edge {
	int from, to, weight;
	
	Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	int getWeight() {
		return this.weight;
	}
}

public class Main {
	static int[] parent;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	// 크루스칼로 MST를 구하고 마지막 간선(즉 제일 weight가 큰 간선은 포함 안한다. 간선의 합을 구함)
    	parent = new int[N+1];
    	for (int i = 1; i <= N; i++)
    		parent[i] = i;
    	PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
    	
    	int from, to, weight;
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		from = Integer.parseInt(st.nextToken());
    		to = Integer.parseInt(st.nextToken());
    		weight = Integer.parseInt(st.nextToken());
    		pq.offer(new Edge(from, to, weight));
    	}
    	
    	// MST는 간선이 N-1개
    	// union find인가
    	// 간선을 하나 뽑는데 두개를 union할때, 
    	int edgeCnt = 0, lastWeight = 0;
    	long answer = 0;
    	while (edgeCnt < N-1 && !pq.isEmpty()) {
    		Edge edge = pq.poll();
    		if (find(edge.from) != find(edge.to)) {
    			union(edge.from, edge.to);
    			edgeCnt++;
    			lastWeight = edge.weight;
    			answer += edge.weight;
    		}
    	}
    	answer -= lastWeight;
    	System.out.println(answer);
    }

	private static void union(int from, int to) {
		int rootA = find(from);
		int rootB = find(to);
		if (rootA != rootB) {
			parent[rootA] = rootB;
		}
		
	}

	private static int find(int idx) {
		if (parent[idx] == idx)
			return idx;
		return parent[idx] = find(parent[idx]);
	}
}
