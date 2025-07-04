import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
	static int dp[], cities[][], INF = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int C = Integer.parseInt(st.nextToken());
    	int N = Integer.parseInt(st.nextToken());
    	dp = new int[C+1];
    	cities = new int[N][2];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		cities[i][0] = Integer.parseInt(st.nextToken());
    		cities[i][1] = Integer.parseInt(st.nextToken());
    	}
    	Arrays.fill(dp, INF);
    	dp[0] = 0;
    	solve(C);
//    	System.out.println(Arrays.toString(dp));
    	System.out.println(dp[C]);
    }

	private static int solve(int num) {
		if (dp[num] != INF) {
			return dp[num];
		}
		
		int min = INF;
		int prev;
		for (int i = 0; i < cities.length; i++) {
//			if (num - cities[i][1] < 0)
//				continue;
			prev = dp[Math.max(0,num - cities[i][1])];
			if (prev == INF) {
				prev = solve(num - cities[i][1]);
			}
			min = Math.min(min, prev + cities[i][0]);
		}
		
		return dp[num] = min;
	}

}

/*

C명을 늘리기 위한 최소 금액 dp[C] 
도시는 N개 있음
dp[C] = min(dp[C-a1] + a0 + ...) 

*/