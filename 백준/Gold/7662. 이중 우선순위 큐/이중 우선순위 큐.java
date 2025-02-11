import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	String command;
    	int value, key;
    	StringBuilder sb = new StringBuilder();
    	for (int t = 0; t < tc; t++) {
    		int K = Integer.parseInt(br.readLine());
    		TreeMap<Integer, Integer> tmap = new TreeMap<>();
    		for (int i = 0; i < K; i++) {
    			st = new StringTokenizer(br.readLine(), " ");
    			command = st.nextToken();
    			value = Integer.parseInt(st.nextToken());
    			
    			switch (command) {
	    			case "I" :
	    				tmap.put(value, tmap.getOrDefault(value, 0) + 1);
	    				break;
	    			case "D" :
	    				if (tmap.isEmpty()) break;
	    				else if (value == 1) 
	    					key = tmap.lastKey();
	    				else
	    					key = tmap.firstKey();
	    				
	    				if (tmap.get(key) == 1) 
	    					tmap.remove(key);
	    				else 
	    					tmap.compute(key, (k, v) -> v-1);
    			}
    		}
    		if (tmap.isEmpty()) sb.append("EMPTY");
    		else 
    			sb.append(tmap.lastEntry().getKey()).append(" ").append(tmap.firstEntry().getKey());
    		sb.append("\n");
    	}
    	System.out.println(sb.toString());
    }
}