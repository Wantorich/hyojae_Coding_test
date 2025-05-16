import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[][] adjMatrix = new int[N+1][N+1];
    	
    	int from, to;
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		from = Integer.parseInt(st.nextToken());
    		to = Integer.parseInt(st.nextToken());
    		adjMatrix[from][to] = 1;
    		adjMatrix[to][from] = 1;
    	}
    	
    	int answer = 0;
    	boolean[] visit = new boolean[N+1];
    	for (int i = 1; i <= N; i++) {
    		if (visit[i]) continue;
    		dfs(i, adjMatrix, visit);
    		answer++;
    	}
    	
    	System.out.println(answer);
    }

	private static void dfs(int v, int[][] adjMatrix, boolean[] visit) {
		visit[v] = true;
		
		for (int i = 1; i <= visit.length-1; i++) {
			if (adjMatrix[v][i] == 1 && !visit[i]) {
				dfs(i, adjMatrix, visit);
			}
		}
	}
}