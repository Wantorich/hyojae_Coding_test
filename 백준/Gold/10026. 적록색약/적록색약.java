import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	char[][] originMap = new char[N][N];
    	char[][] rgbMap = new char[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		char[] row = br.readLine().toCharArray();
    		for (int j = 0; j < N; j++) {
    			originMap[i][j] = row[j];
    			if (row[j] == 'R') 
    				rgbMap[i][j] = 'G';
    			else 
    				rgbMap[i][j] = row[j];
    		}
    	}
    	
    	boolean[][] visit = new boolean[N][N];
    	boolean[][] rgbVisit = new boolean[N][N];
    	
    	int originCount = 0;
    	int rgbCount = 0;
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (!visit[i][j]) {
    				bfs(i, j, originMap, visit, originMap[i][j]);
    				originCount++;
    			}
    			if (!rgbVisit[i][j]) {
    				bfs(i, j, rgbMap, rgbVisit, rgbMap[i][j]);
    				rgbCount++;
    			}
    		}
    	}
    	
    	System.out.printf("%d %d", originCount, rgbCount);
    }
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

	private static void bfs(int r, int c, char[][] map, boolean[][] visit, char color) {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		visit[r][c] = true;
		
		int nr, nc;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			
			for (int i = 0; i < dr.length; i++) {
				nr = curr.x + dr[i];
				nc = curr.y + dc[i];
				
				if (nr < 0 || nr >= map.length || nc < 0 || nc >= map.length 
						|| visit[nr][nc] || color != map[nr][nc])
					continue;
				
				visit[nr][nc] = true;
				queue.offer(new Point(nr, nc));
			}
		}
	}
}