import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int N, M, adjMatrix[][], nums[], inDegree[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			adjMatrix = new int[N+1][N+1];
			inDegree = new int[N+1];
			nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// 인접 매트릭스 만들기
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					adjMatrix[nums[i]][nums[j]] = 1;
					inDegree[nums[j]]++;
				}
			}
			
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if (adjMatrix[from][to] == 1) {
					inDegree[to]--;
					inDegree[from]++;
				}
				else {
					inDegree[to]++;
					inDegree[from]--;
				}
				adjMatrix[from][to] = 1 - adjMatrix[from][to];
				adjMatrix[to][from] = 1 - adjMatrix[to][from];
			}
			
			Queue<Integer> q = new ArrayDeque<Integer>();
			for (int i = 1; i < inDegree.length; i++) {
				if (inDegree[i] == 0) q.offer(i);
			}
			
			StringBuilder sb = new StringBuilder();
			int edgeCnt = 0;
			
			while (!q.isEmpty()) {
				int curr = q.poll();
				
				sb.append(curr).append(" ");
				edgeCnt++;
				
				for (int j = 1; j <= N; j++) {
					if (adjMatrix[curr][j] == 1) {
						if (--inDegree[j] == 0) q.offer(j);
					}
				}
				
			}
			
			if (edgeCnt == N) System.out.println(sb.toString());
			else System.out.println("IMPOSSIBLE");
		}
	}
}
