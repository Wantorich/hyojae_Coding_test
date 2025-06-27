import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.Point;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	// 인접 리스트 정의
    	List<Integer>[] adjList = new ArrayList[N+1];
    	for (int i = 0; i < adjList.length; i++)
    		adjList[i] = new ArrayList<>();
    	
    	// 진입 차수 정의
    	int[] inDegree = new int[N+1];
    	
    	// 인접 리스트 원소 삽입
    	int from, to;
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		from = Integer.parseInt(st.nextToken());
    		to = Integer.parseInt(st.nextToken());
    		inDegree[to]++; // 진입 차수 1 증가
    		adjList[from].add(to); // 단방향 리스트 연결
    	}
    	
    	// PQ 정의 후 진입차수가 0인 것부터 추가
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	for (int i = 1; i <= N; i++) {
    		if (inDegree[i] == 0) {
    			pq.offer(i);
    		}
    	}
    	
    	// PQ를 빼면서 순회 돌기
    	List<String> result = new ArrayList<>();
    	while (!pq.isEmpty()) {
    		Integer curr = pq.poll();
    		result.add(curr.toString());
    		
    		// 연관된 노드 인접 차수 1씩 감소
    		for (Integer next : adjList[curr]) {
    			if (--inDegree[next] == 0) {
    				// 진입 차수 0이라면 추가
    				pq.offer(next);
    			}
    		}
    	}
    	System.out.println(result.stream().collect(Collectors.joining(" ")));
    }
}

/*

위상정렬 문제 + PQ?우선순위?
선호도가 있는 문제는 링크로 연결해준다?
즉 두번째는 inDegree가 +1되고, inDegree가 0인거부터 PQ에 넣는다
PQ를 뽑으면서 해당이 연결된 링크를 다 inDegree -1 해준다. 단방향 리스트

*/