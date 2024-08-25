import java.util.*;

public class Solution {
	static int N;
	static int [][] synergy;
	static long result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			synergy = new int[N+1][N+1];
			result = Long.MAX_VALUE;
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) synergy[i][j] = sc.nextInt();
			}
			
			// N개 중에 N/2를 뽑아 A, 나머지를 B에 할당해야하니 조합을 써야함
			// 최대 16C8
			combination(0, 0, new int[N/2]);
			System.out.printf("#%d %d\n", t, result);
		}
		
		sc.close();
	}

	private static void combination(int idx, int k, int[] sel) {
		if (idx == N+1) return; // 뽑을 식재료 범위를 넘어섬
		
		// base part
		if (k == sel.length) { // 식재료 다 뽑은 경우
			// v에서 true는 A팀, false는 B팀
			boolean [] v = new boolean[N+1];
			for (int i = 0; i < sel.length; i++) {
				v[sel[i]] = true; // A팀 체크
			}
			
			// 각 행렬에서 각 팀 안에서 서로 속하있는 재료는 시너지를 더해야하기때문에
			// 반복문 2개 쓰면 다 탐색할 수 있음
			long a_sum = 0, b_sum = 0;
			for (int i = 0; i < v.length; i++) {
				for (int j = 0; j < v.length; j++) {
					if (v[i] != v[j]) continue; // 팀이 다른건 시너지 안 더함
					
					if (v[i]) // true는 A팀
						a_sum += synergy[i][j];
					else  // false는 B팀
						b_sum += synergy[i][j];
				}
			} // 각 팀의 시너지 합 계산 완료
			long diff = Math.abs(a_sum - b_sum);
			result = Math.min(result, diff);
			
			return;
		}
		
		// induction part
		sel[k] = idx;
		combination(idx+1, k+1, sel); // 뽑는 경우
		combination(idx+1, k, sel); // 안뽑는 경우
	}
}

