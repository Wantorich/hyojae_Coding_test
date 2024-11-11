import java.util.*;

public class Main {
	static char map[][];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int adjMatrix[][], answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int w = sc.nextInt();
			int h = sc.nextInt();
			if (w == 0 && h == 0)
				break;
			sc.nextLine();

			int[] cleaner = new int[2];
			List<int[]> points = new ArrayList<>();
			points.add(cleaner);
			answer = Integer.MAX_VALUE;

			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				map[i] = sc.nextLine().toCharArray();
				// 청소기 위치와 더러운곳 미리 저장하기
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 'o') {
						cleaner[0] = i;
						cleaner[1] = j;
					} else if (map[i][j] == '*') {
						points.add(new int[] { i, j });
					}
				}
			}

			// point들에서 자신을 제외하고 다른 포인트까지의 거리 구하기
			int arrSize = points.size();
			adjMatrix = new int[arrSize][arrSize];

			for (int p = 0; p < points.size(); p++) {
				// bfs돌리기
				int[] point = points.get(p);
				boolean[][] visit = new boolean[h][w];
				Queue<int[]> q = new ArrayDeque<>();
				q.offer(point);
				visit[point[0]][point[1]] = true;

				int dis = 1;
				while (!q.isEmpty()) {
					int qSize = q.size();

					for (int s = 0; s < qSize; s++) {
						int[] curr = q.poll();

						for (int i = 0; i < dr.length; i++) {
							int nr = curr[0] + dr[i];
							int nc = curr[1] + dc[i];

							if (nr < 0 || nr >= h || nc < 0 || nc >= w || visit[nr][nc] || map[nr][nc] == 'x')
								continue;

							int[] next = new int[] { nr, nc };
							q.offer(next);
							visit[nr][nc] = true;

							// 더러운 곳인지 확인
							for (int j = 0; j < points.size(); j++) {
								if (Arrays.equals(points.get(j), next)) {
									adjMatrix[p][j] = dis;
									break;
								}
							}
						}
					}
					dis++;
				}
			}

			permutation(0, new int[points.size() - 1], new boolean[points.size() - 1]);
			if (answer == Integer.MAX_VALUE) answer = -1;
			System.out.println(answer);
		}
		sc.close();
	}

	private static void permutation(int k, int[] sel, boolean[] v) {
		if (k == sel.length) {
			// 여기서 계산
			int disSum = 0;
			int from = 0;
			int to = 0;
			for (int i = 0; i < sel.length; i++) {
				to = sel[i] + 1;
				disSum += adjMatrix[from][to];
				if (adjMatrix[from][to] == 0) return; // 연결된 길 없음, 이 경우의 수는 틀린 경우
				from = to;
			}
			answer = Math.min(answer, disSum);
			return;
		}

		for (int i = 0; i < sel.length; i++) {
			if (v[i])
				continue;
			sel[k] = i;
			v[i] = true;
			permutation(k + 1, sel, v);
			v[i] = false;
		}
	}
}
