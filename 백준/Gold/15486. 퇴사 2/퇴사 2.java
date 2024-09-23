import java.io.*;
import java.util.*;

public class Main {
	static int N, times[], earns[], result;
	static int memo[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		times = new int[N+1];
		earns = new int[N+1];
		memo = new int[N+1];
		Arrays.fill(memo, -1);
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			earns[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dp());
	}

	private static int recur(int k) {
		if (k > N) return 0; // 범위 넘어감
		
		// 현재 일수에서 상담을 할지 안할지
		if (memo[k] != -1) return memo[k];
		
		int ret = 0;
		if (k + times[k] - 1 <= N) ret = recur(k + times[k]) + earns[k];
		return memo[k] = Math.max(ret, recur(k+1));
//		return memo[k] = Math.max(k + times[k] - 1 <= N ? recur(k + times[k]) + earns[k] : 0, recur(k+1));
	}
	
	private static int dp() {
		int table[] = new int[N+2];
		for (int i = 1; i <= N; i++) {
			int next = i + times[i] - 1;
			if (next <= N) table[next] = Math.max(table[i-1] + earns[i], table[next]);
			table[i] = Math.max(table[i], table[i-1]);
		}
//		System.out.println(Arrays.toString(table));
		return table[N];
	}

}
