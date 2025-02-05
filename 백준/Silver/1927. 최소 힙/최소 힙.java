import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	StringBuilder sb = new StringBuilder();
    	int val;
    	for (int i = 0; i < N; i++) {
    		val = Integer.parseInt(br.readLine());
    		if (val == 0) {
    			if (pq.isEmpty())
    				sb.append(0);
    			else
    				sb.append(pq.poll());
    			sb.append("\n");
    		} else {
    			pq.offer(val);
    		}
    	}
    	System.out.println(sb.toString());
    }
}