import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	
    	int start = 0, end = 0;
    	int answer = 0;
    	Map<Integer, Integer> map = new HashMap<>();
    	
    	while (end < N) {
    		int curr = map.getOrDefault(nums[end], 0);
    		if (curr == K) {
    			answer = Math.max(answer, end - start);
    			map.compute(nums[start++], (k, v) -> v - 1);
    			continue;
    		}
    		map.put(nums[end++], curr + 1);
    	}
    	answer = Math.max(answer, end - start);
    	System.out.println(answer);
    }
}