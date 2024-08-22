import java.util.*;

public class Main {
	static int N, M, result;
	static char [][] grid;
	static boolean [][] v;
	static ArrayList<ArrayList<int[]>> trackList = new ArrayList<ArrayList<int[]>>(); 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		v = new boolean[N][M];
		
		sc.nextLine();
		grid = new char[N][M];
		for (int i = 0; i < N; i++) {
			grid[i] = sc.nextLine().toCharArray();
			trackList.add(new ArrayList<int[]>());
		}
		
		for (int i = 0; i < N; i++) {
			// 시작점은 i,0
			grid[i][0] = '#';
			trackList.get(i).add(new int[] {i, 0});
			dfs(i, 0, i);
		}
		
		// 지나가면서 내가 지나간 좌표를 기억하고
		// 더 돌데가 없으면, 즉 다음 dfs를 호출하지 못하면 list에 잇는 값들을 모두 0으로 해줌
		
//		for (char [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
		
		System.out.println(result);
		sc.close();
	}

	static int [] dr = {-1, 0, 1};
	
	private static void dfs(int r, int c, int rowIdx) {
		if (c == M-1) {
			// 도착하면
			result++;
//			trackList.get(rowIdx).clear();
			return;
		}
		
		
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + 1;
			
			if (nr < 0 || nr >= N || nc >= M || grid[nr][nc] == '#' || grid[nr][nc] == 'x') continue;
			
			// 파이프 설치해주고
			// 그곳에서 다시 탐색 시작
			
//			System.out.println(rowIdx +  ":" + "list size : " + trackList.get(rowIdx).size());
			int lastIdx = trackList.get(rowIdx).size()-1;
			int lastPos [] = trackList.get(rowIdx).get(lastIdx);
			
			if (lastPos[1] == M-1) {
				// list에 끝점을 도달한 경우가 있다면 나옴
				return;
			}
			
			trackList.get(rowIdx).add(new int[] {nr, nc});
			grid[nr][nc] = '#';
			dfs(nr, nc, rowIdx);
			// 도착하면 밑에서는 더 가면 안되네
			// 중간에 더 갈데가 없으면 돌아오면서 길 다 지워줘야함
		}
		
		// 더 탐색 못하니 지금까지 지나온길 리셋해줌
//		if (trackList.get(rowIdx).size() > 0 && lastPos[1] != M-1) {
//			// list에 끝점을 도달한 경우가 있다면 나옴
//			for (int[] pos : trackList.get(rowIdx)) {
//				grid[pos[0]][pos[1]] = '.';
//			}
//			return;
//		}
	}
}
