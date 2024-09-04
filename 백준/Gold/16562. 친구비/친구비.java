import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static List<int[]>[] adjList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st;
        
        String input[] = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        
        adjList = new ArrayList[N+1];
        for (int i = 0; i < adjList.length; i++) adjList[i] = new ArrayList<int[]>();
        
        // 원점으로부터의 가중치
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	int weight = Integer.parseInt(st.nextToken());
        	adjList[0].add(new int[] {i, weight});
        }
        
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	adjList[from].add(new int[] {to, 0});
        	adjList[to].add(new int[] {from, 0});
        }
        
        // 원점으로 부터 prim시작
        prim(0);
        
        sc.close();
    }

	private static void prim(int v) {
		boolean [] visit = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((arr1, arr2) -> Integer.compare(arr1[1], arr2[1]));
		pq.offer(new int[] {v, 0});
		int cost = 0; int edgeCnt = 0;
		
		while (edgeCnt < N+1) {
			int [] vertexInfo = pq.poll();
			int curr = vertexInfo[0];
			int weight = vertexInfo[1];
			
			if (visit[curr]) continue;
			
			visit[curr] = true;
			edgeCnt++;
			
			if (cost + weight > K) break;
			cost += weight;
			
			for (int j = 0; j < adjList[curr].size(); j++) {
				int next = adjList[curr].get(j)[0];
				int nextWeight = adjList[curr].get(j)[1];
				
				if (!visit[next]) {
					pq.offer(new int[] {next, nextWeight});
				}
			}
		}
		
		if (edgeCnt < N+1) System.out.println("Oh no");
		else System.out.println(cost);
	}
}