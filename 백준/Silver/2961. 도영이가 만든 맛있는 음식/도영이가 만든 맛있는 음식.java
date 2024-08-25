import java.util.*;

public class Main {
	static int N;
	static int [] sour;
	static int [] bitter;
	static long result = Long.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sour = new int[N];
		bitter = new int[N];
		
		for (int i = 0; i < N; i++) {
			sour[i] = sc.nextInt();
			bitter[i] = sc.nextInt();
		}
		
		subset(0, new boolean[N]);
		System.out.println(result);
		sc.close();
	}

	private static void subset(int k, boolean[] sel) {
		if (k == sel.length) {
			long sum = 0, mul = 1;
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) {
					sum += bitter[i];
					mul *= sour[i];
				}
			}
			
			if (sum != 0) {
				// 하나도 안고르는 경우는 고려하지않음
				result = Math.min(result, Math.abs(mul - sum));
			}
			
			return;
		}
		
		sel[k] = true;
		subset(k+1, sel);
		
		sel[k] = false;
		subset(k+1, sel);
	}
}
