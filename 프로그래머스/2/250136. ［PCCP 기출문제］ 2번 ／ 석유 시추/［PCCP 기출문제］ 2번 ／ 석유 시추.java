import java.util.*;

class Solution {
    static boolean visit[][];
    static int MAX_ROW, MAX_COL, energyCnt;
    
    public int solution(int[][] land) {
        MAX_ROW = land.length;
        MAX_COL = land[0].length;
        visit = new boolean[MAX_ROW][MAX_COL];
        int [] energySum = new int[MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (land[i][j] == 0 || visit[i][j]) continue;
                // Set<Integer> colSet = new HashSet<>();
                boolean [] visitCol = new boolean[MAX_COL];
                energyCnt = 0;
                // colSet.add(j);
                bfs(i, j, visitCol, land);
                for (int k = 0; k < MAX_COL; k++) {
                    if (visitCol[k]) energySum[k] += energyCnt;
                }
                // for (int col : colSet)
                //     energySum[col] += energyCnt;
            }
        }
        
        int answer = Arrays.stream(energySum).max().getAsInt();
        return answer;
    }
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static void bfs(int r, int c, boolean[] visitCol, int[][] land) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visit[r][c] = true;
        visitCol[c] = true;
        energyCnt++;
        // set.add(c);
        
        while (!q.isEmpty()) {
            int [] p = q.poll();
            
            for (int i = 0; i < dr.length; i++) {
                int nr = p[0] + dr[i];
                int nc = p[1] + dc[i];

                if (nr < 0 || nr >= MAX_ROW || nc < 0 || nc >= MAX_COL || visit[nr][nc] || land[nr][nc] == 0) 
                    continue;

                // if (c != nc) 
                //     set.add(nc);
                // dfs(nr, nc, visitCol, land);
                visit[nr][nc] = true;
                visitCol[nc] = true;
                energyCnt++;
                q.offer(new int[]{nr, nc});
             }
        }
        
    }
}