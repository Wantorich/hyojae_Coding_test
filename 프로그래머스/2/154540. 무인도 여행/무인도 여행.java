import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int sum;
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        int N = maps.length;
        int M = maps[0].length();
        boolean[][] v = new boolean[N][M];
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'X' || v[i][j]) {
                    continue;
                }
                sum = 0;
                dfs(i, j, maps, v);
                list.add(sum);
            }
        }
        
        Collections.sort(list);
        if (list.isEmpty()) 
            list.add(-1);
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    void dfs (int r, int c, String[] maps, boolean[][] v) {
        v[r][c] = true;
        sum += maps[r].charAt(c) - '0';
        
        for (int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length()
               || v[nr][nc] || maps[nr].charAt(nc) == 'X') {
                continue;
            }
            
            dfs(nr, nc, maps, v);
        }
    }
}