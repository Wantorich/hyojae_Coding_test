import java.util.*;

public class Main {
  static int[][] adjMatrix;
  static int N, W, costs[], result;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    for (int t = 1; t <= tc; t++) {
      N = sc.nextInt();
      int K = sc.nextInt();
      
      adjMatrix = new int[N][N];
      int[] inDegree = new int[N];
      result = Integer.MAX_VALUE;
      costs = new int[N];
      for (int i = 0; i < N; i++) costs[i] = sc.nextInt();
      
      for (int i = 0; i < K; i++) {
        int from = sc.nextInt() - 1;
        int to = sc.nextInt() - 1;
        adjMatrix[from][to] = 1;
        inDegree[to]++;
      }
      
      W = sc.nextInt() - 1;
      Queue<Integer> q = new ArrayDeque<Integer>();
      int[] memo = new int[N];
      Arrays.fill(memo, Integer.MAX_VALUE);
      
      for (int i = 0; i < N; i++) {
        if (inDegree[i] == 0) {
        	q.offer(i);
        	memo[i] = costs[i];
        }
      }
      
      game(q, inDegree, memo);
      System.out.printf("%d\n", result);
    }
    sc.close();
  }
  
  private static void game(Queue<Integer> q, int[] inDegree, int[] memo) {
	  while (!q.isEmpty()) {
		  int v = q.poll();
		  
		  if (v == W) {
			  // 목적지 도착
			  result = Math.min(result, memo[v]);
			  return;
		  }
		  
		  for (int j = 0; j < N; j++) {
			  if (adjMatrix[v][j] == 0) continue;
			  inDegree[j]--;
			  if (inDegree[j] == 0) {
				  // 진입차수 계산
				  int prevTime = 0;
				  for (int k = 0; k < N; k++) {
					  if (adjMatrix[k][j] == 1)
						  prevTime = Math.max(prevTime, memo[k]);
				  }
				  memo[j] = prevTime + costs[j];
				  q.offer(j);
//				  game(q, inDegree, memo);
			  } 
		  }
	  }
  }
}
