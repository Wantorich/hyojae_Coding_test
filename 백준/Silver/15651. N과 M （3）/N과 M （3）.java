import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class Main {
	static StringBuffer sb = new StringBuffer();
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		arr = IntStream.rangeClosed(1, N).toArray();

		permutation(new int[M], 0);
		System.out.println(sb.toString());
		sc.close();
	}

	private static void permutation(int[] sel, int k) {
		if (k == sel.length) {
			for (int num : sel) {
				sb.append(String.valueOf(num));
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < arr.length; i++) {

			sel[k] = arr[i];
			permutation(sel, k + 1);
		}

	}

}
