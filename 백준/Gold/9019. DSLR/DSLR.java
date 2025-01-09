import java.util.*;
import java.io.*;

public class Main {
	static int src, desc;
	static String answer = "";
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int T = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	for (int i = 1; i <= T; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		src = Integer.parseInt(st.nextToken());
    		desc = Integer.parseInt(st.nextToken());
    		
    		Map<Integer, String> map = new HashMap<>();
    		Deque<Integer> deque = new ArrayDeque<>();
    		deque.offer(src);
    		
    		int mul, sub, Ltate, Rtate;
    		int base = 0;
    		while (!deque.isEmpty()) {
    			base = deque.poll();
    			if (base == desc) break;
    			
    			String baseStr = map.getOrDefault(base, "");
				mul = base * 2 % 10000;
				if (map.get(mul) == null) {
					map.put(mul, baseStr + "D");
					deque.offer(mul);
				}
				sub = base == 0 ? 9999 : base - 1;
				if (map.get(sub) == null) {
					map.put(sub, baseStr + "S");
					deque.offer(sub);
				}
				Ltate = (base % 1000 * 10) + base / 1000;
				if (map.get(Ltate) == null) {
					map.put(Ltate, baseStr + "L");
					deque.offer(Ltate);
				}
				Rtate = (base / 10) + base % 10 * 1000;
				if (map.get(Rtate) == null) {
					map.put(Rtate, baseStr + "R");
					deque.offer(Rtate);
				}
    		}
    		
    		sb.append(map.get(base)).append("\n");
    		answer = "";
    	}
    	System.out.println(sb.toString());
    }
}
