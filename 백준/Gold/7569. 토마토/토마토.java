import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int r,c,h;
	Point(int r, int c, int h) {
		this.r = r;
		this.c = c;
		this.h = h;
	}
}

public class Main {
	static int [][][] grid;
    static int R, C, H, tomato_cnt = 0;
    static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] line = br.readLine().split(" ");
        C = Integer.parseInt(line[0]);
        R = Integer.parseInt(line[1]);
        H = Integer.parseInt(line[2]);
        grid = new int[R][C][H];
        q = new LinkedList<Point>();
        
        for (int k = 0; k < H; k++) {
        	for (int i = 0; i < R; i++) {
        		line = br.readLine().split(" ");
        		for (int j = 0; j < C; j++) {
        			switch (grid[i][j][k] = Integer.parseInt(line[j])) {
	        			case 0 : tomato_cnt++; break;
	        			case 1 : q.offer(new Point(i, j, k));
        			}
        		}
        	}
        }
        
        int time = 0, prev_cnt, result = -1;
        
        while (!q.isEmpty()) {
        	prev_cnt = tomato_cnt;
        	int qSize = q.size();
        	for (int i = 0; i < qSize; i++) {
        		Point p = q.poll();
        		if (grid[p.r][p.c][p.h] == time+1) {
        			contagious(p.r, p.c, p.h, time+2);
        		}
        	}
        	if (prev_cnt == tomato_cnt) { // 전염이 일어나지 않았다
        		// 이건 전염이 끝난 경우나, 도달하지 못하는 경우
        		if (tomato_cnt == 0) result = 0; 
        		break;
        	}
        	if (tomato_cnt == 0) {
        		result = time + 1;
        		break;
        	}
        	time++;
        }
        
        System.out.println(result);
    }
    
    static int [] dr = {-1, 0, 1, 0, 0, 0};
    static int [] dc = {0, 1, 0, -1, 0, 0};
    static int [] dh = {0, 0, 0, 0, -1, 1};
    
    static void contagious(int r, int c, int h, int t) {
    	int nr, nc, nh;
    	for (int i = 0; i < dr.length; i++) {
    		nr = r + dr[i];
    		nc = c + dc[i];
    		nh = h + dh[i];
    		
    		if (nr < 0 || nr >= R || nc < 0 || nc >= C || nh < 0 || nh >= H
    				|| grid[nr][nc][nh] != 0) continue;
    		
    		grid[nr][nc][nh] = t;
    		tomato_cnt--;
    		q.offer(new Point(nr, nc, nh));
    	}
    }
}