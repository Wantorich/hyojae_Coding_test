import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer>[] adjList = new ArrayList[n+1];
        for (int i = 0; i <= n; i++)
            adjList[i] = new ArrayList<>();
        int from, to;
        for (int[] e : edge) {
            from = e[0];
            to = e[1];
            adjList[from].add(to);
            adjList[to].add(from);
        }
        
        // 1번부터 BFS 시작
        boolean[] visit = new boolean[n+1];
        visit[1] = true;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        
        int curr;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            answer = qSize;
            for (int i = 0; i < qSize; i++) {
                curr = queue.poll();
                for (Integer next : adjList[curr]) {
                    if (!visit[next]) {
                        queue.offer(next);
                        visit[next] = true;
                    }
                }
            }
        }
        return answer;
    }
}

/*
BFS를 도는데 이번턴에 돈 수를 계속 저장한다
그러다 다 돌고 저장된거 리턴한다

offer를 할때 넣어야하겠지?

정점이 더 많다. adjList
*/