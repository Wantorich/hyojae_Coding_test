
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int W = sc.nextInt();
		int H = sc.nextInt();
		int fence = 2 * (W + H);
		sc.nextLine();
		
		int N = sc.nextInt();
		sc.nextLine();
		
		int [][] grid = new int[H+1][W+1];
		
		int dis; int dir; 
		int px = 0; int py = 0;
		
		for (int i = 0; i <= N; i++) {
			dir = sc.nextInt();
			dis = sc.nextInt();
			if (sc.hasNextLine()) sc.nextLine();
			
			switch (dir) {
			case 1: // 북
				grid[0][dis] = 1;
				if (i == N) {
					px = 0;
					py = dis;
				}
				break;
			case 2: // 남
				grid[H][dis] = 1;
				if (i == N) {
					px = H;
					py = dis;
				}
				break;
			case 3: // 서
				grid[dis][0] = 1;
				if (i == N) {
					px = dis;
					py = 0;
				}
				break;
			case 4: // 동
				grid[dis][W] = 1;
				if (i == N) {
					px = dis;
					py = W;
				}
			}
		}
		// 마지막 데이터가 플레이어 위치
		
		int [] dx = {0, 1, 0, -1};
		int [] dy = {1, 0, -1, 0};
		int idx;
		int cnt = 0;
		int result = 0;
		int nx; int ny;
		
		if (px == 0) {
			idx = 0;
		}
		else if (px == H) {
			idx = 2;
		}
		else if (py == W) {
			idx = 1;
		}
		else {
			idx = 3;
		}
		
		while (cnt < fence) {
			
			nx = px + dx[idx];
			ny = py + dy[idx];
			
			if (nx > H || nx < 0 || ny > W || ny < 0) {
				idx = (idx + 1) % 4;
				continue;
			}
			
			cnt++;
//			System.out.println("nx : " + nx + " ny : " + ny);
			
			if (grid[nx][ny] == 1) {
				result += Math.min(cnt, fence - cnt);
//				System.out.println("result : " + Math.min(cnt, fence - cnt));
			}
			
			px = nx;
			py = ny;
		}
		
		System.out.println(result);
		
		sc.close();
	}

}

/*

*/