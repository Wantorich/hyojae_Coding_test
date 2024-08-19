import java.util.*;

public class Solution {
	static int N, L;
	static int [] taste, calories;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			L = sc.nextInt();
			
			taste = new int[N];
			calories = new int[N];
			
			for (int i = 0; i < N; i++) {
				taste[i] = sc.nextInt();
				calories[i] = sc.nextInt();
			}
			
			int result = 0;
			
			// 햄버거 재료를 1 ~ N개 뽑는 조합을 np로 짬
			for (int i = 1; i <= N; i++) {
				int [] sel = new int[N];
				for (int j = 0; j < i; j++) {
					sel[N-1-j] = 1;
					// np로 조합만들기 호출
				}
				do {
					// sel에 있는 index로 햄버거를 선택하고 max calories 넘지 않는지 확인하고
					// 최대값 갱신
					int taste_sum = 0, cal_sum = 0;
					for (int k = 0; k < sel.length; k++) {
						if (sel[k] == 1) {
							// 뽑힌거
							taste_sum += taste[k];
							cal_sum += calories[k];
						}
					}
					if (cal_sum <= L) 
						result = Math.max(result, taste_sum);
				} while (np(sel));
			}
			System.out.printf("#%d %d\n", t, result);
		}
		
		sc.close();
	}

	// 0 0 0 1
	// 0 0 1 0
	// 0 1 0 0
	// 1 0 0 0
	
	// 0 0 1 1
	// 0 1 1 0 -> 0 1 0 1
	// 0 1 1 0
	// 1 1 0 0 -> 1 0 0 1
	// 1 0 1 0
	// 1 1 0 0
	
	
	private static boolean np(int[] sel) {
		int N = sel.length;
		
		// step 1. 꼭대기 찾기
		int i = N-1;
		while (i > 0 && sel[i-1] >= sel[i]) i--;
		
		if (i == 0) return false; // 내림차순으로 배열이 정리되서 더 진행할 수가 없음
		
		// step 2. 꼭대기 앞 교환 위치에 교환할 값(i-1 위치 값보다 큰 값중 가장 작은 값) 자리 찾기
		// 첫번째로 본놈이 i-1 위치의 값보다 큰 값중에 가장 작은 값임 -> 왜일까? ㅋㅋ
		// 답 -> 꼭대기 찾을때 가장 높은 값을 찾는데 만약 꼭대기 이후로 낮아졌다 커지는 값이 있으면
		// 그 값이 꼭대기가 되기때문에 전제에 모순이 생김
		// 즉 꼭대기부터 오른쪽까지는 무조건 내림차순인게 보장됌
		int j = N-1;
		while (sel[i-1] >= sel[j]) j--;
		
		// j에는 스왑할 값의 위치가 저장돼있음
		// step 3. 두 위치의 수 교환
		swap(sel, i-1, j);
		
		// step 4. 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦
		// 처음 실행하면 꼭대기가 맨 뒤로 가짐
		// 꼭대기 바로 오른쪽은 꼭대기보다는 작고 나머지보다는 더 큰값임
		// 맨 오른쪽은 이미 꼭대기가 있으니까 맨 오른쪽 전값과 스왑하면 됌
		int k = N-1;
		while (i < k) {
			swap(sel, i++, k--);
		}
		
		return true;
	}
	
	static void swap(int [] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}

