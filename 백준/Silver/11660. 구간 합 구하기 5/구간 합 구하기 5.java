import java.util.*;

public class Main {
	static int N, M, answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); M = sc.nextInt();
		int[][] map = new int[N+2][N+2];
		int[][] subsum = new int[N+2][N+2];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int num = sc.nextInt();
				map[i][j] = num;
				subsum[i][j] = subsum[i][j-1] + num;
			}
		}
		
		for (int i = 0; i < M; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			if (x1 == x2 && y1 == y2) {
				sb.append(map[x1][y1]).append("\n");
				continue;
			}
			
			int sum = 0;
			for (int r = x1; r <= x2; r++) {
				sum += subsum[r][y2] - subsum[r][y1-1];
			}
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}
