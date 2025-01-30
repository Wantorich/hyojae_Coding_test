import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	
    	int start = 0, end = 0;
    	int sum = nums[0];
    	int answer = 0;
    	while (end < N) {
    		if (sum <= M) {
    			if (sum == M) answer++;
    			if (end + 1 == N) break;
    			sum += nums[++end];
    		} else if (sum > M) {
    			sum -= nums[start++];
    		}
    	}
    	
    	System.out.println(answer);
    }
}