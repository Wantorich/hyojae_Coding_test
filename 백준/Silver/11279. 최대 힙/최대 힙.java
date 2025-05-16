import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    	StringBuilder sb = new StringBuilder();
    	int val;
    	for (int i = 0; i < N; i++) {
    		val = Integer.parseInt(br.readLine());
    		if (val == 0) {
    			if (maxHeap.isEmpty())
    				sb.append(0);
    			else
    				sb.append(maxHeap.poll());
    			sb.append("\n");
    		} else 
    			maxHeap.offer(val);
    	}
    	System.out.println(sb.toString());
    }
}
