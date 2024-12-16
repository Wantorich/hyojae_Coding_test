import java.util.*;

public class Main {
	static int N;
	static long table[][];
	static int map[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		table = new long[N][N];
		for (long[] row : table)
			Arrays.fill(row, -1);
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();

		long answer = jump(0, 0);
		System.out.println(answer);
		
		sc.close();
	}
	
	private static long jump(int r, int c) {
		if (r == N-1 && c == N-1) return 1;
		if (table[r][c] != -1) return table[r][c];
		if (map[r][c] == 0) return 0;
		
		int num = map[r][c];
		
		// right jump
		int nc = c + num;
		long right = 0;
		if (inRange(r, nc)) {
			right = jump(r, nc);
		}
		
		// bottom jump
		int nr = r + num;
		long bottom = 0;
		if (inRange(nr, c)) {
			bottom = jump(nr, c);
		}
		
		return table[r][c] = right + bottom;
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}

