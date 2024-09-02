import java.util.*;

public class Main {
	static int K, H, grid[][];
	static String[] command;
	static int[] order = new int[4];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		grid = new int[1 << K][1 << K];
		int length = 1 << K;
		sc.nextLine();
		command = sc.nextLine().split(" ");
		H = sc.nextInt();

		// D U R L이 실행될때마다 겹쳐지는 부분이 생김
		// 상태변화를 어떻게 체크할지

		// 마지막 부분만 체크하면 결국 전체는 똑같아지는건가? 패턴이?
		// 3 -> 0 1 2 3
		// 2 -> 1 0 3 2
		// 1 -> 2 3 0 1
		// 0 -> 3 2 1 0

		int[][] tmp = new int[3][3];
		for (int[] row : tmp)
			Arrays.fill(row, -1);
		tmp[1][1] = H;

		int i = command.length - 1;
		while (command[i].equals("L") || command[i].equals("R"))
			i--;
		// U나 D 만남
		int pos = command[i].equals("U") ? 2 : 0;
		tmp[pos][1] = (H + 2) % 4;

		i = command.length - 1;
		while (command[i].equals("U") || command[i].equals("D"))
			i--;
		// L이나 R 만남
		// 아래로 펼쳐짐
		for (int r = 0; r <= 2; r++) {
			if (tmp[r][1] == -1)
				continue;
			pos = command[i].equals("L") ? 2 : 0;
			tmp[r][pos] = tmp[r][1] % 2 == 0 ? tmp[r][1] + 1 : tmp[r][1] - 1;
		}
		
		int idx = 0;
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (tmp[j][k] == -1) continue;
				order[idx++] = tmp[j][k];
			}
		}

		divideNconquer(0, 0, length);

		for (int[] row : grid) {
			for (int col : row)
				System.out.print(col + " ");
			System.out.println();
		}

		sc.close();
	}

	private static void divideNconquer(int r, int c, int len) {
		if (len == 2) {
			// 구멍 뚫자
			int idx = 0;
			for (int i = r; i < r + 2; i++) {
				for (int j = c; j < c + 2; j++) {
					grid[i][j] = order[idx++];
				}
			}
			return;
		}

		// 4분할
		int half = len / 2;
		divideNconquer(r, c, half);
		divideNconquer(r, c + half, half);
		divideNconquer(r + half, c, half);
		divideNconquer(r + half, c + half, half);
	}
}
