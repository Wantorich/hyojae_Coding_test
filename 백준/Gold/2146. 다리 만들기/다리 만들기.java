import java.util.*;

class Point {
	int r, c;
	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(r, c);
	}
	
	@Override
	public boolean equals(Object object) {
		Point p = (Point) object;
		return this.r == p.r && this.c == p.c; 
	}
	
	public int distance(Point p) {
		return Math.abs(this.r - p.r) + Math.abs(this.c - p.c);
	}
}

public class Main {
	static int N, cnt = 1;;
	static int [][] grid;
	static boolean [][] v;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static Set<Point>[] setList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		grid = new int[N][N];
		v = new boolean[N][N];
		setList = new Set[N*N];
		for (int i = 0; i < setList.length; i++) {
			setList[i] = new HashSet<Point>();
		}
		
		// 격자 채우기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		// 섬 묶어주기 위해서 DFS
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (v[i][j] || grid[i][j] == 0) continue;
				dfs(i, j);
				cnt++;
			}
		}
		
//		for (int [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
		
		int result = Integer.MAX_VALUE;
		
		for (int i = 1; i < cnt; i++) {
			Set<Point> set = setList[i];
			for (Point p : set) {
//				System.out.println(p.r + ", " + p.c);
				
				for (int j = 1; j < cnt; j++) {
					if (i == j) continue;
					// 각 섬들중 하나씩 뽑아서 거리값 비교
					Set<Point> compSet = setList[j];
					for (Point cmpP : compSet) {
						int distance = p.distance(cmpP) - 1;
						result = Math.min(distance, result);
					}
				}
			}
			
//			System.out.println("섬 : " + i);
		}
		
		System.out.println(result);
		sc.close();
	}

	private static void dfs(int r, int c) {
		v[r][c] = true;
		grid[r][c] = cnt;
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
			
			if (grid[nr][nc] == 0) {
				// 옆에 바다가 인접해있으면 여기는 탐색해야하는 점임
				setList[cnt].add(new Point(r, c));
				continue;
			}
			
			v[nr][nc] = true;
			dfs(nr, nc);
		}
	}

}
