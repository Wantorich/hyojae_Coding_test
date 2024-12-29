import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextInt();
		long B = sc.nextInt();
		
		solve(A, B, 1);
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
		
		sc.close();
	}

	private static void solve(long a, long b, int cnt) {
		if (a == b) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		long multifly = a * 2;
		long addOne = a * 10 + 1;
		
		if (multifly <= b)
			solve(multifly, b, cnt+1);
		
		if (addOne <= b)
			solve(addOne, b, cnt+1);
	}
}
