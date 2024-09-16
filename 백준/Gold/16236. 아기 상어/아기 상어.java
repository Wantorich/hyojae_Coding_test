import java.util.*;

public class Main {
    static int N, sharkSize = 2, grid[][], dis, eatCnt;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static int sr, sc;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        grid = new int[N][N];
        for (int i = 0; i < N*N; i++) {
        	grid[i/N][i%N] = scanner.nextInt();
        	if (grid[i/N][i%N] == 9) {
        		sr = i / N; 
        		sc = i % N;
        	}
        }
        
        while (bfs(sr, sc));
        System.out.println(dis);
        
        scanner.close();
    }
    
    private static boolean bfs(int r, int c) {
    	Queue<int[]> q = new LinkedList<int[]>();
    	PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> {
    		int rowCmp = Integer.compare(p1[0], p2[0]);
    		if (rowCmp != 0) return rowCmp;
    		else return Integer.compare(p1[1], p2[1]);
    	});
    	
    	q.offer(new int[] {r, c});
    	boolean [][] visit = new boolean[N][N];
    	visit[r][c] = true;
    	int rotate = 0;
    	while (!q.isEmpty()) {
    		int qSize = q.size();
    		
    		if (!pq.isEmpty()) {
    			// 먹이가 있다면
    			int [] end = pq.poll();

    			// 좌표 업데이트
    			sr = end[0];
    			sc = end[1];
    			
    			// 거리 더해주기
    			dis += rotate;
    			
    			// 아기 상어 위치 바꿔주기
    			grid[r][c] = 0;
    			grid[sr][sc] = 9;
    			
    			// 상어 몸집 업
    			if (++eatCnt == sharkSize) {
    				sharkSize++;
    				eatCnt = 0;
    			}
    			
    			return true;
    		}
    		
    		for (int k = 0; k < qSize; k++) {
    			int [] p = q.poll();
    			
    			for (int i = 0; i < dr.length; i++) {
    				int nr = p[0] + dr[i];
    				int nc = p[1] + dc[i];
    				
    				if (!inRange(nr, nc) || visit[nr][nc] || grid[nr][nc] > sharkSize) continue;
    				
    				// 먹이면 pq에 넣음
    				if (0 < grid[nr][nc] && grid[nr][nc] < sharkSize) 
    					pq.offer(new int[] {nr, nc});
    				
    				q.offer(new int[] {nr, nc});
    				visit[nr][nc] = true;
    			}
    		}
    		rotate++;
    	}
    	
    	return false;
	}

	static boolean inRange(int r, int c) {
    	return 0 <= r && r < N && 0 <= c && c < N;
    }
}