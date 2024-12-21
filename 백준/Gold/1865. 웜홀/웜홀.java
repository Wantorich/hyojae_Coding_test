import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		int[][] adjMatrix;
		StringBuilder sb = new StringBuilder();
		int INF = Integer.MAX_VALUE;
		
		O:for (int tc = 1; tc <= testCase; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int W = sc.nextInt();
			
			adjMatrix = new int[N+1][N+1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) adjMatrix[i][j] = 0;
					else adjMatrix[i][j] = INF;
				}
			}
			
			int from, to, weight;
			for (int i = 1; i <= M+W; i++) {
				from = sc.nextInt();
				to = sc.nextInt();
				weight = sc.nextInt();
				
				if (i <= M) {
					adjMatrix[from][to] = Math.min(adjMatrix[from][to], weight);
					adjMatrix[to][from] = Math.min(adjMatrix[to][from], weight);
				} else {
					adjMatrix[from][to] = Math.min(adjMatrix[from][to], -weight);
				}
			}
			
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						int prev = adjMatrix[i][k];
						int next = adjMatrix[k][j];
						if (prev == INF || next == INF) 
							continue;
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], prev + next); 
//						System.out.println("prev : " + prev + ", next : " + next);
					}
				}
			}
			
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[i][i] < 0) {
//					for (int [] row : adjMatrix)
//						System.out.println(Arrays.toString(row));
					sb.append("YES").append("\n");
					continue O;
				}
			}
			
//			for (int [] row : fw)
//				System.out.println(Arrays.toString(row));
			sb.append("NO").append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
	
	static int getMin(List<Integer> list) {
		int result = Integer.MAX_VALUE;
		for (int num : list) {
			result = Math.min(result, num);
		}
		return result;
	}
}

