import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int [] scores, cals;
	static int result = 0, L;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			scores = new int[N];
			cals = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}
			recursive(0, new boolean[N]);
			System.out.printf("#%d %d\n", t, result);
            result = 0;
		}
	}
	
	static void recursive(int idx, boolean [] sel) {
		if (idx == sel.length) {
			int score_sum = 0;
			int cal_sum = 0;
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) {
					score_sum += scores[i];
					cal_sum += cals[i];
				}
			}
			if (cal_sum <= L) {
				result = Math.max(result, score_sum);
			}
			return;
		}
		
		// 선택 경우
		sel[idx] = true;
		recursive(idx+1, sel);
		
		// 선택 안하는 경우
		sel[idx] = false;
		recursive(idx+1, sel);
	}

}

/*
부분집합같은데
*/