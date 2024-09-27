import java.util.*;

class Solution {
    static int er, ec, sr, sc, fullDis, destDis = Integer.MAX_VALUE;
    static int grid[][];
    static boolean visit[][], find =false;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        characterY *= 2; characterX *= 2; itemY *= 2; itemX *= 2;
        sr = characterY; sc = characterX; 
        er = itemY; ec = itemX;
        
        grid = new int[120][120];
        visit = new boolean[120][120];
        
        for (int [] rect : rectangle) {
            // 우 상 좌 하
            rect[0] *= 2; rect[1] *= 2; rect[3] *= 2; rect[2] *= 2;
            int r = rect[1];
            int c = rect[0];
            for (int i = 0; i <= rect[2] - rect[0]; i++, c++) grid[r][c] = 1;
            c--;
            for (int i = 0; i <= rect[3] - rect[1]; i++, r++) grid[r][c] = 1;
            r--;
            for (int i = 0; i <= rect[2] - rect[0]; i++, c--) grid[r][c] = 1;
            c++;
            for (int i = 0; i <= rect[3] - rect[1]; i++, r--) grid[r][c] = 1;
            r++;
        }
        
        // for (int [] row : grid) {
        //     System.out.println(Arrays.toString(row));
        // }
        // visit[characterY][characterX] = true;
        dfs(sr, sc, 0, rectangle);
        // return Math.min(destDis, fullDis - destDis);
        return destDis / 2;
    }
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static boolean inRange(int r, int c) {
        return 1 <= r && r <= 120 && 1 <= c && c <= 120;
    }
    
    static void dfs(int r, int c, int dis, int[][] rectangle) {
        // List<int[]> tempList = new ArrayList();
        visit[r][c] = true;
        // if (find) return;
        
        // item 발견
        if (r == er && c == ec) {
            destDis = Math.min(destDis, dis);
            visit[r][c] = false;
            return;
        }
        
        for (int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // // 다음 이동지가 목적지(원점)
            // if (dis > destDis && nr == sr && nc == sc) {
            //     fullDis = Math.max(fullDis, dis+1);
            //     // find = true;
            //     return;
            // }
            
            if (!inRange(nr, nc) || visit[nr][nc] || grid[nr][nc] == 0) continue;
            
            // 직사각형 영역 안이면 이동 안함
            boolean flag = true;
            for (int [] rect : rectangle) {
                if (rect[1] < nr && rect[0] < nc && nr < rect[3] && nc < rect[2]) {
                    flag = false;
                    break;
                }
            }
            
            if (!flag) continue;
            
            // tempList.add(new int[]{nr, nc, i});
            // visit[nr][nc] = true;
            dfs(nr, nc, dis+1, rectangle);
            // visit[nr][nc] = false;
        }
    }
}