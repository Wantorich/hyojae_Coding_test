import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static char [][] grid;
	static int [][] road;
	static boolean [][] v;
	
	static String markers = "|-+1234";
	static int [] connect = {5, 10, 15, 3, 6, 12, 9};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		grid = new char[N][M];
		road = new int[N][M];
		v = new boolean[N][M];
		
		int index;
		for (int i = 0; i < N; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < M; j++) {
				char c = line.charAt(j);
				grid[i][j] = c;
				if ((index = markers.indexOf(c)) != -1) {
					// 마커가 존재한다면
					int val = connect[index];
					marking(i, j, val);
				}
			}
		}
		
//		for (int [] row : road) {
//			System.out.println(Arrays.toString(row));
//		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == '.' && road[i][j] != 0) {
					// 파이프가 부서진 곳
					int targetIdx = -1;
					for (int k = 0; k < connect.length; k++) {
						if (connect[k] == road[i][j]) {
							targetIdx = k;
							break;
						}
					}
					System.out.printf("%d %d %c", i+1, j+1, markers.charAt(targetIdx));
					return;
				}
			}
		}
		
		sc.close();
	}
// 첫번째는 왼쪽, 2번째는 위쪽, 3번째는 오른쪽, 4번째는 아래쪽

	private static void marking(int r, int c, int val) {
		String binary = String.format("%4s", Integer.toBinaryString(val)).replaceAll(" ", "0");
//		System.out.println("binary : " + binary);
		if (binary.charAt(0) == '1') {
			road[r][c-1] |= 2;
			road[r][c] |= 8;
		} 
		if (binary.charAt(1) == '1') {
			road[r-1][c] |= 1;
			road[r][c] |= 4;
		}
		if (binary.charAt(2) == '1') {
			road[r][c+1] |= 8;
			road[r][c] |= 2;
		} 
		if (binary.charAt(3) == '1') {
			road[r+1][c] |= 4;
			road[r][c] |= 1;
		}
	}
}
