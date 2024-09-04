import java.io.*;
import java.util.*;

public class Main {
	static int N, M, parents[];
	static int[][] edgeList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st;
        
        String input[] = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        
        edgeList = new int[M][2];
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	edgeList[i][0] = from;
        	edgeList[i][1] = to;
        }
        
        int result = 0;
        int idx = 0;
        makeSet();
        for (int [] edge : edgeList) {
        	idx++;
        	if (!union(edge[0], edge[1])) {
        		result = idx;
        		break;
        	}
        }
        
        System.out.println(result);
        sc.close();
    }
    
    static void makeSet() {
    	parents = new int[N];
    	for (int i = 0; i < N; i++) parents[i] = i;
    }
    
    static int find(int v) {
    	if (parents[v] == v) return v;
    	return parents[v] = find(parents[v]);
    }
    
    static boolean union(int i, int j) {
    	int pi = find(i);
    	int pj = find(j);
    	if (pi == pj) return false;
    	parents[pi] = pj;
    	return true;
    }
}