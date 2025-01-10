import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int height;
    	ArrayDeque<int[]> st = new ArrayDeque<>();
    	long answer = 0;
    	
    	for (int i = 1; i <= N; i++) {
    		height = Integer.parseInt(br.readLine());
    		int sameCount = 1;
    		
    		while (!st.isEmpty() && st.peek()[0] <= height) {
    			answer += st.peek()[1];
    			
    			if (st.peek()[0] == height) 
    				sameCount += st.pop()[1];
    			else
    				st.pop();
    		}
    		
    		if (!st.isEmpty()) answer++;
    		st.push(new int[] {height, sameCount});
    	}
    	System.out.println(answer);
    }
}
