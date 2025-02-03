import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int[] counts = new int[100001];
    	
    	int start = 0, end = 0;
    	int answer = 0;
    	
    	while (end < N) {
    		while (end < N && counts[nums[end]] < K) 
    			counts[nums[end++]]++;
    		
    		answer = Math.max(answer, end - start);
    		
    		while (start < end - 1 && counts[nums[start]] != K)
    			counts[nums[start++]]--;
    		counts[nums[start++]]--;
    	}
    	System.out.println(answer);
    }
}