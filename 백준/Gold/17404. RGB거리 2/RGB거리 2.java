import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[][] dp = new int[N][3];
    	int[][] costs = new int[N][3];
    	
    	for (int i = 0; i < N; i++) {
    		costs[i] = Arrays.stream(br.readLine().split(" "))
    	    		.mapToInt(Integer::parseInt).toArray();
    	}
    	
    	int INF = 1000 * 1000;
    	int answer = INF;
    	
    	for (int i = 0; i < 3; i++) {
			dp[0][i] = costs[0][i];
			dp[0][(i+1)%3] = dp[0][(i+2)%3] = INF;
			
			for (int k = 1; k < N; k++) {
	    		for (int l = 0; l < 3; l++) {
	    			dp[k][l] = costs[k][l] + Math.min(dp[k-1][(l+1)%3], dp[k-1][(l+2)%3]);
	    		}
	    	}
			
			for (int j = 0; j < 3; j++) {
				if (i == j) continue;
				answer = Math.min(answer, dp[N-1][j]);
			}
    	}
    	System.out.println(answer);
    }
}