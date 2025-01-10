import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int height;
    	ArrayDeque<int[]> st = new ArrayDeque<>();
    	long answer = 0;
    	Map<Integer, Integer> same = new HashMap<>();
    	
    	for (int i = 1; i <= N; i++) {
    		height = Integer.parseInt(br.readLine());
    		
    		if (!st.isEmpty() && st.peek()[0] > height) {
				answer++;
				st.push(new int[] {height, i});
				continue;
    		}
    		
    		while (!st.isEmpty() && st.peek()[0] <= height) {
    			answer++;
				// peek < height
				if (same.getOrDefault(st.peek()[0], 0) > 0) {
					answer += same.get(st.peek()[0]);
					if (st.peek()[0] < height)
						same.put(st.peek()[0], 0);
				}
				
				if (st.peek()[0] == height) {
					same.put(height, same.getOrDefault(height, 0) + 1);
				} 
    			st.pop();
    		}	
    		
    		if (!st.isEmpty()) answer++;
			st.push(new int[] {height, i});
    	}
    	System.out.println(answer);
    }
}
