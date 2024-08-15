import java.util.*;
import java.io.*;

class Point {
	int r, c, num;

	Point(int r, int c, int num) {
		super();
		this.r = r;
		this.c = c;
		this.num = num;
	}
}

public class Main {
	static int N, M, watch = 0, area, result;
	static int [][] grid;
	static int [][] v;
	static List<Point> plist = new ArrayList<Point>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		grid = new int[N][M];
		v = new int[N][M];
		area = 0;
		result = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] > 0) {
					v[i][j] = -1; // 벽이나 감시카메라는 -1로
					if (grid[i][j] < 6) {
						plist.add(new Point(i, j, grid[i][j]));
					}
				} else {
					area++;
				}
			}
		}
		
//		System.out.println("area : " + area);
		
//		for (int [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
//		
		combination(0);
		System.out.println(result);
		
		sc.close();
	}

	private static void combination(int index) {
		if (index == plist.size()) {
			int remain = area - watch;
//			System.out.println("watch : " + watch);
			result = Math.min(result, remain);
			return;
		}
		
		Point p = plist.get(index);
		
		switch(p.num) {
		case 1:
			for (int i = 0; i < dr.length; i++) {
				watching(p, i, true);
				combination(index+1);
				watching(p, i, false);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				watching(p, i, true);
				watching(p, i+2, true);
				combination(index+1);
				watching(p, i, false);
				watching(p, i+2, false);
			}
			break;
		case 3:
			for (int i = 0; i < dr.length; i++) {
				watching(p, i % 4, true);
				watching(p, (i+1) % 4, true);
				combination(index+1);
				watching(p, i % 4, false);
				watching(p, (i+1) % 4, false);
			}
			break;
		case 4:
			for (int i = 0; i < dr.length; i++) {
				watching(p, i % 4, true);
				watching(p, (i+1) % 4, true);
				watching(p, (i+2) % 4, true);
				combination(index+1);
				watching(p, i % 4, false);
				watching(p, (i+1) % 4, false);
				watching(p, (i+2) % 4, false);
			}
			break;
		case 5:
			for (int i = 0; i < dr.length; i++) {
				watching(p, i % 4, true);
				watching(p, (i+1) % 4, true);
				watching(p, (i+2) % 4, true);
				watching(p, (i+3) % 4, true);
				combination(index+1);
				watching(p, i % 4, false);
				watching(p, (i+1) % 4, false);
				watching(p, (i+2) % 4, false);
				watching(p, (i+3) % 4, false);
			}
			break;
		}
	}
	
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	// dir -> 0, 1, 2, 3 == 북 동 남 서
	private static void watching(Point p, int dir, boolean status) {
		int r = p.r + dr[dir];
		int c = p.c + dc[dir];
		
		while (r >= 0 && r < N && c >= 0 && c < M && grid[r][c] != 6) {
			
			if (grid[r][c] == 0) {
				// 감시해야하면 카메라 개수를 늘리고, 아니면 줄인다
				v[r][c] = status ? v[r][c]+1 : v[r][c]-1;
			}
			
			if (v[r][c] == 1) {
				// 한대 이상의 카메라로 관찰되고있다
				watch = status ? watch+1 : watch;
			} else if (v[r][c] == 0) {
				// 감시되고있지않다
				watch = !status ? watch-1 : watch;
			}
			
			r += dr[dir];
			c += dc[dir];
		}
	}
}