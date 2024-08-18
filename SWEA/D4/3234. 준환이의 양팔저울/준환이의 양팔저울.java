import java.util.*;

public class Solution {
	static int [] weights;
	static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			weights = new int[N];
			for (int i = 0; i < N; i++) weights[i] = sc.nextInt();
			
			result = 0;
			permutation(new int[N], 0, new boolean[N]);
			System.out.printf("#%d %d\n", t, result);
		}
		
		sc.close();
	}

	private static void permutation(int[] sel, int k, boolean[] v) {
		if (k == sel.length) {
//			System.out.println(Arrays.toString(sel));
			placeWeights(sel, 0, 0, new boolean[sel.length], 0); // 무게추 배열, 좌 무게 , 우 무게
			return;
		}
		
		for (int i = 0; i < weights.length; i++) {
			if (v[i]) continue;
			v[i] = true;
			sel[k] = weights[i];
			permutation(sel, k+1, v);
			v[i] = false;
		}
		
	}

	private static void placeWeights(int[] sel, int left, int right, boolean [] v, int k) {
		if (right > left) {
//			System.out.println("left : " + left + ", right : " + right);
			return;
		}
		
		if (k == sel.length) {
			// 조건 위배없이 도달한 경우
			result++; // 경우의 수 한가지 추가
			return;
		}
		
        placeWeights(sel, left + sel[k], right, v, k+1);
        placeWeights(sel, left, right + sel[k], v, k+1);
	}

}
