import java.util.*;

public class Main {
	static int N, M, grid[][], pos[], count, result = Integer.MIN_VALUE;
	static List<int[]> germList = new ArrayList<int[]>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		grid = new int[N][M];
		pos = new int[N*M];
		for (int i = 0; i < N*M; i++) {
			int v = sc.nextInt();
			grid[i/M][i%M] = v;
			pos[i] = v;
			if (pos[i] == 2) {
				// germList에 추가
				germList.add(new int[] {i/M, i%M});
			}
		}
		
		comb(0, 0, new int[3]);
		
		System.out.println(result);
		sc.close();
	}
	
	private static int getSafeArea(int [][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	private static int[][] cloneGrid(int [][] src) {
		int [][] dest = new int[N][M];
		for (int i = 0; i < N; i++) {
			dest[i] = src[i].clone();
		}
		return dest;
	}

	private static void comb(int cnt, int start, int[] sel) {
		if (cnt == sel.length) {
			// 다 뽑음
//			System.out.println(Arrays.toString(sel));
			count++;
			
			// 여기서 벽 만들어주고 BFS 돌림
			// 일단 배열 복사해서 벽 넣어주고
			int[][] copy = cloneGrid(grid);
			for (int i = 0; i < sel.length; i++) {
				int tr = sel[i] / M;
				int tc = sel[i] % M;
				copy[tr][tc] = 1;
			}
			
			bfs(copy);
			result = Math.max(result, getSafeArea(copy));
			return;
		}
		
		for (int i = start; i < N*M; i++) {
			if (pos[i] == 1 || pos[i] == 2) continue; // 벽이 이미 있거나 세균맨 있으면 벽 못세움
			sel[cnt] = i;
			comb(cnt+1, i+1, sel);
		}
	}

	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	private static void bfs(int[][] map) {
		Queue<int[]> q = new LinkedList<int[]>(germList);
		boolean [][] v = new boolean[N][M];
		
		while (!q.isEmpty()) {
			int[] germInfo = q.poll();
			int gr = germInfo[0];
			int gc = germInfo[1];
			
			for (int i = 0; i < dr.length; i++) {
				int nr = gr + dr[i];
				int nc = gc + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc] || map[nr][nc] == 1) continue;
				
				v[nr][nc] = true;
				map[nr][nc] = 2;
				q.offer(new int[] {nr, nc});
			}
		}
	}
}
