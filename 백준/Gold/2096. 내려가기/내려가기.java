import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int INF = Integer.MAX_VALUE / 3;
		final int col = 3;
		int N = sc.nextInt();
		int[][] table = new int[N][col];
		int[][] maxTable = new int[N][col];
		int[][] minTable = new int[N][col];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < col; j++) {
				table[i][j] = sc.nextInt();
				maxTable[i][j] = table[i][j];
			}
		}

		for (int[] row : minTable)
			Arrays.fill(row, INF);

		minTable[0] = table[0].clone();
		
		int[] dc = {-1, 0, 1};
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < col; j++) {
				int nr = i - 1;
				for (int k = 0; k < dc.length; k++) {
					int nc = j + dc[k];
					if (nc < 0 || nc >= col) continue;
					maxTable[i][j] = Math.max(maxTable[i][j], table[i][j] + maxTable[nr][nc]);
					minTable[i][j] = Math.min(minTable[i][j], table[i][j] + minTable[nr][nc]);
				}
			}
		}
		
		int max = Arrays.stream(maxTable[N-1]).max().getAsInt();
		int min = Arrays.stream(minTable[N-1]).min().getAsInt();
		
		System.out.println(max + " " + min);
		sc.close();
	}
}
