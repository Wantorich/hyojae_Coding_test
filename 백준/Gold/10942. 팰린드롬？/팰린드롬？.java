import java.util.*;
import java.io.*;

public class Main {
	static int[][] memo;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	memo = new int[N][N];
    	
    	// initialize
    	for (int [] row : memo)
    		Arrays.fill(row, -1);
    	for (int i = 0; i < N; i++)
    		memo[i][i] = 1;
    	
    	int[] nums = Arrays.stream(br.readLine().split(" "))
    			.mapToInt(Integer::parseInt).toArray();
    	
    	// 테이블 채우기
    	for (int i = 0; i < N; i++) {
    		for (int j = N-1; j > i; j--) {
    			if (memo[i][j] != -1) continue;
    			memo[i][j] = palindrom(i, j, nums) ? 1 : 0;
    			
    			if (memo[i][j] == 1) {
    				int index = 1;
    				while (i + index <= j - index) {
    					memo[i+index][j-index] = 1;
    					index++;
    				}
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	int M = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int a = Integer.parseInt(st.nextToken()) - 1;
    		int b = Integer.parseInt(st.nextToken()) - 1;
    		sb.append(memo[a][b]).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }

	private static boolean palindrom(int a, int b, int[] nums) {
		if (a == b) 
			return true;
		
		if (memo[a][b] != -1)
			return memo[a][b] == 1 ? true : false;
		
		boolean current = nums[a] == nums[b];
		
		if (!current) {
			memo[a][b] = 0;
			return false;
		}
		
		if (a + 1 == b) {
			memo[a][b] = 1;
			return true;
		}
		
		memo[a][b] = palindrom(a+1, b-1, nums) ? 1 : 0;
		return memo[a][b] == 1 ? true : false;
	}
}
