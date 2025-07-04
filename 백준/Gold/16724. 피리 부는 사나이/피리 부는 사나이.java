import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.Point;

public class Main {
	static final String INDEX = "URDL";
	static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	char[][] map = new char[N][M];
    	
    	// 지도 입력
    	for (int i = 0; i < N; i++) {
    		map[i] = br.readLine().toCharArray();
    	}
    	
    	// 순회하면서 SAFE ZONE 카운팅
    	boolean[][] visit = new boolean[N][M];
    	int answer = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			if (!visit[i][j]) {
    				move(i, j, map, visit);
    				answer++;
    			}
    		}
    	}
    	
    	System.out.println(answer);
//    	for (boolean[] row : visit) {
//    		System.out.println(Arrays.toString(row));
//    	}
    }

	private static void move(int i, int j, char[][] map, boolean[][] visit) {
		visit[i][j] = true;
		int dir = INDEX.indexOf(map[i][j]);
		int nr = i + dr[dir];
		int nc = j + dc[dir];
		
		if (!visit[nr][nc]) {
			move(nr, nc, map, visit);
		}
		
		// 내가 가는 방향 + 나에게 올 수 있는 방향 -> 상하좌우 체크
		for (int k = 0; k < dr.length; k++) {
			int pr = i + dr[k];
			int pc = j + dc[k];
			
			if (pr < 0 || pr >= map.length || pc < 0 || pc >= map[0].length || visit[pr][pc]) 
				continue;
			
			// 즉 내 이전 방향이 현재 내 위치를 향해 있는지를 봐야함
			if (match(i, j, pr, pc, map)) {
				move(pr, pc, map, visit);
			}
		}
	}

	private static boolean match(int r, int c, int pr, int pc, char[][] map) {
		int dir = INDEX.indexOf(map[pr][pc]);
		int nr = pr + dr[dir];
		int nc = pc + dc[dir];
		if (r == nr && c == nc) 
			return true;
		else
			return false;
	}
}

/*

배열 전부 순회하면서(10^6) 방문배열 체크하면서 한번 방문한 곳은 체크해놓고
적힌대로 이동한다 쭉.. 재귀로
SAFE ZONE 개수 늘리면서
내 지점에 올 수 있는 것도 체크를 해야할거같네

*/