import java.util.*;

public class Main {
	static int R,C;
	static int [][] grid;
	static Queue<int[]> cheeseQ = new LinkedList<int[]>();
	static List<int[]> airList = new ArrayList<int[]>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		grid = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] == 1) {
					// 치즈이면 따로 보관해줌
					cheeseQ.offer(new int[] {i, j});
				}
			}
		}
		
		// 공기인 부분을 표시해줌
		bfs(0, 0);
		
//		for (int [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
		
		// grid에서 아직도 0인 부분은 공기가 없는 부분임
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == 0) {
					airList.add(new int[] {i, j});
				}
			}
		}
		
		// 치즈가 다 없어질때까지
		int time = 0; int qSize = 0;
		Queue<int[]> removeCheeseQ = new LinkedList<int[]>();
		while (!cheeseQ.isEmpty()) {
			// 시간을 여기서 잼
			qSize = cheeseQ.size();
			
			// 치즈 큐를 돌면서 주변에 공기가 있으면 없애는 치즈큐에 넣어줌
			for (int i = 0; i < qSize; i++) {
				int [] cheese = cheeseQ.poll();
				// 주변에 공기가 있는지 확인
				if (isCorrupt(cheese[0], cheese[1])) {
					removeCheeseQ.offer(new int[] {cheese[0], cheese[1]});
				} else {
					// 부패하지않는다면 다시 cheeseQ에 넣어줌
					cheeseQ.offer(new int[] {cheese[0], cheese[1]});
				}
			}
			
			// removeCheeseQ에 있는 값들을 2로(공기로) 바꿔줌
			while (!removeCheeseQ.isEmpty()) {
				int [] removeCheese = removeCheeseQ.poll();
				grid[removeCheese[0]][removeCheese[1]] = 2;
			}
			
			// airQ에서 탐색을 시작해서 이전의 사이즈와 같은지 판단함
			// 다르면 공기가됐으니 전부 바꿔줘야하고
			// 같으면 여전히 공기가 아닌거임
			for (int [] air : airList) {
				if (isCorrupt(air[0], air[1]) && grid[air[0]][air[1]] == 0) {
					// 이 지점부터 bfs 돌려서 모두 2로 바꿔줌
					bfs(air[0], air[1]);
				}
			}
			time++;
		}
		
//		for (int [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
		
		System.out.printf("%d\n%d", time, qSize);
		sc.close();
	}

	private static boolean isCorrupt(int r, int c) {
		// 사분 탐색해서 주변에 공기(2)가 있는지 확인
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			
			if (grid[nr][nc] == 2) return true;
		}
		return false;
	}

	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		grid[r][c] = 2; // 공기랑 닿는 부분을 2로 처리
		
		while (!q.isEmpty()) { 
			int [] p = q.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || grid[nr][nc] == 1 || grid[nr][nc] == 2) continue;
				
				grid[nr][nc] = 2;
				q.offer(new int[] {nr, nc});
			}
			
		}
		
	}
}
