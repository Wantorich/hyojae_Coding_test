import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			char [][] grid = new char[N+1][N+1];
			
			int [][] center = {{N/2, N/2}, {N/2+1, N/2}, {N/2, N/2+1}, {N/2+1, N/2+1}};
			
			for (int i = 0; i < center.length; i++) {
				int x = center[i][0];
				int y = center[i][1];
				
				if (0 < i && i < center.length-1) {
					// B
					grid[x][y] = 'B';
				}
				else {
					// W
					grid[x][y] = 'W';
				}
			}
//			System.out.println(Arrays.deepToString(grid));
			
			int [] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
			int [] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
			int w_cnt = 2, b_cnt = 2;
			
			for (int i = 0; i < M; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				int color = sc.nextInt();
				// 1 -> black, 2 -> white
				char rock, between;
				
				if (color == 1) {
					rock = 'B';
					b_cnt++;
					between = 'W';
				}
				else {
					rock = 'W';
					w_cnt++;
					between = 'B';
				}
				
				grid[x][y] = rock;
				
//				for (int z = 0; z <= N; z++) {
//					System.out.println(Arrays.toString(grid[z]));
//				}
//				System.out.println();
				// B W B , W B W 이 값을 찾아야됌
				
				for (int j = 0; j < dx.length; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if (1 <= nx && nx <= N && 1 <= ny && ny <= N) {
						between = grid[nx][ny];
						
						if ((between == 'B' || between == 'W') && rock != between) { // 다른 돌 발견하면
//							System.out.println("between : " + between);
							// 내 돌이 보일때까지 탐색
//							grid[nx][ny] = rock;
							
//							System.out.println(Arrays.deepToString(grid));
//							for (int z = 0; z <= N; z++) {
//								System.out.println(Arrays.toString(grid[z]));
//							}
//							System.out.println();
							
							int k = 2;
							int nnx = x + dx[j]*k;
							int nny = y + dy[j]*k;
//							int cnt = 1;
							
							Outer : while (1 <= nnx && nnx <= N && 1 <= nny && nny <= N) {
								between = grid[nnx][nny];
//								System.out.println("between : " + between);
								
								if ((between == 'B' || between == 'W')) {
									if (rock != between) {
										k++;
										nnx = x + dx[j]*k;
										nny = y + dy[j]*k;
									}
									
									else {
										// endpoint
										for (int l = 1; l < k; l++) {
											int lx = nnx - dx[j]*l;
											int ly = nny - dy[j]*l;
											grid[lx][ly] = rock;
										}
										if (rock == 'B') {
											b_cnt += k-1;
											w_cnt -= k-1;
										}
										else {
											w_cnt += k-1;
											b_cnt -= k-1;
										}
										break Outer;
									}
								} 
								else {
									break;
								}
							}
									
//									grid[nnx][nny] = rock; // 덧씌우기
////									System.out.println(Arrays.deepToString(grid));
//									for (int z = 0; z < N; z++) {
//										System.out.println(Arrays.toString(grid[z]));
//									}
//									System.out.println();
//									cnt++;
//								else {
//									break;
//								}
//								k++;
							
//							if (rock == 'B') {
//								b_cnt += cnt;
//								w_cnt -= cnt;
//							}
//							else {
//								w_cnt += cnt;
//								b_cnt -= cnt;
//							}
							
//							if (1 <= nnx && nnx <= N && 1 <= nny && nny <= N) {
//								// 마지막 것도 배열 안에 존재해야함
//								if (grid[nnx][nny] == rock) {
//									// 내 돌이랑 색깔이 같으면 가운데 낀거 색깔 바꿈
//									grid[nx][ny] = rock;
//									if (rock == 'B') {
//										b_cnt++;
//										w_cnt--;
//									}
//									else {
//										w_cnt++;
//										b_cnt--;
//									}
//								}
//							}
						}
					}
				}
			}
			
			System.out.printf("#%d %d %d %n", t, b_cnt, w_cnt);
//			System.out.println(Arrays.deepToString(grid));
//			for (int z = 0; z <= N; z++) {
//				System.out.println(Arrays.toString(grid[z]));
//			}
		}
	}

}

/*

*/