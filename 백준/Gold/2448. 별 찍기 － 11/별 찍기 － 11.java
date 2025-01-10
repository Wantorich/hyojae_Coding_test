import java.util.*;
import java.io.*;

public class Main {
	static final char STAR = '*';
	
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	char[][] map = new char[N+1][N*2];
    	
    	staring(N, 0, N-1, map);
    	StringBuilder sb = new StringBuilder();
    	for (char[] row : map) {
    		for (char c : row) {
    			if (c == STAR) sb.append(c);
    			else sb.append(' ');
    		}
    		sb.append("\n");
    	}
    	System.out.println(sb.toString());
    	sc.close();
    }

	private static void staring(int n, int r, int c, char[][] map) {
		if (n % 2 == 1) {
			// base
			// 3*6 staring
			for (int i = 0; i < 3; i++) {
				map[r+i][c-i] = STAR;
				map[r+i][c+i] = STAR;
			}
			for (int i = c-1; i <= c+1; i++)
				map[r+2][i] = STAR;
			return;
		}
		
		// recursive
		int half = n / 2;
		staring(half, r, c, map);
		staring(half, r + half, c - half, map);
		staring(half, r + half, c + half, map);
	}
}
