import java.util.*;

public class Main {
	static int N, result = Integer.MAX_VALUE;
	static int weights[][], wellCosts[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		weights = new int[N + 1][N + 1];
		wellCosts = new int[N+1];
		
		int minCost = Integer.MAX_VALUE, minIdx = -1;
		for (int i = 1; i<= N; i++) {
			wellCosts[i] = sc.nextInt();
			if (minCost > wellCosts[i]) {
				minCost = wellCosts[i];
				minIdx = i;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int val = sc.nextInt();
				if (val == 0)
					continue;
				weights[i][j] = val;
			}
		}

//		for (int[] row : weights) {
//			System.out.println(Arrays.toString(row));
//		}

		int result = prim(minIdx, minCost);
		System.out.println(result);
		sc.close();
	}

	// 우물을 가장 비용적게 팔수있는걸 파고
	// N돌면서 해당 우물로 물을 길수있는건 다 옮기고
	//

	private static int prim(int v, int wellCost) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> Integer.compare(p1[1], p2[1]));
		pq.offer(new int[] { v, wellCost});
		boolean[] visit = new boolean[N + 1];
		int weightSum = 0;
		
		while (!pq.isEmpty()) {
			int[] field = pq.poll();
			int curr = field[0];
			int weight = field[1];

			if (visit[curr])
				continue;
			
			weightSum += weight;
			visit[curr] = true; // 현재 논에 최소 비용으로 물 대줌

			for (int j = 1; j <= N; j++) {
				if (!visit[j]) {
					// 방문 안했으면 q에 넣어줌
					// 물을 길러오는 비용이랑 직접 우물파는 비용이랑 비교
					int cost = weights[curr][j] > wellCosts[j] ? wellCosts[j] : weights[curr][j];
					pq.offer(new int[] {j, cost});
				}
			}
			
//			System.out.println("weightSum : " + weightSum);
		}

		return weightSum;
	}
}