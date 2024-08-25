import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int N;
	static int grid[][];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		grid = new int[N][N];
		
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			char [] charArr = sc.nextLine().toCharArray();
			for (int j = 0; j < N; j++) {
				grid[i][j] = charArr[j] - '0';
			}
		}
		
		divideNConquer(0, 0, N);
		System.out.println(sb.toString());
		sc.close();
	}

	private static void divideNConquer(int r, int c, int len) {
		if (len == 1) {
			// 정사각형 한개
			sb.append(grid[r][c]);
			return;
		}
		
		// 해당 영역이 한 색깔로 가득 차있는지 체크
		// 그래프의 영역의 총합이랑 가로 * 세로가 같거나 0이거나 확인하면 됌
		// 채워져있는게 1이니까
		int sum = 0;
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				sum += grid[i][j];
			}
		}
		
		if (sum == 0) {
			// 흰색 -> 0
			sb.append(0);
		} else if (sum == len * len) {
			// 검은색 -> 1
			sb.append(1);
		} else {
			// 그렇지 않다면 4영역으로 분리
			sb.append("(");
			int halfLen = len >> 1;
			divideNConquer(r, c, halfLen);
			divideNConquer(r, c + halfLen, halfLen);
			divideNConquer(r + halfLen, c, halfLen);
			divideNConquer(r + halfLen, c + halfLen, halfLen);
			sb.append(")");
		}
	}
}
