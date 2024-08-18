import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static int N, W, H, total, answer;
	static int[][] grid;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			grid = new int[H][W];
			total = 0;
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] != 0)
						total++;
				}
			}
			// 어디를 깨뜨릴지 고르는 것도 재귀고
			// 벽돌을 깨는 행위도 재귀(DFS)로 구현해야할듯

			recursive(0, new int[N]);
            answer = answer == Integer.MAX_VALUE ? 0 : answer;
			sb.append("#").append(t).append(" ").append(answer).append("\n");
//			System.out.println("answer : " + answer);
		}
		System.out.println(sb.toString());
	}

	private static void recursive(int cnt, int[] sel) {
		// N번동안 어느 위치에 벽돌을 떨어뜨릴지 정하는 경우
		if (cnt == sel.length) {
			// 해당 회차에 맞는 벽돌 떨어뜨리는 경우로 시뮬레이션 돌림
			simulation(sel);
			return;
		}

		for (int i = 0; i < W; i++) {
			sel[cnt] = i;
			recursive(cnt + 1, sel);
		}
	}

	private static void simulation(int[] sel) {
		// 다 안떨어뜨려도 되는 경우는 떨어뜨리려는 자리에 벽돌이 없을때
		int result = 0;

		// 원본 배열 복사 및 방문배열 생성
		boolean[][] v = new boolean[H][W]; // false면 벽돌이 없거나 깨진거, true면 벽돌 존재
		int[][] temp = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp[i][j] = grid[i][j];
				if (temp[i][j] != 0)
					v[i][j] = true; // 벽돌있는칸은 true로 표시
			}
		}

		// 벽돌 아래로 밀리는걸 구현안했네
		// 다 끝나고 v배열이 true인곳을 세면 됌
		for (int i = 0; i < sel.length; i++) {
			int c = sel[i];
			// 아래로 내려가면서 true를 처음 만나는 곳에 투하
			boolean find = false;
			for (int r = 0; r < H; r++) {
				if (v[r][c] && temp[r][c] > 0) {
					dropBomb(r, c, temp, v);
					moveBlock(temp, v);

					find = true;
					break; // 한번만 터트려야함
				}
			}
			if (!find)
				return; // 벽돌 떨어뜨리려는 자리에 벽돌이 없으면 여기에 떨어뜨리면 안되므로 탈출
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (v[i][j])
					result++;
			}
		}

		// 6이 안깨졌다
//		for (boolean[] row : v) {
//			System.out.println(Arrays.toString(row));
//		}
//		System.out.println("result : " + result);
//		System.out.println(Arrays.toString(sel));

//		for (int[] row : temp) {
//			System.out.println(Arrays.toString(row));
//		}

		answer = Math.min(answer, result);
	}

	private static void moveBlock(int[][] temp, boolean[][] v) {
		// 벽돌 옮기기
		for (int c = 0; c < W; c++) {
			for (int r = H - 1; r >= 0; r--) {
				if (!v[r][c]) {
					// 벽돌이 비었다면
					temp[r][c] = 0;
					for (int k = r - 1; k >= 0; k--) {
						// 벽돌 있는것을 찾을때까지
						if (v[k][c]) {
							v[r][c] = true;
							temp[r][c] = temp[k][c];
							v[k][c] = false;
							break;
						}
					}
				}
			}
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void dropBomb(int r, int c, int[][] map, boolean[][] v) {
		v[r][c] = false;

		for (int i = 0; i < dr.length; i++) {
			for (int k = 1; k < map[r][c]; k++) {
				int nr = r + dr[i] * k;
				int nc = c + dc[i] * k;

				if (nr < 0 || nr >= H || nc < 0 || nc >= W || !v[nr][nc])
					continue;

				// 상하좌우로 k-1 칸씩 재귀 호출
				dropBomb(nr, nc, map, v);
			}
		}
	}
}
