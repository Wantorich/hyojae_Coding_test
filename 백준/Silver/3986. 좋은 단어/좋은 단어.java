import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		Deque<Character> stack = new ArrayDeque<>();
		
		char[] chars;
		int answer = 0;
		for (int i = 0; i < N; i++) {
			stack.clear();
			chars = sc.nextLine().toCharArray();
			for (int j = 0; j < chars.length; j++) {
				if (!stack.isEmpty() && stack.peek() == chars[j])
					stack.pop();
				else 
					stack.push(chars[j]);
			}
			
			if (stack.isEmpty()) 
				answer++;
		}
		
		System.out.println(answer);
		sc.close();
	}
}