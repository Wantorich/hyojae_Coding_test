import java.util.*;

public class Main {
	static int moduler, num;
	static Map<Integer, Long> map = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		num = sc.nextInt();
		int cnt = sc.nextInt();
		moduler = sc.nextInt();
		
		long answer = dnq(cnt) % moduler;
		System.out.println(answer);
		sc.close();
	}

	private static long dnq(int cnt) {
		if (cnt == 1) return num;
		
		if (map.get(cnt) != null)
			return map.get(cnt);
		
		// cnt가 홀수일때 짝수일때
		long result = ((dnq(cnt/2) % moduler) * (dnq(cnt/2) % moduler)) % moduler;
		result = cnt % 2 == 1 ? result * num % moduler : result;
		map.put(cnt, result);
		return result;
	}
}

