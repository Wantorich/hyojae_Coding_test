import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] A, B;
	static String[][] memo;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = sc.nextInt();
		
		M = sc.nextInt();
		B = new int[M];
		for (int i = 0; i < M; i++)
			B[i] = sc.nextInt();
		
		memo = new String[N][M];
		
		String[] result = common(0, 0).split(" ");
		List<String> answer = Arrays.stream(result)
				.filter(str -> str.length() > 0)
				.collect(Collectors.toList());
		
		System.out.println(answer.size());
		System.out.println(String.join(" ", answer));
		
		sc.close();
	}

	private static String common(int i, int j) {
		if (i >= N || j >= M) return "";
		
		if (memo[i][j] != null)
			return memo[i][j];
		
		String result = null;
		
		if (A[i] == B[j]) {
			String curr = A[i] + "";
			String next = common(i+1, j+1);
			result = getLast(curr + " " + next, next);
		} else {
			result = getLast(common(i, j+1), common(i+1, j));
		}
		
		return memo[i][j] = result;
	}
	
	static String getLast(String s1, String s2) {
		if (s1.isBlank()) return s2;
		else if (s2.isBlank()) return s1;
		
		String[] longer, shorter;
		String[] split_s1 = s1.split(" ");
		String[] split_s2 = s2.split(" ");
		
		if (split_s1.length > split_s2.length) {
			longer = split_s1;
			shorter = split_s2;
		} else {
			longer = split_s2;
			shorter = split_s1;
		}
		
		for (int i = 0; i < shorter.length; i++) {
			int a = Integer.parseInt(shorter[i]);
			int b = Integer.parseInt(longer[i]);
			
			if (a < b) {
				return String.join(" ", longer);
			} else if (a > b) {
				return String.join(" ", shorter);
			}
		}
		return String.join(" ", longer);
	}
}
