import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static final int MOD = 1000000007;
	static long[][] baseMatrix = {{1, 1}, {1, 0}};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	long N = Long.parseLong(br.readLine());
    	long[][] answer = fibo(N, baseMatrix);
    	System.out.println(answer[0][1]);
    }

	private static long[][] fibo(long n, long[][] matrix) {
		if (n == 1) {
			return baseMatrix;
		}
		
		long[][] half = fibo(n/2, matrix);
		long[][] halfResult = multipleByMatrix(half, half);
		
		if (n % 2 == 0)
			return halfResult;
		else
			return multipleByMatrix(halfResult, baseMatrix);
			
	}

	private static long[][] multipleByMatrix(long[][] m1, long[][] m2) {
		long[][] result = new long[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j] += m1[i][k] * m2[k][j];
					result[i][j] %= MOD;
				}
			}
		}
		return result;
	}
}
