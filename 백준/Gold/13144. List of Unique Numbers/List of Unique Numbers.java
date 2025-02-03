import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int[] counts = new int[100001];
    	
    	long answer = 0;
    	int left = 0, right = 0;
    	
    	while (left < N) {
    		if (right < N && counts[nums[right]] == 0) {
    			counts[nums[right++]]++;
    		} else {
    			answer += right - left;
    			counts[nums[left++]]--;
    		}
    	}
    	System.out.println(answer);
    }
}
