import java.awt.Point;
import java.util.*;

public class Main {
	static int N, M, T;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Dust[][] map;
	static List<Dust> dustList;
	static List<Dust> tmpDustList = new ArrayList<>();
	static List<Dust> delDustList = new ArrayList<>();
	static Cleaner cleaner;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		
		map = new Dust[N][M];
		cleaner = new Cleaner();
		dustList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int val = sc.nextInt();
				if (val == -1) {
					cleaner.points.add(new Point(i, j));
				} else if (val > 0) {
					Dust dust = new Dust(new Point(i, j), val);
					map[i][j] = dust;
					dustList.add(dust);
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			// 미세먼지 확산
			dustList.forEach(Dust::spread);
			// 새로운 미세먼지 추가
			dustList.addAll(tmpDustList);
			// 추가된 미세먼지 반영
			dustList.forEach(Dust::update);
			// 초기화
			tmpDustList.clear();
			// Cleaner 작동
			cleaner.clean();
			// 없어진 미세먼지 삭제
			dustList.removeAll(delDustList);
			delDustList.clear();
		}

		int result = Arrays.stream(map)
			.mapToInt(arr -> Arrays.stream(arr)
					.filter(val -> val != null)
					.mapToInt(Dust::getDust).sum())
			.sum();
		
		System.out.println(result);
		sc.close();
	}
	
	static class Cleaner {
		List<Point> points = new ArrayList<>();
		
		// 첫번째는 반시계, 두번째는 시계
		void clean() {
			int nr, nc;
			int r, c;
			for (int i = 0; i < points.size(); i++) {
				Point point = points.get(i);
				r = point.x;
				c = point.y;
				
				int[] loop = {r, M-1, r, M-2};
				if (i == 1) {
					loop[0] = N - r - 1;
					loop[2] = N - r - 1;
				}
				
				for (int j = 0; j < loop.length; j++) {
					int len = loop[j];
					for (int k = 0; k < len; k++) {
						if (i == 0) {
							nr = r + dr[j];
							nc = c + dc[j];
						} else {
							nr = r + dr[j] * -1;
							nc = c + dc[j];
						}
						swap(r, c, nr ,nc); 
						r = nr;
						c = nc;
					}
				}
			}
		}
		
		private void swap(int r1, int c1, int r2, int c2) {
			if (map[r2][c2] != null) {
				Dust dust = map[r2][c2];
				dust.point = new Point(r1, c1);
				
				if (this.points.contains(new Point(r1, c1))) {
					delDustList.add(dust);
					map[r2][c2] = null;
					return;
				}
			}
			
			if (this.points.contains(new Point(r1, c1))) {
				map[r2][c2] = null;
				return;
			}
			
			Dust temp = map[r1][c1];
			map[r1][c1] = map[r2][c2];
			map[r2][c2] = temp;
			
			if (map[r1][c1] != null) {
				Dust dust = map[r1][c1];
				dust.point = new Point(r1, c1);
			}
		}
	}

	
	static class Dust {
		Point point;
		int quantity;
		int added;
		
		Dust (Point point, int val) {
			this.point = point;
			this.quantity = val;
		}
		
		int getDust() {
			return this.quantity;
		}
		
		void update() {
			this.quantity += added;
			added = 0;
		}
		
		void spread() {
			int nr, nc;
			int initQuantity = this.quantity;
			int spreadQuantity = initQuantity / 5;
			int spreadCnt = 0;
			
			if (spreadQuantity == 0) 
				return;
			
			for (int i = 0; i < dr.length; i++) {
				nr = point.x + dr[i];
				nc = point.y + dc[i];
				
				if (!inRange(nr, nc) || cleaner.points.contains(new Point(nr, nc))) 
					continue;
				
				if (map[nr][nc] == null) {
					Dust dust = new Dust(new Point(nr, nc), 0);
					tmpDustList.add(dust);
					map[nr][nc] = dust;
				}
				map[nr][nc].added += spreadQuantity;
				spreadCnt++;
			}
			
			map[point.x][point.y].quantity = initQuantity - spreadQuantity * spreadCnt;
		}
		
		private boolean inRange(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < M;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			Dust other = (Dust) obj;
			return this.point.x == other.point.x &&
					this.point.y == other.point.y;
		}
	}
}
