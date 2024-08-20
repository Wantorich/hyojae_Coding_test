import java.util.*;

public class Solution {
	static int N;
	static int [] h;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			h = new int[N];
			for (int i = 0; i < N; i++) h[i] = sc.nextInt();
			
			int s = 0;
			int result = 0;
			while (s < N - 1) {
				int l = 0, r = 0;
				while (s < N-1 && h[s] < h[s+1]) {
					l++;
					s++;
				}
				// s는 꼭대기 
				// 여기서 부터 내리막을 찾음
				
				while (s < N-1 && h[s] > h[s+1]) {
					r++;
					s++;
				}
				
				result += l * r; // 왼쪽 산 출발 지점 개수와 오른쪽 도착 지점 개수를 곱함
			}
			// 산을 올라갔다 내려오려면 최소 점 3개가 필요함
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}
}
