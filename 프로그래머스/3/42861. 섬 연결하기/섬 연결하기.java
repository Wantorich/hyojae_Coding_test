import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        List<int[]>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) adjList[i] = new ArrayList<int[]>();
        
        for (int [] edgeInfo : costs) {
            int from = edgeInfo[0];
            int to = edgeInfo[1];
            int weight = edgeInfo[2];
            adjList[from].add(new int[]{to, weight});
            adjList[to].add(new int[]{from, weight});
        }
        
        boolean [] visit = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((arr1, arr2) -> Integer.compare(arr1[1], arr2[1]));
        pq.offer(new int[]{0, 0});
        int disSum = 0; int islandCnt = 0;
        
        while (!pq.isEmpty()) {
            int [] edge = pq.poll();
            int curr = edge[0];
            int weight = edge[1];
            
            if (visit[curr]) continue;
            
            visit[curr] = true;
            islandCnt++;
            disSum += weight;
            
            if (islandCnt == n) break;
            
            for (int [] vertex : adjList[curr]) {
                int next = vertex[0];
                int nextWeight = vertex[1];
                
                if (!visit[next]) {
                    pq.offer(new int[]{next, nextWeight});
                }
            }
        }
        answer = disSum;
        return answer;
    }
}