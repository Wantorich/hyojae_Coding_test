import java.util.*;
import java.io.*;
import java.math.BigDecimal;

public class Main {
	static int result = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		BigDecimal bi = new BigDecimal(Math.pow(2, N));
		bi = bi.subtract(new BigDecimal(1));
		System.out.println(bi);
		if (N <= 20)
			hanoi(N, 1, 3);
		
		System.out.println(sb.toString());
		sc.close();
		
	}

	private static void hanoi(int n, int s, int e) {
		
		if (n == 1) {
			sb.append(s + " " + e + "\n");
//			System.out.println(s + " " + e);
			return;
		}
		
		hanoi(n-1, s, 6-(s+e));
		sb.append(s + " " + e + "\n");
//		System.out.println(s + " " + e);
		hanoi(n-1, 6-(s+e), e);
	}

}
