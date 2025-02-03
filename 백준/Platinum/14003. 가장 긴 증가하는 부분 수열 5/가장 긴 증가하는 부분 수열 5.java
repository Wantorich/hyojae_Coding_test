import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	List<Integer> lis = new ArrayList<>();
    	int[] prev = new int[N];
    	int[] indices = new int[N];
    	
    	int value, length = 0;
    	for (int i = 0; i < N; i++) {
    		value = nums[i];
    		int index = Collections.binarySearch(lis, value);
    		if (index < 0)
    			index = -index - 1;
    		
    		if (index == lis.size())
    			lis.add(value);
    		else 
    			lis.set(index, value);
    		
    		indices[index] = i;
    		prev[i] = index > 0 ? indices[index-1] : -1;
    		
    		if (index == length) length++;
    	}
    	
    	System.out.println(lis.size());
    	
    	List<Integer> answer = new ArrayList<>();
    	int k = indices[length - 1];
    	while (k >= 0) {
    		answer.add(nums[k]);
    		k = prev[k];
    	}
    	
    	Collections.reverse(answer);
    	String result = answer.stream().map(String::valueOf).collect(Collectors.joining(" "));
    	System.out.println(result);
    }
}
