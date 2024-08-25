import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static char [] nums;
	static char [] middle = {'1', '3', '5', '7', '9'};
	static char [] first = {'2', '3', '5', '7'};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new char[N];
		
		// 소수인지 판단하려면 N의 제곱근까지만, 정확히 말하면 제곱근이 소수점이니까 +1만큼까지만 탐색
		// 일단 N의 자리수만큼 수를 뽑아야함
		// 첫자리에는 2 3 5 7
		// 마지막 자리는 1 3 7 9
		// 중간 자리는 1 3 5 7 9가 올수 있다
		makeNumber(0, nums);
		System.out.println(sb.toString());
		sc.close();
	}

	private static void makeNumber(int k, char [] sel) {
		if (k == N) {
			// 모든 수 다 뽑음
			// 여기 오기 전에 검증된 상태?
			sb.append(new String(sel)).append("\n");
			return;
		}
		
		// 첫 수 뽑는 경우
		if (k == 0) {
			for (int i = 0; i < first.length; i++) {
				sel[k] = first[i];
				makeNumber(k+1, sel);
			}
		}
		
		// 나머지 수 뽑는 경우
		else {
			for (int i = 0; i < middle.length; i++) {
				char addNum = middle[i];
				// i-1 까지 뽑은거와 방금 addNum을 합쳐서 숫자를 만듬
				String numStr = "";
				for (int j = 0; j < k; j++) {
					numStr += sel[j];
				}
				numStr += addNum;
				
				// 여기서 지금까지 뽑은 수가 소수인지 검증
				int targetNum = Integer.parseInt(numStr);
				if (isPrimeNum(targetNum)) {
					// select
					sel[k] = middle[i];
					makeNumber(k+1, sel);
				}
			}
		}
	}

	private static boolean isPrimeNum(int N) {
		for (int i = 2; i < (int) Math.sqrt(N) + 1; i++) {
			if (N % i == 0) return false;
		}
		return true;
	}
}
