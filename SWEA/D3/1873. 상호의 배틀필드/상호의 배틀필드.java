import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			int H = sc.nextInt(); int W = sc.nextInt();
			sc.nextLine();
			
			char [][] map = new char[H][W];
			int tr = 0, tc = 0, dir = 0;
			
			int [] dx = {-1, 1, 0, 0};
			int [] dy = {0, 0, -1, 1};
			
			for (int i = 0; i < H; i++) {
				char [] map_line = sc.nextLine().trim().toCharArray();
				map[i] = map_line;
				for (int j = 0; j < W; j++) {
					char tp = map[i][j];
					if (tp == '<' || tp == '>' || tp == '^' || tp == 'v') {
						switch (tp) {
						case '<': 
							dir = 2;
							break;
						case '>':
							dir = 3;
							break;
						case '^':
							dir = 0;
							break;
						case 'v':
							dir = 1;
							break;
						}
						// tank position
						tr = i; tc = j;
					}
				}
			}
			
			int c_num = sc.nextInt();
			sc.nextLine();
			char [] command = sc.nextLine().trim().toCharArray();
			
			// U D L R -> 전차 방향 바꿈
			// S -> 포탄발사 *는 깨짐 #는 안깨짐
			
			for (char c : command) {
				int nr, nc;
				
				if (c == 'S') {
					// 벽돌벽은 평지로 만들고, 강철벽은 못뚫음
					int loop = 1;
					nr = tr + dx[dir]*loop;
					nc = tc + dy[dir]*loop;
					while (0 <= nr && nr < H && 0 <= nc && nc < W) {
						if (map[nr][nc] == '*') {
							map[nr][nc] = '.';
							break;
						}
						else if (map[nr][nc] == '#') {
							break;
						}
						loop++;
						nr = tr + dx[dir]*loop;
						nc = tc + dy[dir]*loop;
					}
					continue;
				}
				
				switch (c) {
				case 'U':
					dir = 0;
					map[tr][tc] = '^';
					break;
				case 'D':
					dir = 1;
					map[tr][tc] = 'v';
					break;
				case 'L':
					dir = 2;
					map[tr][tc] = '<';
					break;
				case 'R':
					dir = 3;
					map[tr][tc] = '>';
					break;
				default: // Shoot
				}
				nr = tr + dx[dir];
				nc = tc + dy[dir];
				
				if (0 <= nr && nr < H && 0 <= nc && nc < W) {
					char curr_pos = map[nr][nc];
					if (curr_pos == '.') {
						map[nr][nc] = map[tr][tc];
						map[tr][tc] = '.';
						tr = nr;
						tc = nc;
					}
				}
			}
			
			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (char s : map[i]) {
					System.out.print(s);
				}
				System.out.println();
			}
			
		}
	}

}
