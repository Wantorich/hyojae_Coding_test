import java.util.*;
import java.io.*;

public class Main {
	static int[][] table;
	static int N, M;
	static int[] dr = {-1, -1, 0};
	static int[] dc = {-1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		table = new int[N][M];
		int[][] candys = new int[N][M];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) 
				candys[i][j] = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				table[i][j] = maxCandy(i, j, candys);
			}
		}
		System.out.println(table[N-1][M-1]);
		sc.close();
	}

	private static int maxCandy(int r, int c, int[][] candys) {
		int max = 0, nr, nc;
		for (int i = 0; i < dr.length; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if (!inRange(nr, nc)) continue;
			max = Math.max(max, table[nr][nc]);
		}
		max += candys[r][c];
		return max;
	}
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

