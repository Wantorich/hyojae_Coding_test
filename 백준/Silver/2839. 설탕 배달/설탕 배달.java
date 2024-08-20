import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// N을 5로 나눈 나머지의 경우의 수
		// 0 1 2 3 4
		// 0이면 그냥 5로 처리 + 0
		// 3이면 3 1개 추가 + 1
		// 1이면 5 하나 덜넣고 3 두개 넣으면 됌 결과적으로 +1
		// 2이면 2개빼고 3 4개 넣으니 + 2
		// 4면 1개 빼고 3 세개 + 2
		int result = -1;
		int diviend = N / 5;
		// N >= 3
		switch (N % 5) {
			case 0 : 
				result = diviend; break;
			case 1 : 
			case 3 : 
				result = diviend + 1; break;
			case 2 : if (N > 7) result = diviend + 2; break;
			case 4 : if (N > 4) result = diviend + 2; break;
		}
		System.out.println(result);
		sc.close();
	}
}
