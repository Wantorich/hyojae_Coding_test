import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int positiveIdx = -Arrays.binarySearch(nums, 0) - 1;
    	
    	int[] answer = new int[3];
    	answer[0] = Integer.MAX_VALUE;
    	
    	if (positiveIdx < N-1) {
    		answer[0] = nums[positiveIdx] + nums[positiveIdx+1];
    		answer[1] = positiveIdx;
    		answer[2] = answer[1] + 1;
    	}
    	
    	if (positiveIdx >= 2 && -(nums[positiveIdx-1] + nums[positiveIdx-2]) < answer[0]) {
    		answer[0] = -(nums[positiveIdx-1] + nums[positiveIdx-2]);
    		answer[1] = positiveIdx-2;
    		answer[2] = answer[1] + 1;
    	}
    	
    	int targetIdx, j;
    	for (int i = 0; i < positiveIdx; i++) {
    		targetIdx = Arrays.binarySearch(nums, -nums[i]);
    		if (targetIdx >= 0) {
    			System.out.println(nums[i] + " " + nums[targetIdx]);
    			return;
    		}
    		
    		j = -targetIdx - 1;
    		
    		if (j < N && Math.abs(nums[i] + nums[j]) < answer[0]) {
    			answer[0] = Math.abs(nums[i] + nums[j]);
    			answer[1] = i;
    			answer[2] = j;
    		}
    		
    		if (j-1 > i && Math.abs(nums[i] + nums[j-1]) < answer[0]) {
    			answer[0] = Math.abs(nums[i] + nums[j-1]);
    			answer[1] = i;
    			answer[2] = j-1;
    		}
    	}
    	
    	System.out.printf("%d %d", nums[answer[1]], nums[answer[2]]);
    }
}