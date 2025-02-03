import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
	static int[][] memo;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[] memories = new int[N+1];
    	int[] costs = new int[N+1];

    	st = new StringTokenizer(br.readLine(), " ");
    	for (int i = 1; i <= N; i++)
    		memories[i] = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	for (int i = 1; i <= N; i++)
    		costs[i] = Integer.parseInt(st.nextToken());
    	
    	int MAX_COST = 100 * 100;
    	memo = new int[N+1][MAX_COST+1];
    	
    	for (int i = 1; i <= N; i++) {
    		for (int j = 0; j <= MAX_COST; j++) {
    			memo[i][j] = memo[i-1][j];
    			if (j - costs[i] >= 0) {
    				memo[i][j] = Math.max(memo[i][j], memo[i-1][j-costs[i]] + memories[i]);
    			}
    		}
    	}

    	int answer = MAX_COST;
    	
    	for (int i = 1; i <= N; i++) {
    		for (int j = 0; j <= MAX_COST; j++) {
    			if (memo[i][j] >= M) {
    				answer = Math.min(answer, j);
    				break;
    			}
    		}
    	}
    	System.out.println(answer);
    }
}