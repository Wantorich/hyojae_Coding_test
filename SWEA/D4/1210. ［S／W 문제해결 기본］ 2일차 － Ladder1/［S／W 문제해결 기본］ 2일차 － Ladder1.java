import java.util.*;
import java.io.*;

public class Solution {
	static int [][] grid;
	static int [] dr = {-1, 0, 0};
	static int [] dc = {0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
//		int test_case = sc.nextInt();
		int test_case = 10;
		int SIZE = 100;
		
		for (int t = 1; t <= test_case; t++) {
			sc.nextInt();
			grid = new int[SIZE][SIZE];
			int er = -1, ec = -1;
			
			for (int i = 0; i < SIZE*SIZE; i++) {
				grid[i/SIZE][i%SIZE] = sc.nextInt();
				if (grid[i/SIZE][i%SIZE] == 2) {
					er = i / SIZE;
					ec = i % SIZE;
				}
			}
			
			while (er > 0) {
				if (ec-1 >= 0 && grid[er][ec-1] == 1) {
					// 왼쪽 길이 존재한다면, 0이 나올때까지 왼쪽으로 전진
					int nc = ec + dc[1];
					while (nc >= 0 && grid[er][nc] == 1) nc--;
					// 무조건 길이 존재함
					er = er-1; // 위에 길이 존재할테니 한칸 위로 올림
					ec = nc+1;
				} 
				else if (ec+1 < SIZE && grid[er][ec+1] == 1) {
					// 오른쪽 길이 존재한다면, 0이 나올때까지 오른쪽으로 전진
					int nc = ec + dc[2];
					while (nc < SIZE && grid[er][nc] == 1) nc++;
					// 무조건 길이 존재함
					er = er-1; // 위에 길이 존재할테니 한칸 위로 올림
					ec = nc-1;
				}
				else {
					er -= 1;
				}
			}
			
			System.out.printf("#%d %d\n", t, ec);
		}
		sc.close();
	}
}

