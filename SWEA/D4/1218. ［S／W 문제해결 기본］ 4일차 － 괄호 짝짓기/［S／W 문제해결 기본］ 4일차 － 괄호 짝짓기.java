import java.util.*;

public class Solution {
	static int result;
	static char [] inputs;
	static String openBrackets = "({[<";
	static String closeBrackets = ")}]>";
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int test_case = sc.nextInt();

		for (int t = 1; t <= 10; t++) {
			int N = sc.nextInt();
			sc.nextLine();
			inputs = sc.nextLine().toCharArray();
			result = examine() ? 1 : 0;
			System.out.printf("#%d %d\n", t, result);
		}

		sc.close();
	}

	private static boolean examine() {
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < inputs.length; i++) {
			char curr = inputs[i];
			if (openBrackets.indexOf(curr) != -1) {
				// 여는 브라켓이면
				st.push(curr);
			} else {
				// 닫는 괄호일때
				// 스택이 비어있어도 오류
				// 스택의 탑이 짝이 안맞아도 오류
				int index = closeBrackets.indexOf(curr);
				if (st.isEmpty() || st.peek() != openBrackets.charAt(index)) {
					return false;
				}
				st.pop();
			}
		}
		return true;
	}
}
