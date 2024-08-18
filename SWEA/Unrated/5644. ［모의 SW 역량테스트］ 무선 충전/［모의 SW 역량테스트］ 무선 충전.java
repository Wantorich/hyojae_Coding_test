import java.util.*;
import java.io.*;
class Wifi {
	int r, c, range, power;
	Wifi(int c, int r, int range, int power) {
		this.r = r;
		this.c = c;
		this.range = range;
		this.power = power;
	}
	
	boolean isInRange(int pr, int pc) {
		int distance = Math.abs(pr - r) + Math.abs(pc - c);
		if (distance <= range) return true;
		return false;
	}

	@Override
	public String toString() {
		return "Wifi [r=" + r + ", c=" + c + ", range=" + range + ", power=" + power + "]";
	}
	
}

class Player {
	int r, c;
	Player(int c, int r) {
		this.r = r;
		this.c = c;
	}
	
	void move(int dir) {
		switch(dir) {
			case 1:
				r -= 1; break;
			case 2:
				c += 1; break;
			case 3:
				r += 1; break;
			case 4:
				c -= 1; break;
		}
	}
	
	List<Wifi> searchWifi(List<Wifi> list) {
		List<Wifi> result = new ArrayList<Wifi>();
		for (Wifi wifi : list) {
			if (wifi.isInRange(r, c)) {
				result.add(wifi);
			}
		}
		return result;
	}
}

public class Solution {
	static int [] movA, movB;
	static List<Wifi> wifiList;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			int M = sc.nextInt();
			int A = sc.nextInt();
			wifiList = new ArrayList<Wifi>();
			
			movA = new int[M+1];
			movB = new int[M+1];
			
			// 첫턴은 이동안하게 즉 시작하자 마자 바로 탐색
			movA[0] = 0; movB[0] = 0;
			
			for (int i = 1; i <= M; i++) movA[i] = sc.nextInt();
			for (int i = 1; i <= M; i++) movB[i] = sc.nextInt();
			
			for (int i = 0; i < A; i++) {
				wifiList.add(new Wifi(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
			} 
			
			Player playerA = new Player(1,1);
			Player playerB = new Player(10,10);
			int result = 0;
			
			// 처음시작할때거를 안넣어줬네
			for (int i = 0; i <= M; i++) {
				// 이동 커맨드에 따라 이동함
				playerA.move(movA[i]);
				playerB.move(movB[i]);
				List<Wifi> wifiA = playerA.searchWifi(wifiList);
				List<Wifi> wifiB = playerB.searchWifi(wifiList);
				
				// wifi 확인 디버깅
//				System.out.println("---wifiA List---");
//				for (Wifi wifi : wifiA) {
//					System.out.println(wifi);
//				}
//				
//				System.out.println("---wifiB List---");
//				for (Wifi wifi : wifiB) {
//					System.out.println(wifi);
//				}
				
				int max_sum = Integer.MIN_VALUE;
				if (wifiA.size() > 0 && wifiB.size() > 0) {
					for (int j = 0; j < wifiA.size(); j++) {
						Wifi wa = wifiA.get(j);
						for (int k = 0; k < wifiB.size(); k++) {
							Wifi wb = wifiB.get(k);
							
							// 와이파이가 같은경우와 다른 경우
							if (wa.r == wb.r && wa.c == wb.c) {
								// 둘이 같은 와이파이 쓰면 나눠가지니까 결국 와이파이 한개의 파워만 전달됌
								max_sum = Math.max(max_sum, wa.power);
							} else {
								max_sum = Math.max(max_sum, wa.power + wb.power);
							}
						}
					}
					
				} else if (wifiA.size() > 0 && wifiB.size() == 0) {
					// A만 존재하는 경우
					for (int j = 0; j < wifiA.size(); j++) {
						max_sum = Math.max(max_sum, wifiA.get(j).power);
					}
					
				} else if (wifiA.size() == 0 && wifiB.size() > 0) {
					// B만 존재하는 경우
					for (int j = 0; j < wifiB.size(); j++) {
						max_sum = Math.max(max_sum, wifiB.get(j).power);
					}
				} else {
					// 둘다 0인 경우
					max_sum = 0;
				}
				
				result += max_sum;
				
//				System.out.println("curr result : " + result);
				
				// 와이파이 이용가능한 것들을 비교
				// 서로 같은 와이파이면 파워를 반띵해야하고, 즉 N * M의 모든 경우의수를 계산해 최댓값을 적용
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}
}