import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            dfs(i, computers, visit);
            answer++;
        }
        return answer;
    }
    
    static void dfs(int v, int[][] computers, boolean[] visit) {
        visit[v] = true;
        
        for (int i = 0; i < computers.length; i++) {
            if (!visit[i] && computers[v][i] == 1) {
                dfs(i, computers, visit);
            }
        }
    }
}