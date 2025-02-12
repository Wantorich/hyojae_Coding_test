import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> compareInteger(a, b));
    	int value;
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < N; i++) {
    		value = Integer.parseInt(br.readLine());
    		if (value == 0) {
    			if (pq.isEmpty())
    				sb.append(0);
    			else
    				sb.append(pq.poll());
    			sb.append("\n");
    		} else {
    			pq.offer(value);
    		}
    	}
    	System.out.println(sb.toString());
    }

	private static int compareInteger(Integer a, Integer b) {
		int absCompare = Integer.compare(Math.abs(a), Math.abs(b));
		if (absCompare != 0)
			return absCompare;
		return Integer.compare(a, b);
	}
}
