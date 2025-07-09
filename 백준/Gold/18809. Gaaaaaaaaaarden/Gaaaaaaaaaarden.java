import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.awt.Point;

public class Main {
	static int answer;
	static int N, M, G, R, map[][];
	static List<Point> pointList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int val;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				if (val == 2) {
					pointList.add(new Point(i, j));
				}
			}
		}
		
		// index, greenCnt, redCnt, sel[] -> 놓을 수 있는 자리 
		// sel -> 0 : 아무것도 없음, 4 : Green, 8 : Red
		selPos(0, G, R, new int[pointList.size()]);
		System.out.println(answer);
	}

	private static void selPos(int start, int gc, int rc, int[] sel) {
		// 종료 조건
		if (gc == 0 && rc == 0) {
			// simulation
			int flowerCnt = simulation(sel);
			answer = Math.max(answer, flowerCnt);
			return;
		}
		
		if (start == sel.length)
			return;
		
		for (int i = start; i < sel.length; i++) {
			int initial = sel[i];
			if (gc > 0) {
				sel[i] = 4;
				selPos(i+1, gc-1, rc, sel);
				sel[i] = initial;
			}
			
			if (rc > 0) {
				sel[i] = 8;
				selPos(i+1, gc, rc-1, sel);
				sel[i] = initial;
			}
		}
	}

	private static int simulation(int[] sel) {
		// sel을 순회하면서 색깔에 맞게 map에 배양액 뿌림 
		// map 복사
		int result = 0;
		int[][] cloneMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			cloneMap[i] = map[i].clone();
		}
		
		ArrayDeque<Point> q = new ArrayDeque<>();
		for (int i = 0; i < sel.length; i++) {
			if (sel[i] != 0) {
				Point p = pointList.get(i);
				cloneMap[p.x][p.y] = sel[i]; 
				q.offer(p);
			}
		}
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int nr, nc;
		Map<Point, Integer> spreadMap = new HashMap<>();
		
		while (!q.isEmpty()) {
			int qSize = q.size();
			spreadMap.clear();
			for (int j = 0; j < qSize; j++) {
				Point curr = q.poll();
				int status = cloneMap[curr.x][curr.y];
				
				// 상하좌우 배양액 뿌리기 
				// 배양액 퍼지는 조건 설정하기 -> map의 위치가 2인 곳만 가능 
				for (int i = 0; i < dr.length; i++) {
					nr = curr.x + dr[i];
					nc = curr.y + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M 
							|| (cloneMap[nr][nc] != 2 && cloneMap[nr][nc] != 1))
						continue;
						
					// 어디 위치에, 무슨 색 -> 2가지 정보, bitmasking? 2자리로 1111
					Point next = new Point(nr, nc);
					spreadMap.compute(next, (k, prev) -> prev == null ? status : prev | status);
				}
			}
			
			// Map에 저장한거 일괄 반영해주기 
			for (Entry<Point, Integer> entry : spreadMap.entrySet()) {
				// 꽃인 경우는 1100,그린은 100, 레드는 1000
				Point loc = entry.getKey();
				int val = entry.getValue();
				cloneMap[loc.x][loc.y] = val;
				
				if (entry.getValue() == 12) {
					result++;
					continue;
				}
				
				q.offer(loc);
			}
		}
		
		return result;
	}
}

/*
 * 배양액을 뿌릴 수 있는 땅의 개수 >= 배양액 수
 * 즉, 어디 뿌릴지 정해야함 -> 경우의 수가 다 다름 (완탐) 
 * 근데 같은색 배양액의 순서를 바꾸는건 같은 경우임 -> 가지치기 
 * 매 칸마다 초록색-빨간색 선택 
 * 배양액 배치 + 시뮬레이션  
 *  
 */