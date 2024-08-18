import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, SIZE = 100;
	static boolean [] v;
	static List<Integer>[] list;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = 10;

		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			v = new boolean[SIZE+1];
			list = new ArrayList[SIZE+1];
			
			for (int i = 1; i <= SIZE; i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				list[s].add(e);
			}
			
			int result = bfs(start);
			System.out.printf("#%d %d\n", t, result);
		}
		
	}


	private static int bfs(int curr) {
		v[curr] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(curr);
		int max = -1, qSize = 0;
		
		while (!q.isEmpty()) {
			max = Integer.MIN_VALUE;
			qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int next = q.poll();
				max = Math.max(max, next);
				List<Integer> s_list = list[next];
				
				for (int vertex : s_list) {
					if (!v[vertex]) {
						v[vertex] = true;
						q.offer(vertex);
					}
				}
			}
		}
		return max;
	}

}
