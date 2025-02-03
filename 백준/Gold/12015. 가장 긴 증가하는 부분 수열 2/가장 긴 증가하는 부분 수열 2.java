import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	List<Integer> lis = new ArrayList<>();
    	
    	int idx;
    	for (int i = 0; i < N; i++) {
    		idx = Collections.binarySearch(lis, nums[i]);
    		
    		if (idx < 0)
    			idx = -idx - 1;
    		
    		if (idx == lis.size())
    			lis.add(nums[i]);
    		else
    			lis.set(idx, nums[i]);
    	}
    	System.out.println(lis.size());
    }
}