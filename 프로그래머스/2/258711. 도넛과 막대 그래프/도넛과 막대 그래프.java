import java.util.*;

class Solution {
    static List<Integer>[] adjList;
    static boolean[] visit;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int MAX_NUM = 1000010;
        adjList = new ArrayList[MAX_NUM];
        for (int i = 1; i < adjList.length; i++) 
            adjList[i] = new ArrayList<>();
        
        int[] inDegree = new int[MAX_NUM];
        int max = 0;
        
        for (int [] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList[from].add(to);
            inDegree[to]++;
            max = Math.max(max, Math.max(from, to));
        }
        
        // 연결지점 찾아내기
        int start = -1;
        for (int i = 1; i <= max; i++) {
            if (inDegree[i] == 0 && adjList[i].size() >= 2) {
                start = i;
                break;
            }
        }
        
        // 연결지점의 인접리스트에 대해 bfs돌리기
        // 각 그래프 판단은 정점수와 간선수로 비교하면됌
        visit = new boolean[max+1];
        visit[start] = true;
        for (int next : adjList[start]) {
            int idx = bfs(next);
            answer[idx]++;
        }

        answer[0] = start;
        return answer;
    }
    
    static int bfs(int v) {
        int nodeCnt = 0, edgeCnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(v);
        visit[v] = true;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            nodeCnt++;
            edgeCnt += adjList[curr].size();
            
            for (int next : adjList[curr]) {
                if (visit[next]) continue;
                
                q.offer(next);
                visit[next] = true;
            }
        }
        
        if (nodeCnt == edgeCnt) return 1;
        else if (nodeCnt == edgeCnt + 1) return 2;
        else if (nodeCnt + 1 == edgeCnt) return 3;
        else return -1;
    }
}