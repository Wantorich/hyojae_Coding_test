import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int N, er, ec, result, cnt;
	static boolean find;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		er = sc.nextInt();
		ec = sc.nextInt();
		
		// 2^N을 변의 길이로하는 격자
		// r, c, 변의 길이
		divideNConquer(0, 0, 1 << N, 0);
		
		System.out.println(result);
		
		sc.close();
	}

	private static void divideNConquer(int r, int c, int len, int count) {
		if (find) return;
		
		if (len == 2) {
			// 한 변의 길이가 2이면 base part
			for (int i = r; i < r+2; i++) {
				for (int j = c; j < c+2; j++) {
					count++;
					if (i == er && j == ec) {
						result = count - 1;
						find = true;
						return;
					}
				}
			}
			return;
		}
		
		if (len > 2) {
			// 2보다 큰 경우에는 divide and conquer
			int halfLen = len >> 1;
			int halfLenPow = halfLen * halfLen;
			
			if (er < r + halfLen) {
				if (ec < c + halfLen) {
					divideNConquer(r, c, halfLen, count);
					
				} else {
					divideNConquer(r, c + halfLen, halfLen, count + halfLenPow);
				}
			}
			else {
				if (ec < c + halfLen) {
					divideNConquer(r + halfLen, c, halfLen, count + halfLenPow*2);
				} else {
					divideNConquer(r + halfLen, c + halfLen, halfLen, count + halfLenPow*3);
				}
			}
			
		}
	}
}
