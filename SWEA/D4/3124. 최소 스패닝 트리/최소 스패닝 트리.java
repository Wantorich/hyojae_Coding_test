import java.util.*;

public class Solution {
	static int N, M, parents[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for (int t = 1; t <= tc; t++) {
			
			N = sc.nextInt();
			M = sc.nextInt();
			
			List<int[]> edgeList = new ArrayList();
			for (int i = 0; i < M; i++) 
				edgeList.add(new int[] {sc.nextInt(), sc.nextInt(), sc.nextInt()});
			
			Collections.sort(edgeList, (e1, e2) -> Integer.compare(e1[2], e2[2]));
			makeSet();
			
			long sum = 0; long cnt = 0;
			for (int [] edge : edgeList) {
				if (union(edge[0], edge[1])) {
					sum += edge[2];
					cnt++;
				}
				
				if (cnt == N-1) break;
			}
			
			System.out.printf("#%d %d\n", t, sum);
		}
		
		sc.close();
	}
	
	static void makeSet() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) parents[i] = i;
	}
	
	static int find(int v) {
		if (parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int i, int j) {
		if (find(i) == find(j)) return false;
		parents[find(j)] = find(i);
		return true;
	}
}
