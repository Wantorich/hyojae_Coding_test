import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static StringBuffer sb = new StringBuffer();
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[N];
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			max = Math.max(max, arr[i]);
		}

		Arrays.sort(arr);
		permutation(new int[M], 0, new boolean[max+1]);
		System.out.println(sb.toString());
		sc.close();
	}

	private static void permutation(int[] sel, int k, boolean [] v) {
		if (k == sel.length) {
			for (int num : sel) {
				sb.append(String.valueOf(num));
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!v[i]) {
				if (k >= 1 && sel[k-1] > arr[i]) continue;
				v[i] = true;
				sel[k] = arr[i];
				permutation(sel, k + 1, v);
				v[i] = false;
			}
		}
	}
}
