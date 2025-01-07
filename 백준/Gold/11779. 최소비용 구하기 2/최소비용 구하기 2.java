import java.util.*;
import java.io.*;

class Edge {
	int to;
	long weight;
	Edge (int to, long weight) {
		this.to = to;
		this.weight = weight;
	}
	
	long getWeight() {
		return this.weight;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		List<int[]>[] adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();
		
		// saved edge
		int from, to, weight;
		for (int i = 0; i < M; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			weight = sc.nextInt();
			adjList[from].add(new int[] {to, weight});
		}
		
		// destination
		int start = sc.nextInt();
		int end = sc.nextInt();
		
		long[] dis = new long[N+1];
		int[] prev = new int[N+1];
		
		Arrays.fill(prev, -1);
		Arrays.fill(dis, Long.MAX_VALUE);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
		pq.offer(new Edge(start, 0));
		dis[start] = 0;
		
		int cnt = 0;
		while (!pq.isEmpty()) {
			
			Edge curr = pq.poll();
			if (curr.weight > dis[curr.to]) continue;
			cnt++;
			
			for (int[] connect : adjList[curr.to]) {
				if (dis[connect[0]] > dis[curr.to] + connect[1]) {
					dis[connect[0]] = dis[curr.to] + connect[1];
					pq.offer(new Edge(connect[0], dis[connect[0]]));
					prev[connect[0]] = curr.to; // tracking
				}
			}
		}
		
		System.out.println(dis[end]); // 최소 비용 거리
		
		int pointer = end;
		ArrayDeque<Integer> stack = new ArrayDeque<>(); 
		stack.push(end);
		
		while (prev[pointer] != -1) {
			stack.push(prev[pointer]);
			pointer = prev[pointer];
		}
		
		System.out.println(stack.size());
		stack.forEach(path -> System.out.print(path + " "));
		
		sc.close();
	}

}
