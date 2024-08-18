import java.util.*;
import java.io.*;

class Point {
	int r, c, num;

	Point(int r, int c, int num) {
		super();
		this.r = r;
		this.c = c;
		this.num = num;
	}

	@Override
	public String toString() {
		return String.format("Point [r=%s, c=%s, num=%s]", r, c, num);
	}
}

public class Main {
	static int N, M, area = 1, result;
	static int [][] grid, distance;
	static boolean [][] v;
	static List<List<Point>> islandList = new ArrayList<List<Point>>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		grid = new int[N][M];
		v = new boolean[N][M];
		result = Integer.MAX_VALUE;
		Queue<int []> islands = new LinkedList<int[]>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] > 0) {
					islands.offer(new int[] {i, j});
				}
			}
		}
		
		while (!islands.isEmpty()) {
			int [] p = islands.poll();
			if (v[p[0]][p[1]]) continue;
			islandList.add(new ArrayList<Point>());
			dfs(p[0], p[1]);
			area++;
		}
		
		
		distance = new int[islandList.size()+1][islandList.size()+1];
		
		for (List<Point> iList : islandList) {
//			System.out.println("size : " + iList.size());
//			System.out.println(iList);
			for (Point p : iList) {
				getDistance(p);
			}
		}
		
//		System.out.println("---섬---");
//		for (int [] row : grid) {
//			System.out.println(Arrays.toString(row));
//		}
//		
//		System.out.println("---거리---");
//		for (int [] row : distance) {
//			System.out.println(Arrays.toString(row));
//		}
		
		for (int i = 1; i < distance.length; i++) {
			// 시작 정점을 바꿔가면서 최소 거리 탐색
			prim(i);
		}
		
//		System.out.println("primResult : " + result);
		result = result == Integer.MAX_VALUE ? -1 : result;
		System.out.println(result);
		
		sc.close();
	}

	private static void prim(int v) {
		boolean [] visit = new boolean[distance.length];
		int total_weight = 0;
		
		PriorityQueue<int []> pq = new PriorityQueue<int[]>((v1, v2) -> Integer.compare(v1[1], v2[1]));
		pq.offer(new int[] {v, 0});
		
		while (!pq.isEmpty()) {
			int [] edge = pq.poll();
			int next = edge[0];
			int weight = edge[1];
			if (visit[next]) continue; // 이미 방문한 정점은 방문하지 않는다
			
			visit[next] = true;
			total_weight += weight;
			
			for (int j = 1; j < distance.length; j++) {
				if (distance[next][j] > 0 && !visit[j]) {
					// 그래프 가중치가 있고 아직 방문하지 않았다면
//					System.out.println("next : " + next + ", weight : " + weight);
					pq.offer(new int[] {j, distance[next][j]});
				}
			}
		}
		
		for (int i = 1; i < visit.length; i++) {
			if (!visit[i]) {
				// 방문안한 정점이 있다면 탐색 불가
				return;
			}
		}
		
		if (total_weight != 0) {
			result = Math.min(result, total_weight);
		}
	}
	
	private static void getDistance(Point p) {
		// 동서남북 4방향
		for (int i = 0; i < dr.length; i++) {
			int nr = p.r + dr[i];
			int nc = p.c + dc[i];
			int cnt = 1;
			
			while (0 <= nr && nr < N && 0 <= nc && nc < M && grid[nr][nc] != p.num) {
				if (grid[nr][nc] > 0) {
					// 다른 섬을 만난경우
					if (cnt <= 2) break; // 섬의 거리가 1인 경우는 고려하지 않음
					int end = grid[nr][nc];
					if (distance[p.num][end] == 0) {
						distance[p.num][end] = cnt-1;
						distance[end][p.num] = cnt-1;
					} else {
						// 이미 기존재하는 값이 있으면
						distance[p.num][end] = Math.min(distance[p.num][end], cnt-1);
						distance[end][p.num] = Math.min(distance[end][p.num], cnt-1);
					}
					break;
				}
				cnt++;
				nr += dr[i];
				nc += dc[i];
			}
		}
	}


	private static void dfs(int r, int c) {
		v[r][c] = true;
		grid[r][c] = area;
		islandList.get(area-1).add(new Point(r, c, area));
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc] || grid[nr][nc] == 0) continue;
			
			dfs(nr, nc);
		}
	}


	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

}
