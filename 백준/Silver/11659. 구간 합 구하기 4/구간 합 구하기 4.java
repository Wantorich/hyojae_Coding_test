import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int[] cumulative = new int[N+1];
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	for (int i = 1; i <= N; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		cumulative[i] = cumulative[i-1] + num;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int i = 1; i <= M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		sb.append(cumulative[to] - cumulative[from-1]).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
