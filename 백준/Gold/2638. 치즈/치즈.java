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
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int j = 0; j < M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    		
    	int[] dr = {-1, 0, 1, 0};
    	int[] dc = {0, 1, 0, -1};
    	
    	boolean[][] visit = new boolean[N][M];
    	
    	int answer = 0;
    	int nr, nc;
    	Map<Point, Integer> hitMap = new HashMap<>();
    	
    	for (;; answer++) {
    		for (boolean [] row : visit)
    			Arrays.fill(row, false);
    		
    		ArrayDeque<int[]> queue = new ArrayDeque<>();
    		queue.offer(new int[] {0, 0});
    		visit[0][0] = true;
    		
    		while (!queue.isEmpty()) {
    			int[] cheese = queue.poll();
    			for (int j = 0; j < dr.length; j++) {
    				nr = cheese[0] + dr[j];
    				nc = cheese[1] + dc[j];
    				
    				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc])
    					continue;
    				
    				Point pos = new Point(nr, nc);
    				if (map[nr][nc] == 0) {
    					queue.offer(new int[] {nr, nc});
    					visit[nr][nc] = true;
    				} else {
    					hitMap.put(pos, hitMap.getOrDefault(pos, 0) + 1);
    				}
    			}
    		}
    		
    		if (hitMap.isEmpty())
    			break;
    		
    		hitMap.entrySet()
    			.stream().filter(entry -> entry.getValue() >= 2)
    			.map(Map.Entry::getKey).forEach(pos -> map[pos.x][pos.y] = 0);
    		
    		hitMap.clear();
    	}
    	System.out.println(answer);
    }
}
