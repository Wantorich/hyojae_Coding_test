import java.util.*;

public class Solution {
	static int N, M, C, max;
	static int [][] grid;
	static List<int[]> honeyList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			grid = new int[N][N];
			honeyList = new ArrayList<int[]>();
			
			for (int i = 0; i < N*N; i++) 
				grid[i/N][i%N] = sc.nextInt();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					int [] tmp = new int[M];
					int idx = 0;
					for (int k = j; k < j + M; k++) {
						// M개 뽑음
						tmp[idx++] = grid[i][k];
					}
					// 한번 루프를 돌릴때마다 max를 0으로 초기화하고 이번 행동에서 얻을 수 있는 가장 큰 산출을 저장
					max = 0; 
					calHoney(0, 0, tmp, new int[tmp.length]);
					
					// ArrayList에 값을 저장
					honeyList.add(new int[] {i, j, max});
				}
			}
			
			// List에 있는 산출 값들을 내림차순으로 정렬
			Collections.sort(honeyList, (h1, h2) -> Integer.compare(h2[2], h1[2]));
			
			// 첫번째 있는 값이 가장 큰 값이므로 첫번째 일꾼은 무조건 이를 선택함
			int result = honeyList.get(0)[2];
			
			// 첫번째 일꾼이 선택한 범위를 r과 c에 저장함
			int r = honeyList.get(0)[0];
			int c = honeyList.get(0)[1];
			
			// 두번째 인덱스부터 첫번째 일꾼과 겹치지 않는 값중에 가장 큰 값을 찾음
			for (int i = 1; i < honeyList.size(); i++) {
				int nr = honeyList.get(i)[0];
				int nc = honeyList.get(i)[1];
				
				if (r == nr) {
					// 같은 행에 있을때는 c부터 c+M 전까지와 nc의 시작점이 안 겹쳐야함
					if (nc >= c + M) { // 범위가 안겹친다면 뽑음
						result += honeyList.get(i)[2];
						break; // 찾았으니 탈출
					}
				} else {
					// 다른 행에서 찾을 경우에는 열을 고려할 필요가 없음
					result += honeyList.get(i)[2];
					break;
				}
			}
			
//			for (int[] money : honeyList) {
//				System.out.println(money[0] + " " + money[1] + " " + money[2]);
//			}
			
			System.out.printf("#%d %d\n", t, result);
		}
		
		sc.close();
	}

	// 한번에 꿀통에 담을 수 있는 범위내에서 가장 큰 산출을 낼 수 있는 값을 찾음
	// 한 꿀통에 대해 선택하든 안선택하는 2가지 경우만 있으므로, 부분조합의 코드를 구현
	private static void calHoney(int k, int idx, int[] arr, int[] sel) {
		if (k == arr.length) {
			// 가능한 꿀의 조합중 큰 것을 여기서 정함
			// sel 배열의 원소의 합이 C 이하여야함
			int sum = 0; // sum은 뽑은 원소의 합
			int pow_sum = 0; // pow_sum 은 각 원소의 제곱의 합
			
			for (int i = 0; i < sel.length; i++) {
				sum += sel[i];
				pow_sum += sel[i] * sel[i];
			}
			
			// sum이 C보다 작으면서 pow_sum이 가장 큰 것을 max 값에 저장
			max = sum <= C && pow_sum > max ? pow_sum : max;
			return;
		}
		
		if (idx == arr.length) return;
		
		sel[k] = arr[idx];
		calHoney(k+1, idx+1, arr, sel);
		
		sel[k] = 0;
		calHoney(k+1, idx+1, arr, sel);
	}
}

// ** 시간 복잡도 **
// 전체 리스트에 담아야하는 경우의 수는 N*N - M만큼임 -> 최대 경우의 수는 100 - 5 = 95개
// 한번의 행동에서 멱집합에서 나올수 있는 경우의 수는 2^M -> M은 5가 최대이므로 32개
// 한번의 행동마다 두가지 행동을 하므로 95 * 32 = 3040
// 정렬된 리스트에서 값을 찾는 경우는 O(N) 즉 최대 3040