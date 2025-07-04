import java.io.*;
import java.util.*;

class MatrixWrapper {
	int[][] matrix;
	final int MOD = 1000;
	
	MatrixWrapper(int[][] matrix) {
		this.matrix = matrix;
	}
	
	MatrixWrapper multiple(MatrixWrapper mw) {
		int len = this.matrix.length;
		int[][] multiplied = new int[len][len];
		int[][] ops = mw.matrix;
		
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int result = 0;
				for (int k = 0; k < len; k++) {
					result = (result + this.matrix[i][k] * ops[k][j]) % MOD;
				}
				multiplied[i][j] = result;
			}
		}
		
//		for (int[] row : multiplied) {
//			System.out.println(Arrays.toString(row));
//		}
		
		return new MatrixWrapper(multiplied);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final String delimeter = " ";
		final String newLine = "\n";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				sb.append(matrix[i][j]).append(delimeter);
			}
			sb.append(newLine);
		}
		return sb.toString();
	}
}

public class Main {
	static int[][] initial;
	static Map<Long, MatrixWrapper> memo = new HashMap<>();
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	long B = Long.parseLong(st.nextToken());
    	
    	int[][] matrix = new int[N][N];
    	initial = matrix;
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int j = 0; j < N; j++) {
    			matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
    		}
    	}
    	
    	// Divide and Conquer
    	memo.put(1L, new MatrixWrapper(initial));
    	MatrixWrapper result = dnc(B, new MatrixWrapper(matrix));
    	System.out.println(result.toString());
    }

	private static MatrixWrapper dnc(long n, MatrixWrapper mw) {
		if (n == 1) {
			return new MatrixWrapper(initial);
		}
		
		if (memo.get(n) != null)
			return memo.get(n);
		
		if (n % 2 == 0) {
			MatrixWrapper result = dnc(n/2, mw)
					.multiple(dnc(n/2, mw));
			memo.put(n, result);
			return result;
		} else {
			MatrixWrapper result = dnc(n/2, mw)
					.multiple(dnc(n/2, mw))
					.multiple(new MatrixWrapper(initial));
			memo.put(n, result);
			return result;
		}
	}
}

/*

a1 b1
a2 b2

-> a1*a1 + a1*b1, a1*b1 + b1*b2

*/