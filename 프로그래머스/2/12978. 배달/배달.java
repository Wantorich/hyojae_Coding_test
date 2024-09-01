import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<int[]>[] adjList = new ArrayList[N+1]; 
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<int[]>(); // 인접리스트 생성
        }
        
        // 인접리스트에 간선 정보 저장
        for (int [] row : road) {
            int from = row[0];
            int to = row[1];
            int weight = row[2];
            adjList[from].add(new int[]{to, weight});
            adjList[to].add(new int[]{from, weight});
        }
        
        int answer = 0;
        // road의 원소 개수는 간선 개수
        int [] distance = new int[N+1]; // 거리배열
        Arrays.fill(distance, Integer.MAX_VALUE); // 최대값으로 초기화
        
        // 시작 정점 초기화
        distance[1] = 0;
        
        boolean [] visit = new boolean[N+1]; // 정점 방문배열
        
        // 거리 업데이트
        for (int i = 1; i <= N; i++) {
            // 모든 정점에 대해 최소 거리부터 시작
            int minIdx = -1, minDis = Integer.MAX_VALUE;
            for (int j = 1; j <= N; j++) {
                if (!visit[j] && distance[j] < minDis) { // 미방문한 정점중 최소거리정점 찾음
                    minDis = distance[j];
                    minIdx = j;
                }
            }
            
            visit[minIdx] = true; // 시작정점 방문처리
            
            // 시작 정점 찾음
            // 시작정점으로 부터 연결된 정점에 대해 거리 업데이트
            for (int [] vertex : adjList[minIdx]) {
                int next = vertex[0];
                int weight = vertex[1];
                
                // 현재 거리부터 vertext까지의 거리가 vertex의 distance보다 작으면 업데이트
                if (distance[minIdx] + weight < distance[next]) {
                    distance[next] = distance[minIdx] + weight; // 거리 업데이트
                }
            }
        }
        
        // 거리 배열 돌면서 k이하의 값마다 카운트 증가
        for (int dis : distance) {
            if (dis <= K) answer++;
        }

        return answer;
    }
}

// 다익스트라 lets go
// 정점 기준은 1번 마을
// 두 마을을 잇는 간선이 2개면 최솟값만 처리함