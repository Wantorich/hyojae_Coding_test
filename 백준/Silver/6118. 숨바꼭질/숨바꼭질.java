import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static boolean [] v;
	static List<Integer>[] list;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new boolean[N+1];
		list = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[s].add(e);
			list[e].add(s);
		}
		
		bfs(1);
	}


	private static void bfs(int curr) {
		v[curr] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(curr);
		int min = -1, qSize = 0, dept = 0;
		
		while (!q.isEmpty()) {
			min = Integer.MAX_VALUE;
			qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int next = q.poll();
				min = Math.min(min, next);
				List<Integer> s_list = list[next];
				
				for (int vertex : s_list) {
					if (!v[vertex]) {
						v[vertex] = true;
						q.offer(vertex);
					}
				}
			}
			dept++;
		}
		System.out.printf("%d %d %d", min, dept-1, qSize);
	}

}
