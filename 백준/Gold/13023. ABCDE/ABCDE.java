import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] adjList;
	static boolean[] visit;
	static boolean find;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adjList = new ArrayList[N];
		
		for (int i = 0; i < N; i++) adjList[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (visit[i] || adjList[i].size() == 0) continue;
			visit[i] = true;
			dfs(i, 1);
			visit[i] = false;
			if (find) {
				System.out.println(1);
				return;
			}
		}
		
		System.out.println(0);
		sc.close();
	}

	// 전부 다 달라야한다는 조건
	
	private static void dfs(int v, int cnt) {
		if (cnt >= 5) {
			find = true;
			return;
		}
		
		for (Integer next : adjList[v]) {
			if (visit[next]) continue;
			// history에 존재한다면
//			if (history.indexOf(""+next) != -1) continue;
			
			visit[next] = true;
			dfs(next, cnt+1);
			visit[next] = false;
		}
	}
}
