import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	char[] chars;
    	Deque<Character> stack = new ArrayDeque<>();
    	String result;
    	for (int i = 0; i < tc; i++) {
    		chars = br.readLine().toCharArray();
    		stack.clear();
    		result = isVps(stack, chars) ? "YES\n" : "NO\n";
    		sb.append(result);
    	}
    	System.out.println(sb.toString());
    }

	private static boolean isVps(Deque<Character> stack, char[] chars) {
		for (int j = 0; j < chars.length; j++) {
			if (chars[j] == '(') 
				stack.push(chars[j]);
			else {
				if (stack.isEmpty() || stack.peek() == ')') 
					return false;
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
}
