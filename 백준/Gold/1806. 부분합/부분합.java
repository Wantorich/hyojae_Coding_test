import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int S = Integer.parseInt(st.nextToken());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	
    	long subSum = 0;
    	int left = 0, right = 0, answer = Integer.MAX_VALUE;
    	
    	while (left <= right && right < N) {
    		if (subSum + nums[right] >= S) {
    			answer = Math.min(answer, right - left + 1);
    			subSum -= nums[left++];
    			continue;
    		}
    		subSum += nums[right++];
    	}
    	System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
