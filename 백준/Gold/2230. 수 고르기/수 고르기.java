import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[] nums = new int[N];
    	for (int i = 0; i < N; i++)
    		nums[i] = Integer.parseInt(br.readLine());
    	
    	Arrays.sort(nums);
    	int start = 0, end = 0;
    	int value;
    	int answer = Integer.MAX_VALUE;
    	
    	while (end < N) {
    		value = nums[end] - nums[start];
    		
    		if (value < M) {
    			end++;
    		} else if (value > M) {
    			answer = Math.min(answer, value);
    			start++;
    		} else {
    			answer = M;
    			break;
    		}
    	}
    	System.out.println(answer);
    }
}