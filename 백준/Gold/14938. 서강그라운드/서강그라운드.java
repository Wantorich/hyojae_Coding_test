import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int INF = Integer.MAX_VALUE;
		int N = sc.nextInt();
		int M = sc.nextInt();
		int R = sc.nextInt();
		
		// 모든 지점으로 떨어져봐서 거리를 다 구하면
		// 몇개 먹을 수 있는지 답이 나오네
		// 플로이드네
		int[][] graph = new int[N+1][N+1];
		for (int[] row : graph)
			Arrays.fill(row, INF);
		for (int i = 1; i <= N; i++)
			graph[i][i] = 0;
		
		int[] items = new int[N+1];
		for (int i = 1; i <= N; i++)
			items[i] = sc.nextInt();
		
		for (int i = 0; i < R; i++) {
			int from = sc.nextInt();
			int next = sc.nextInt();
			int weight = sc.nextInt();
			graph[from][next] = weight;
			graph[next][from] = weight;
		}
		
		// floid-warshal
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (graph[i][k] == INF) continue;
				for (int j = 1; j <= N; j++) {
					if (graph[k][j] == INF) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		long result = 0;
		for (int i = 1; i <= N; i++) {
			long subsum = 0;
			for (int j = 1; j <= N; j++) {
				subsum += graph[i][j] <= M ? items[j] : 0;
			}
			result = Math.max(result, subsum);
		}
		
		System.out.println(result);
		sc.close();
	}
}
