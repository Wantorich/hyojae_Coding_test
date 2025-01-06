import java.util.*;

public class Main {
    static final int X = 1000000007;
    static Map<Long, Long> memo = new HashMap<>();
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long M = sc.nextInt();
		long answer = 0;
		
		for (int i = 0; i < M; i++) {
			long N = sc.nextInt();
			long S = sc.nextInt();
			// S / N 을 answer에 더해주면 됌
			// 모듈러 연산때린거
			// 즉 S * N ^ X-2 % MOD
			// = S % MOD * N ^ X-2 % MOD
			if (S % N == 0) {
				answer += S / N;
				continue;
			}
			
			long powered = memo.getOrDefault(N, powering(N, X-2));
			memo.put(N, powered);
			answer = (answer + ((S % X) * powered % X) % X) % X;
		}
		
		System.out.println(answer);
		sc.close();
	}

	private static long powering(long n, int exp) {
		if (exp == 1) return n;
		
		long half = powering(n, exp / 2) % X;
		long result = (half * half) % X;
		if (exp % 2 == 1)
			result = (result * (n % X)) % X;
		return result;
	}
}
