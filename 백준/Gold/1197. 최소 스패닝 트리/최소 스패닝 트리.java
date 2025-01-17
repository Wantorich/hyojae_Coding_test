import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int V = Integer.parseInt(st.nextToken());
    	int E = Integer.parseInt(st.nextToken());

    	List<int[]>[] adjList = new ArrayList[V+1];
    	for (int i = 1; i <= V; i++)
    		adjList[i] = new ArrayList<>();

    	int from, to, weight;
    	for (int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		from = Integer.parseInt(st.nextToken());
    		to = Integer.parseInt(st.nextToken());
    		weight = Integer.parseInt(st.nextToken());
    		adjList[from].add(new int[] {to, weight});
    		adjList[to].add(new int[] {from, weight});
    	}
    		
    	long result = 0;
    	boolean[] visit = new boolean[V+1];
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[1], b[1]));
    	pq.offer(new int[] {1, 0});
    	
    	while (!pq.isEmpty()) {
    		int[] edge = pq.poll();
    		
    		if (visit[edge[0]]) continue;
    		visit[edge[0]] = true;
    		result += edge[1];
    		
    		for (int[] connect : adjList[edge[0]]) {
    			if (visit[connect[0]]) continue;
    			pq.offer(new int[] {connect[0], connect[1]});
    		}
    	}
    	
    	System.out.println(result);
    }
}
