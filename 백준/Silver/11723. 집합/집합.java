import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	// 1이 3개면 2^N - 1
    	// 1이 20개잖아 : 2^20(1 << 21) - 1
    	int all = (1 << 20) - 1;
    	int empty = 0;
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	String command;
    	int val, bits = 0;
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		command = st.nextToken();
    		if (command.equals("all")) {
    			bits = all;
    		} else if (command.equals("empty")) {
    			bits = empty;
    		} else {
    			val = 1 << Integer.parseInt(st.nextToken()) - 1;
    			switch (command) {
    			case "add":
    				bits |= val;
    				break;
    			case "check":
    				if ((bits & val) > 0)
    					sb.append(1).append("\n");
    				else
    					sb.append(0).append("\n");
    				break;
    			case "remove":
    				bits &= all ^ val;
    				break;
    			case "toggle":
    				bits ^= val;
    				break;
    			}
    		}
    	}
    	System.out.println(sb.toString());
    }
}