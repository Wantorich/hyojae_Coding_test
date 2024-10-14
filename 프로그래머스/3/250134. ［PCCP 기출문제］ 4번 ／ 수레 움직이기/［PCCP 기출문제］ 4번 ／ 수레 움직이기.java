import java.util.*;
/*
    0 : 빈칸
    1 : 빨간 수레 시작칸
    2 : 파란 수레 시작칸
    3 : 빨간 수레 도착칸
    4 : 파란 수레 도착칸
    5 : 벽
*/
class Point {
    int r, c;
    
    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    static boolean visit[][][];
    static Point srp, erp, sbp, ebp;
    static int MAX_ROW, MAX_COL, minDis = Integer.MAX_VALUE;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};
    
    static boolean inRange(int r, int c) {
        return 0 <= r && r < MAX_ROW && 0 <= c && c < MAX_COL;
    }
    
    public int solution(int[][] maze) {
        MAX_ROW = maze.length;
        MAX_COL = maze[0].length;
        int answer = 0;
        visit = new boolean[MAX_ROW][MAX_COL][2]; // 0은 레드팀, 1은 블루팀
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                switch (maze[i][j]) {
                    case 1 : 
                        srp = new Point(i, j);
                        visit[i][j][0] = true;
                        break;
                    case 2 :
                        sbp = new Point(i, j);
                        visit[i][j][1] = true;
                        break;
                    case 3 :
                        erp = new Point(i, j);
                        break;
                    case 4 :
                        ebp = new Point(i, j);
                        break;
                    case 5 :
                        visit[i][j][0] = visit[i][j][1] = true;
                        break;
                }
            }
        }
        
        backtracking(srp.r, srp.c, sbp.r, sbp.c, 0);
        if (minDis == Integer.MAX_VALUE) minDis = 0;
        return minDis;
    }
    
    static void backtracking(int rr, int rc, int br, int bc, int dis) {
        if (rr == erp.r && rc == erp.c && br == ebp.r && bc == ebp.c) {
            minDis = Math.min(minDis, dis);
            return;
        }
        
        if (dis > minDis) return;
        
        // 레드 먼저 이동
        for (int i = 0; i < dr.length; i++) {
            int nrr = rr + dr[i];
            int nrc = rc + dc[i];
            
            if (rr == erp.r && rc == erp.c) {
                nrr = rr;
                nrc = rc;
            } else {
                if (!inRange(nrr, nrc) || visit[nrr][nrc][0]) continue;
            }
            
            for (int j = 0; j < dr.length; j++) {
                int nbr = br + dr[j];
                int nbc = bc + dc[j];

                if (br == ebp.r && bc == ebp.c) {
                    nbr = br;
                    nbc = bc;
                } else {
                    if (!inRange(nbr, nbc) ||  visit[nbr][nbc][1]) continue;
                }
                if (nrr == nbr && nrc == nbc) continue;
                if (nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                
                visit[nrr][nrc][0] = true;
                visit[nbr][nbc][1] = true;
                backtracking(nrr, nrc, nbr, nbc, dis+1);
                visit[nrr][nrc][0] = false;
                visit[nbr][nbc][1] = false;
            }
        }
    }
}