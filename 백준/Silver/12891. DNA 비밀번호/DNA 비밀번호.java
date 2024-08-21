import java.util.*;

public class Main {
	static int S, P;
	static String cIndex = "ACGT";
	static int [] standard = new int[4];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.nextInt();
		P = sc.nextInt();
		int result = 0;
		
		sc.nextLine();
		String input = sc.nextLine();
		for (int i = 0; i < 4; i++) standard[i] = sc.nextInt();
		
		int [] indexCnt = new int[4];
		// 초기 세팅, 배열 값 채우기
		for (int i = 0; i < P; i++) {
			char c = input.charAt(i);
			int index = cIndex.indexOf(c);
			indexCnt[index]++;
		}
		
		// A C G T
		if (password(indexCnt)) result ++;
		
		for (int i = 0; i < S - P; i++) {
			int removeIndex = cIndex.indexOf(input.charAt(i)); // C 기준 1
			indexCnt[removeIndex]--;
			int addIndex = cIndex.indexOf(input.charAt(i+P));
			indexCnt[addIndex]++;
			if (password(indexCnt)) result++;
		}
		
		System.out.println(result);
		sc.close();
	}

	private static boolean password(int[] indexCnt) {
		for (int i = 0; i < indexCnt.length; i++) {
			// 기준보다 인덱스카운트에 있는 값이 같거나 커야함
			if (standard[i] > indexCnt[i]) return false;
		}
		return true;
	}
}
