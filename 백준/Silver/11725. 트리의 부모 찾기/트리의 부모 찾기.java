import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[] visit = new boolean[N+1];
		List<Integer>[] adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) 
			adjList[i] = new ArrayList<Integer>();
		
		for (int i = 1; i < N; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		int[] parents = new int[N+1];
		dfs(1, adjList, visit, parents);
		
		for (int i = 2; i <= N; i++)
			System.out.println(parents[i]);
		sc.close();
	}

	private static void dfs(int curr, List<Integer>[] adjList, boolean[] visit, int[] parents) {
		for (Integer next : adjList[curr]) {
			if (visit[next]) continue;
			visit[next] = true;
			parents[next] = curr;
			dfs(next, adjList, visit, parents);
		}
	}
}
