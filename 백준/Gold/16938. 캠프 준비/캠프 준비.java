import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R, X, answer;
	static List<Integer> tmp = new ArrayList<>();
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken());
    	L = Integer.parseInt(st.nextToken());
    	R = Integer.parseInt(st.nextToken());
    	X = Integer.parseInt(st.nextToken());
    	
    	int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	powerSet(0, nums, new boolean[nums.length]);
    	System.out.println(answer);
    }

	private static void powerSet(int idx, int[] nums, boolean[] v) {
		if (idx == nums.length) {
			// filter logic
			tmp.clear();
			for (int i = 0; i < v.length; i++) {
				if (!v[i]) continue;
				tmp.add(nums[i]);
			}
			
			if (tmp.isEmpty()) return;
			
			tmp.sort(Comparator.naturalOrder());
			int sum = tmp.stream().mapToInt(Integer::intValue).sum();
			if (tmp.get(tmp.size()-1) - tmp.get(0) >= X && sum >= L && sum <= R) 
				answer++;
			return;
		}
		
		v[idx] = true;
		powerSet(idx+1, nums, v);
		v[idx] = false;
		powerSet(idx+1, nums, v);
	}
}