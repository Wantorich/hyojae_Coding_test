import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	char[][] map = new char[N][M];
    	
    	Set<Point> pointSet = new HashSet<>();
    	Point start = null;
    	for (int i = 0; i < N; i++) {
    		map[i] = br.readLine().toCharArray();
    		for (int j = 0; j < M; j++) {
    			if (map[i][j] == 'I') {
    				start = new Point(i, j);
    				break;
    			}
    				
    		}
    	}
    	
    	ArrayDeque<Point> q = new ArrayDeque<>();
    	q.offer(start);
    	int[] dr = {-1, 0, 1, 0};
    	int[] dc = {0, 1, 0, -1};
    	boolean[][] visit = new boolean[N][M];
    	visit[start.x][start.y] = true;
    	
    	int answer = 0;
    	
    	Point curr, p;
    	int nr, nc;
    	while (!q.isEmpty()) {
			curr = q.poll();
			if (map[curr.x][curr.y] == 'P')
				answer++;
			
			for (int i = 0; i < dr.length; i++) {
				nr = curr.x + dr[i];
				nc = curr.y + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || map[nr][nc] == 'X')
					continue;
				
				p = new Point(nr, nc);
				q.offer(p);
				visit[nr][nc] = true;
			}
		}
    	
    	System.out.println(answer == 0 ? "TT" : answer);
    }
}