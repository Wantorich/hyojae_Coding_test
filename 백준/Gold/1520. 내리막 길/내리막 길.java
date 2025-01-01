import java.util.*;

public class Main {
	static int N, M;
	static int memo[][], map[][];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		memo = new int[N][M];
		map = new int[N][M];
		
		for (int[] row : memo)
			Arrays.fill(row, -1);
		
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();
		
		answer = findWay(0, 0);
		System.out.println(answer);
		
//		for (int[] row : memo)
//			System.out.println(Arrays.toString(row));
		
		sc.close();
	}

	static int[] dr = {1, 0, -1, 0};	
	static int[] dc = {0, 1, 0, -1};	

	private static int findWay(int r, int c) {
		if (r == N-1 && c == M-1) return 1;
		if (memo[r][c] != -1) return memo[r][c];
		
		int curr = map[r][c];
		int result = 0;
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (!inRange(nr, nc) || map[nr][nc] >= curr) 
				continue;
			
			result += findWay(nr, nc);
		}
		
		return memo[r][c] = result;
	}

	private static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

