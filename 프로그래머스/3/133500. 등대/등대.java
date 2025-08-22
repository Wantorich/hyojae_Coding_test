import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        List<Integer>[] adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 연결 숫자 개수
        int[] connect = new int[n+1];
        // 0 : 안켜짐, 1 : 직접 킴, -1 : 연결된 애가 켜짐
        int[] status = new int[n+1];
        
        for (int[] edge : lighthouse) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
            connect[edge[0]]++;
            connect[edge[1]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 1; i <= n; i++) {
            if (connect[i] == 1) {
                q.offer(i);    
            }
        }
        
        // 나머지는 정렬해서 가장 많이 연결된거부터?
        while (!q.isEmpty()) {
            // leaf 인지 확인
            int curr = q.poll();
            if (connect[curr] != 1) continue;
            // 연결된거
            int adj = adjList[curr].get(0);
            
            // 연결된거에서 주위를 다 끊음
            for (int i = 0; i < adjList[adj].size(); i++) {
                int next = adjList[adj].get(i);
                adjList[next].remove((Integer) adj);
                if (--connect[next] == 1) {
                    q.offer(next);
                }
            }
            
            adjList[adj].clear();
            connect[adj] = 0;
            answer++;
        }
        return answer;
    }
}