import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int L = sc.nextInt();
		
		// 자기 길이 보다 숫자가 작은 것만 먹을 수 있음
		int [] fruits = new int[N];
		for (int i = 0; i < N; i++) fruits[i] = sc.nextInt();
		
		Arrays.sort(fruits);
		
		for (int i = 0; i < N; i++) {
			if (L >= fruits[i])
				L++;
			else break;
		}
		
		System.out.println(L);
		sc.close();
	}
}
