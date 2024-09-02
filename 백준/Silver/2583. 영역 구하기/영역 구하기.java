import java.util.*;

public class Main {
	static int M, N, K, areaCnt;
	static boolean [][] grid;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		K = sc.nextInt();
		grid = new boolean[M][N];
		
		// 원래는 N*M인데, 
		
		// 대칭이여도 상관없지않나
		for (int i = 0; i < K; i++) {
			int c1 = sc.nextInt();
			int r1 = sc.nextInt();
			int c2 = sc.nextInt() - 1;
			int r2 = sc.nextInt() - 1;
			
			// 항상 r1, c1이 더 작음
			for (int r = r1; r <= r2; r++) {
				for (int c = c1; c <= c2; c++) {
					grid[r][c] = true;
				}
			}
		}
		
//		for (boolean [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
//		 
		//dfs돌리기
		List<Integer> areaList = new ArrayList();
		
		for (int i = 0; i <M; i++) {
			for (int j = 0; j < N; j++) {
				if (!grid[i][j]) {
					areaCnt = 1;
					dfs(i, j);
					areaList.add(areaCnt);
				}
			}
		}
		
		Collections.sort(areaList);
		
		System.out.println(areaList.size());
		for (int num : areaList) {
			System.out.print(num + " ");
		}
		
	
		
		// 2,0 -> 3,3
		// 1,2 -> 5,2
		
		// 좌표가 들어오는데 x1, y1, x2, y2
		// 뒤 좌표는 -1,-1 해야할거같은데
		// c, N-1-y1, 
		
		sc.close();
	}

	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < M && 0 <= c && c < N;
	}
	
	private static void dfs(int r, int c) {
		grid[r][c] = true;
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (!inRange(nr,nc) || grid[nr][nc]) continue;
			
			areaCnt++;
			dfs(nr, nc);
		}
	}
}

/*
좌표가 0,0이 좌하단
N,M이 우상단

가로 길이가 N, 세로 길이가 M

방문배열 만들어서 색칠 하고
나머지 구역 갯수세면 될거가틍ㄴ데
*/