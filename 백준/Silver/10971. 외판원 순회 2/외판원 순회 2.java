import java.util.*;

public class Main {
	static int V, adjMatrix[][], result = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		adjMatrix = new int[V+1][V+1];
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) adjMatrix[i][j] = sc.nextInt();
		}
		
		permutation(0, new int[V], new boolean[V+1]);
		System.out.println(result);
		
		sc.close();
	}


	private static void permutation(int k, int[] sel, boolean[] visit) {
		if (k == sel.length) {
			int sum = 0;
			for (int i = 0; i < sel.length-1; i++) {
				sum += adjMatrix[sel[i]][sel[i+1]];
			}
			if (adjMatrix[sel[sel.length-1]][sel[0]] == 0) return; // 마지막에서 출발점으로 이어져있지 않음
			
			sum += adjMatrix[sel[sel.length-1]][sel[0]];
			result = Math.min(result, sum);
			return;
		}
		
		for (int i = 1; i <= V; i++) {
			if (visit[i]) continue;
			// k-1번과 k번이 연결되어있는지 확인
			if (k > 0 && adjMatrix[sel[k-1]][i] == 0) continue;
			visit[i] = true;
			sel[k] = i;
			permutation(k+1, sel, visit);
			visit[i] = false;
		}
	}
}

