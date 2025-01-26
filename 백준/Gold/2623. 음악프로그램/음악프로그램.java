import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	List<Integer>[] adjList = new ArrayList[N+1];
    	for (int i = 0; i < adjList.length; i++)
    		adjList[i] = new ArrayList<>();
    	
    	int[] inDegree = new int[N+1];
    	
    	int loop, from, to;
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		loop = Integer.parseInt(st.nextToken());
    		from = Integer.parseInt(st.nextToken());
    		for (int j = 0; j < loop-1; j++) {
    			to = Integer.parseInt(st.nextToken());
    			adjList[from].add(to);
    			inDegree[to]++;
    			from = to;
    		}
    	}
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	for (int i = 1; i <= N; i++) {
    		if (inDegree[i] == 0)
    			pq.offer(i);
    	}
    		
    	Queue<Integer> result = new ArrayDeque<>();
    	int curr;
    	while (!pq.isEmpty()) {
    		curr = pq.poll();
    		result.offer(curr);
    		for (Integer next : adjList[curr]) {
    			if (--inDegree[next] == 0)
    				pq.offer(next);
    		}
    	}
    	
    	if (result.size() != N) 
    		System.out.println(0);
    	else 
    		System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining("\n")).toString());
    }
}
