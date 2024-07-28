import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int [] isvisited;
    static int N = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int str = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        System.out.println(bfs(str, end));
    }
    
    static int bfs(int s, int e) {
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(s);
    	isvisited = new int[N+1];
    	
    	while (!q.isEmpty()) {
    		int val = q.poll();
    		if (val == e) {
    			return isvisited[val];
    		}
    		
    		if (val-1 >= 0 && isvisited[val-1] == 0) {
    			q.offer(val-1);
    			isvisited[val-1] = isvisited[val] + 1;
    		}
    		
    		if (val+1 <= N && isvisited[val+1] == 0) {
    			q.offer(val+1);
    			isvisited[val+1] = isvisited[val] + 1;
    		}
    		
    		if (val*2 <= N && isvisited[val*2] == 0) {
    			q.offer(val*2);
    			isvisited[val*2] = isvisited[val] + 1;
    		}
    	}
    	
    	return -1;
    }
}

