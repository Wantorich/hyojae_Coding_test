import java.util.*;
import java.io.*;

class Monster {
	int r, c, life;

	Monster(int r, int c, int life) {
		super();
		this.r = r;
		this.c = c;
		this.life = life;
	}

	@Override
	public String toString() {
		return "Monster [r=" + r + ", c=" + c + ", life=" + life + "]";
	}
	
	public int distance(int br, int bc) {
		int distance = Math.abs(br - r) + Math.abs(bc - c);
		return distance;
	}
}

public class Main {
	static int N, M, D, result;
	static int [][] grid;
	static List<Monster> mList = new ArrayList<Monster>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		grid = new int[N][M];
		result = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] == 1) {
					// monster 추가
					mList.add(new Monster(i, j, 1));
				}
			}
		}
		
		combination(new int[3], 0, 0);
		System.out.println(result);
		
		sc.close();
	}

	

	private static void combination(int[] sel, int cnt, int start) {
		if (cnt == sel.length) {
//			System.out.println(Arrays.toString(sel));
			// 여기가 궁수 위치 뽑은거 (col 위치, row는 N으로 고정)
			// 여기서 시뮬레이션 시작
			simulation(sel);
			return;
		}
		
		for (int i = start; i < M; i++) {
			sel[cnt] = i;
			combination(sel, cnt+1, i+1);
		}
	}



	private static void simulation(int [] bows) {
		// 몬스터 리스트를 돌면서 현재 궁수가 쏠수있는지 확인하고
		// 죽었으면 데스카운트 증가시키고 리스트에서 뺌
		// 한 턴마다 몬스터는 아래로 한칸씩 이동
		// 만약 몬스터가 캐슬에 도달하는 경우에도 리스트에서 제거
		// 몬스터 원본값을 보존해야하니까 복사
		// 가장 왼쪽것을 때리는걸 구현안했네
		int deathCnt = 0;
		List<Monster> copyMList = new ArrayList<Monster>();
		for (Monster m : mList) {
			copyMList.add(new Monster(m.r, m.c, m.life));
		}
		
		while (copyMList.size() > 0) {
			for (int i = 0; i < bows.length; i++) {
				// 현재 궁수의 위치로 몬스터 정렬
//				Collections.sort(copyMList, (o1, o2) -> {
//					Monster m1 = (Monster) o1;
//					Monster m2 = (Monster) o2;
//					int result =  Integer.compare(m1.distance(N, bows[i]), m2.distance(N, bows[i]));
//					return result == 0 ? Integer.compare(m1.c, m2.c) : result;
//				});
				int min_idx = -1;
				int min_dis = Integer.MAX_VALUE;
				for (int j = 0; j < copyMList.size(); j++) {
					Monster m = copyMList.get(j);
					int currDis = m.distance(N, bows[i]);
					if (currDis > D) continue; // 거리가 안닿음
					
					if (currDis < min_dis) {
						min_dis = currDis;
						min_idx = j;
					} else if (currDis == min_dis) {
						// 누가 더 왼쪽에 있는지 판단
						if (m.c < copyMList.get(min_idx).c) {
							min_idx = j;
						}
					}
				} // 여기까지 돌리면 누굴 때릴지 결정
				
				if (min_idx != -1) {
					// 쏠애가 있을때만
					copyMList.get(min_idx).life--; // 공격
				}
			} // 궁수의 공격이 끝남
			// 여기서 life가 0이하인 놈들은 없애버림
			
			for (int j = copyMList.size()-1; j >= 0; j--) {
				Monster m = copyMList.get(j);
				// 생명 없는놈은 죽고
				if (m.life <= 0) {
					copyMList.remove(j);
//					System.out.println("죽은 몬스터 번호 : " + j + " " + m);
					deathCnt++;
				} else {
					// 생명 있는놈은 한칸 내려옴
					m.r++;
					if (m.r == N) {
						// 끝에 도달하면
						copyMList.remove(j);
					}
				}
			}
			
			// 몬스터 죽인 후
//			for (Monster m : copyMList) {
//				System.out.println(m);
//			}
		}
		result = Math.max(result, deathCnt);
//		System.out.println("deathCnt : " + deathCnt);
	}



	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

}
