import java.util.*;
import java.io.*;

class Point {
    int r, c, d, k;
    Point(int r, int c, int d, int k) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.k = k;
    }
    @Override
    public String toString() {
        return "Point [r=" + r + ", c=" + c + ", d=" + d +  ", k=" + k + "]";
    }
}

public class Main {
    static int R, C, K, cnt = Integer.MAX_VALUE;
    static int[][] grid;
    static boolean[][][] v;

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    K = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    C = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    grid = new int[R][C];
    v = new boolean[R][C][K+1];

    for (int i = 0; i < R; i++) {
        String[] line = br.readLine().split(" ");
        for (int j = 0; j < C; j++) {
            grid[i][j] = Integer.parseInt(line[j]);
        }
    }

    bfs(0, 0);
    cnt = cnt == Integer.MAX_VALUE ? -1 : cnt;
    System.out.println(cnt);
}

private static void bfs(int r, int c) {
    Queue<Point> q = new LinkedList<Point>();
    q.offer(new Point(r, c, 0, 0));
    v[r][c][0] = true;

    while (!q.isEmpty()) {
        Point p = q.poll();
//        System.out.println(p);

        if (p.r == R-1 && p.c == C-1) {
            cnt = Math.min(cnt, p.d);
            return;
//            continue;
        }
        
        int nr, nc;
        O:for (int i = 0; i < dr.length; i++) {
            nr = p.r + dr[i];
            nc = p.c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || grid[nr][nc] == 1) continue;

//            if (v[nr][nc][p.k]) continue;
            for (int j = 0; j <= p.k; j++) {
            	if (v[nr][nc][j]) continue O;
            }
            
            if (i < 4) {
            	v[nr][nc][p.k] = true;
            	q.offer(new Point(nr, nc, p.d+1, p.k));
            }
            else if (p.k < K) {
            	if (v[nr][nc][p.k+1]) continue O;
            	v[nr][nc][p.k+1] = true;
            	q.offer(new Point(nr, nc, p.d+1, p.k+1));
            }
            
        }
    }
}
// 4,5는 0이랑 / 6,7은 1이랑 

static int[] dr = {-1, 0, 0, 1, -1, -2, -2, -1, 1, 2, 2, 1};
static int[] dc = {0, -1, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
}