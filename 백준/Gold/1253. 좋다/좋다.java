import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = Arrays.stream(br.readLine().split(" "))
    			.mapToInt(Integer::parseInt)
    			.toArray();
    	Arrays.sort(nums);
    	
    	int answer = 0;
    	int target;
    	boolean prev, next;
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = 0; j < nums.length; j++) {
    			if (i == j) continue;
    			target = nums[i] - nums[j];
    			
    			if (i < j) {
    				// i < j
    				prev = false;
    				next = Arrays.binarySearch(nums, j+1, N, target) >= 0;
    			} else {
    				// i > j
    				prev = Arrays.binarySearch(nums, j+1, i, target) >= 0;
    				next = Arrays.binarySearch(nums, i+1, N, target) >= 0;
    			}
    			
    			if (prev || next) {
    				answer++;
    				break;
    			}
    		}
    	}
    	System.out.println(answer);
    }
}
