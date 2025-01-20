import java.util.*;
import java.io.*;

public class Main {
	static int[] parent;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int V = Integer.parseInt(st.nextToken());
    	int E = Integer.parseInt(st.nextToken());
    	parent = new int[V+1];
    	Arrays.fill(parent, -1);
    	PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2]));
    	
    	int from, to, weight;
    	for (int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		from = Integer.parseInt(st.nextToken());
    		to = Integer.parseInt(st.nextToken());
    		weight = Integer.parseInt(st.nextToken());
    		pq.offer(new int[] {from, to, weight});
    	}
    		
    	long result = 0;
    	while (!pq.isEmpty()) {
    		int[] edge = pq.poll();
    		if (!union(edge[0], edge[1])) 
    			continue;
    		result += edge[2];
    	}
    	System.out.println(result);
    }
    
    static int find(int a) {
    	if (parent[a] == -1) return a;
    	return parent[a] = find(parent[a]);
    }
    
    static boolean union(int a, int b) {
    	int rootA = find(a);
    	int rootB = find(b);
    	if (rootA == rootB) return false;
    	parent[rootA] = rootB;
    	return true;
    }
}
