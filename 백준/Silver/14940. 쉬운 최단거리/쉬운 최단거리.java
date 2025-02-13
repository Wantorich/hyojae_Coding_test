import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int[][] map = new int[N][M];
    	
    	Set<Point> pointSet = new HashSet<>();
    	Point start = null;
    	int val;
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int j = 0; j < M; j++) {
    			val = Integer.parseInt(st.nextToken());
    			map[i][j] = val;
    			if (val == 1) {
    				pointSet.add(new Point(i, j));
    			} else if (val == 2)
    				start = new Point(i, j);
    		}
    	}
    	
    	ArrayDeque<Point> q = new ArrayDeque<>();
    	q.offer(start);
    	int[] dr = {-1, 0, 1, 0};
    	int[] dc = {0, 1, 0, -1};
    	boolean[][] visit = new boolean[N][M];
    	visit[start.x][start.y] = true;
    	
    	int[][] dis = new int[N][M];
    	
    	Point curr, p;
    	int nr, nc;
    	int loop = 1;
    	while (!q.isEmpty()) {
    		int qSize = q.size();
    		for (int k = 0; k < qSize; k++) {
    			curr = q.poll();
    			
    			for (int i = 0; i < dr.length; i++) {
    				nr = curr.x + dr[i];
    				nc = curr.y + dc[i];
    				
    				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || map[nr][nc] == 0)
    					continue;
    				
    				p = new Point(nr, nc);
    				q.offer(p);
    				pointSet.remove(p);
    				dis[nr][nc] = loop;
    				visit[nr][nc] = true;
    			}
    		}
    		loop++;
    	}
    	
    	pointSet.stream().forEach(point -> dis[point.x][point.y] = -1);
    	
    	StringBuilder sb = new StringBuilder();
    	for (int[] row : dis) {
    		for (int d : row)
    			sb.append(d).append(" ");
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
