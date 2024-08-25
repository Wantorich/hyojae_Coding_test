import java.util.*;

public class Main {
	static int N, grid[][], cnt, result = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		grid = new int[N][10];
		
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= 9; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		// 1번선수를 4번타자로 정했다 -> sel[3] = 0; -> 이게 백트래킹인가보네
		// 2번선수터 9번선수까지의 순서만 정하면 됌
		// 타순을 저장하고
		battOrdering(1, new int[10], new boolean[10]);
//		System.out.println("cnt : " + cnt);
		
		// 시뮬레이션
		System.out.println(result);
		
		sc.close();
	}

	private static void battOrdering(int k, int[] sel, boolean[] v) {
		if (k == sel.length) {
			// 타순 다 뽑았음
			// 게임 시뮬 돌리기
			cnt++;
//			System.out.println(Arrays.toString(sel));
			game(sel);
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (v[i]) continue;
			v[i] = true;
			sel[k] = i;
			
			if (i == 1 && k != 4) { // 4번 타순에 1번타자가 들어오게 나머지 경우 가지치기
				v[i] = false;
				continue;
			}
			
			battOrdering(k+1, sel, v);
			v[i] = false;
		}
	}
	
	private static int baseRunning(int [] base, int size) {
		int earnScore = 0;
		
		for (int i = base.length-1; i >= 1; i--) {
			if (base[i] == 1) {
				// 주자가 루상에 있으면 size만큼 이동해줌
				if (i + size >= base.length) {
					// 홈으로 들어옴
					earnScore++;
				} else {
					base[i+size] = 1; // 주자 진루
				}
				base[i] = 0; // 주자 초기화
			} 
		}
		
		// 타자 진루
		if (size == 4) earnScore++;
		else base[size] = 1;
		
		return earnScore;
	}
	
	// 음 다음 이닝 넘어갈때 base 초기화를 안해준듯

	private static void game(int[] order) {
		// order를 기반으로 시뮬레이션
		int ining = 0;
		int pointSum = 0; // 토탈 점수
		int startHitter = 1;
		while (ining < N) { // 모든 이닝을 다 할때까지
			// 한 이닝은 3아웃이 잡히면 끝 그전까지 돔
			int outCnt = 0;
			int [] base = new int[4];
			// 값이 0이면 아웃카운트 1 증가
			// 타순을 돌면서 점수를 냄
			// order[1] 부터 order[9]까지가 타순
			for (int i = startHitter;; i = (i+1) % 10) {
				if (i == 0) continue; // 0번 타자는 없음
				int hitter = order[i]; // 몇번 타자인지
				
				// 안타쳤는지 아웃인지
				// 루 상을 계산해야해
				int hitterResult = grid[ining][hitter];
				
				if (hitterResult == 0) {
					outCnt++;
				} else {
					pointSum += baseRunning(base, hitterResult);
				}
				
				if (outCnt == 3) {
					startHitter = (i+1) % 10; // 다음 타자부터 다음 이닝 시작
					break; // 3아웃되면 빠져나감
				}
			}
				
			// 다음 이닝
			ining++;
		}
		
		result = Math.max(result, pointSum);
	}
}
