import java.util.*;
import java.io.*;

public class Solution {
	static int N, result, coreCnt, cnt, cmp_cnt;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] v;
	static boolean[] core_visit;
	static List<int[]> coreList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			v = new boolean[N][N];
			coreList = new ArrayList<int[]>();
			result = Integer.MAX_VALUE;
			coreCnt = 0;
			cnt = 0;
			cmp_cnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (sc.nextInt() == 1) {
						v[i][j] = true; // 연결할 필요 없는건 방문 처리
						if (0 < i && i < N - 1 && 0 < j && j < N - 1) {
							coreList.add(new int[] { i, j }); // 연결해야 할건 리스트에 넣고
						} 
					}
				}
			}

			core_visit = new boolean[coreList.size()];

			connectCore(0, 0);
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}

	private static void connectCore(int sel, int p) {
		if (sel == coreList.size()) {
			if (p > cnt) {
				cnt = p;
				result = coreCnt;
			} 
			else if (p == cnt) {
				result = Math.min(result, coreCnt);
			}
			return;
		}

		int[] pos = coreList.get(sel);
		// 사방으로 전선 뻗어보기
		for (int j = 0; j < dr.length; j++) {
			boolean find = true;
			int endPoint = -1;
			for (int k = 1; k < N; k++) {
				// 한방향으로 쭉 연결해서 전선을 연결할 수 있는지 확인하기
				int nr = pos[0] + dr[j] * k;
				int nc = pos[1] + dc[j] * k;

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					endPoint = k - 1;
					break; // 배열이 맵을 벗어난 경우
				}

				else { // 배열이 안에 존재하는 경우
					if (v[nr][nc]) {
						find = false;
						endPoint = k;
						break; // 다른 전선이 깔려있거나 core를 만난 경우
					}
				}
			}

			if (find) {
				for (int k = 1; k <= endPoint; k++) {
					// 한방향으로 쭉 연결해서 전선을 연결할 수 있는지 확인하기
					int nr = pos[0] + dr[j] * k;
					int nc = pos[1] + dc[j] * k;
					v[nr][nc] = true;
					coreCnt++;
				}

				// 재귀 호출
				connectCore(sel + 1, p+1);

				// 깔았던 전선 원상복구
				for (int k = 1; k <= endPoint; k++) {
					// 한방향으로 쭉 연결해서 전선을 연결할 수 있는지 확인하기
					int nr = pos[0] + dr[j] * k;
					int nc = pos[1] + dc[j] * k;
					v[nr][nc] = false;
					coreCnt--;
				}
			}
		}
		connectCore(sel + 1, p);
	}
}