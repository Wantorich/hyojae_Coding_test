import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class Point {
	int r, c;

	Point(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N, result;
	static int [][] grid;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		grid = new int[N][N];
		for (int i = 0; i < N*N; i++) grid[i/N][i%N] = sc.nextInt();
		
		// d는 0 -> 가로, 1 -> 대각선, 2 -> 세로
		// 다만 대각선일땐 4칸 차지함
		dfs(new Point(0, 0), new Point(0, 1), 0);
		
		System.out.println(result);
		sc.close();
	}

	private static void dfs(Point p1, Point p2, int d) {
		if (p2.r >= N || p2.c >= N) return; // out of range
		
		if (grid[p2.r][p2.c] == 1) return; // block
		
		if (d == 1 && (grid[p1.r+1][p1.c] == 1 || grid[p1.r][p1.c+1] == 1))
			return; // 대각선일때도 장애물 체크
		
		if (p2.r == N-1 && p2.c == N-1) {
			result++;
			return;
		}
		
		switch (d) {
		case 0 :
			// 가로 일땐 가로방향, 대각선 방향
			dfs(p2, new Point(p2.r, p2.c+1), 0);
			dfs(p2, new Point(p2.r+1, p2.c+1), 1);
			break;
		case 1 :
			dfs(p2, new Point(p2.r, p2.c+1), 0);
			dfs(p2, new Point(p2.r+1, p2.c+1), 1);
			dfs(p2, new Point(p2.r+1, p2.c), 2);
			break;
		case 2 :
			dfs(p2, new Point(p2.r+1, p2.c), 2);
			dfs(p2, new Point(p2.r+1, p2.c+1), 1);
		}
	}
}
