import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] adjList;
	static int[] inDegree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adjList = new ArrayList[N+1];
		inDegree = new int[N+1];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < M; i++) {
			// 인접 리스트 정보 저장
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from].add(to);
			// 진입 차수 정보 저장
			inDegree[to]++;
		}
		
		// 진입차수가 0인 것들을 Q에 넣고 시작
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 1; i <= N; i++) 
			if (inDegree[i] == 0) q.offer(i);
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append(" "); // 정보 저장
			
			for (Integer next : adjList[curr]) {
				// curr로 부터 이어져있는 간선의 수를 하나씩 지움
				if (--inDegree[next] == 0) {
					// 진입차수가 0이 됐다면 q에 집어넣음
					q.offer(next);
				}
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}

