import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	ArrayList<Integer> list = new ArrayList<>();
    	Map<Integer, Boolean> existMap = new HashMap<>();
    	
    	for (int num : nums) {
    		if (existMap.get(num) != null) 
    			continue;
    		existMap.put(num, true);
    		list.add(num);
    	}
    	
    	list.sort(Integer::compareTo);
    	
    	StringBuilder sb = new StringBuilder();
    	for (int num : nums) {
    		int index = Collections.binarySearch(list, num);
    		sb.append(index).append(" ");
    	}
    	
    	System.out.println(sb.toString());
    }
}
