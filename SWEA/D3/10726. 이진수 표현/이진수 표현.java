import java.util.*;

public class Solution {
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			boolean result = true;
			for (int i = 0; i < N; i++) {
				if ((M & (1 << i)) == 0) {
					result = false;
					break;
				}
			}
			System.out.printf("#%d %s\n", t, result ? "ON" : "OFF");
		}
		sc.close();
	}
}
