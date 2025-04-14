import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	static int ROW = 12, COL = 6;
	static char[] rgb = {'R', 'G', 'B', 'P', 'Y'};
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Map<Character, List<Point>> pointList = new HashMap<>();
    	for (char color : rgb) {
    		pointList.put(color, new ArrayList<>());
    	}
    	
    	char[][] map = new char[ROW][COL];
    	for (int i = 0; i < ROW; i++) {
    		char[] chars = br.readLine().toCharArray();
    		for (int j = 0; j < COL; j++) {
    			map[i][j] = chars[j];
    			if (chars[j] != '.') {
    				pointList.get(chars[j]).add(new Point(i, j));
    			}
    		}
    	}
    	
    	boolean end = false;
    	int answer = 0;
    	for (; !end; answer++) {
    		end = true;
    		
	    	boolean[][] visit = new boolean[ROW][COL];
	    	List<Point> delList = new ArrayList<>();
	    	int nr, nc;
	    	for (List<Point> points : pointList.values()) {
	    		ArrayDeque<Point> q = new ArrayDeque<>();
	    		for (Point p : points) {
	    			if (visit[p.x][p.y] || map[p.x][p.y] == '.') continue;
	    			q.offer(p);
	    			while (!q.isEmpty()) {
	    				Point curr = q.poll();
	    				for (int i = 0; i < dr.length; i++) {
	    					nr = curr.x + dr[i];
	    					nc = curr.y + dc[i];
	    					
	    					if (nr < 0 || nr >= ROW || nc < 0 || nc >= COL || visit[nr][nc] 
	    							|| map[nr][nc] != map[curr.x][curr.y])
	    						continue;
	    					
	    					visit[nr][nc] = true;
	    					Point next = new Point(nr, nc);
	    					q.offer(next);
	    					delList.add(next);
	    				}
	    			}
	    			if (delList.size() >= 4) {
	    				// 싹다 지워줌
	    				delList.forEach(delP -> map[delP.x][delP.y] = '.');
	    				end = false;
	    			}
	    			q.clear();
	    			delList.clear();
	    		}
	    	}
	    	
	    	// 위에서 아래로 땡겨주기
	    	for (int j = 0; j < COL; j++) {
	    		for (int i = ROW-1; i >= 0; i--) {
	    			if (map[i][j] == '.') continue;
	    			// 문자 발견하면 연결된 문자열을 싹다 내려줌
	    			// swap으로 내릴까
	    			int k = i;
	    			while (k < ROW-1 && map[k+1][j] == '.') {
	    				map[k+1][j] = map[k][j];
	    				map[k][j] = '.';
	    				k++;
	    			}
	    		}
	    	}
	    	
//	    	for (char[] row : map) {
//	    		System.out.println(Arrays.toString(row));
//	    	}
    	}
    	System.out.println(answer-1);
    	
    }
}

/* 
 * 터지는거까진 ok R G B P Y 각각 담아서 bfs와 방문배열 이용해서 tmp에 담고 4이상이면 다 터트린다
 * 그 다음 위에 있는걸 내리는게 문제
 * .으로 바꾸는건 바꾸는건데, 저장할때 x좌표가 작은
 * 
 * 저장을 열에 다가 저장을 한다. 그럼 최대 6개임
 * 열을 한번씩 탐색하면서 아래로 떙겨주는 작업을 한다
 */
