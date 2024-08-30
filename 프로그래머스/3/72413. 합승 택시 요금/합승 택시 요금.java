import java.util.*;
import java.io.*;

class Solution {
    static int distance[], S, A, B;
    static boolean visit[], connect[];
    static List<int[]>[] adjList; 
    static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((arr1, arr2) -> Integer.compare(arr1[1], arr2[1]));
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        connect = new boolean[n+1];
        adjList = new ArrayList[n+1];
        S = s; A = a; B = b;
        
        for (int i = 1; i <= n; i++) adjList[i] = new ArrayList<int[]>();
        
        for (int[] edgeInfo : fares) {
            int from = edgeInfo[0];
            int to = edgeInfo[1];
            int weight = edgeInfo[2];
            adjList[from].add(new int[]{to, weight});
            adjList[to].add(new int[]{from, weight});
        }
        // S로부터 dfs돌려서 연결 정점들 찾음
        dfs(S); 
        
        // S, A, B를 세 정점으로 하는 다익스트라를 돌림
        // 각각 돌릴때마다 연결그래프의 모든 정점을 합승지점으로 삼아서 거리를 계산
        int [] startPoint = {S, A, B};
        boolean [][] visitArr = new boolean[3][n+1];
        int [][] disArr = new int[3][n+1];
        
        for (int i = 0; i < startPoint.length; i++) {
            dijkstra(startPoint[i], disArr[i], visitArr[i]);
        }
        
        for (int j = 1; j <= n; j++) {
            if (!connect[j]) continue;
            int minDis = disArr[0][j] + disArr[1][j] + disArr[2][j];
            answer = Math.min(answer, minDis);
            // for (int i = 0; i < 3; i++) { // S A B순서
            //     // S로부터 탑승지까지, + 탑승지부터 A + 탑승지부터 B
            // }
        }
        
        
        
        // connect가 true인 모든 정점으로 부터 다익스트라 돌림
        // for (int i = 1; i < connect.length; i++) {
        //     if (!connect[i]) continue;
        //     int minDis = dijkstra(i, new int[n+1], new boolean[n+1]);
        //     answer = Math.min(answer, minDis);
        // }
        
        System.out.println(answer);
        return answer;
    }
    
    static void dfs(int s) {
        connect[s] = true;
        
        for (int [] vertex : adjList[s]) {
            int next = vertex[0];
            if (connect[next]) continue; // 방문하면 처리안함
            dfs(next);
        }
    }
    
    static int dijkstra(int start, int[] distance, boolean[] visit) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.clear();
        pq.offer(new int[]{start, 0});
        distance[start] = 0; // 시작정점 거리 0으로 초기화
        int selectCnt = 0;
        
        while (!pq.isEmpty()) {
            int [] edgeInfo = pq.poll();
            int curr = edgeInfo[0];
            int weight = edgeInfo[1];
            
            //if (visit[curr]) continue;
            
            //visit[curr] = true;
            // if (curr == A || curr == S || curr == B) selectCnt++;
            // if (selectCnt == 3) break; // A, S, B 거리 다 구했으니 더 구할필요 없음
            
            for (int[] vertex : adjList[curr]) {
                int next = vertex[0];
                int nextWeight = vertex[1];
                
                if (nextWeight + distance[curr] < distance[next]) {
                    distance[next] = nextWeight + distance[curr];
                    // connect[next] = curr;
                    // 어디서 뻗었는지 from을 업데이트
                    pq.offer(new int[]{next, nextWeight});
                }
            }
        }
        // 여기서 해당 정점부터 s까지의 거리 + 정점 ~ A + 정점 ~ B까지 거리 합
        return distance[S] + distance[A] + distance[B];
    }
}

// 경로가 안겹치는 경우 -> 걍 더해주면됌
// 한 경로가 다른 경로에 포함되는 경우 -> 큰 경로를 구해주면됌
// 같이 가다가 마지막에 발산하는 경우 -> 공통 경로를 빼줘야함

// 가지치기가 필요해
// 경로를 중복한다면 지워준다