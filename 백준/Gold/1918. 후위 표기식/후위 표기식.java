import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	char[] exps = sc.nextLine().toCharArray();
    	
    	StringBuilder sb = new StringBuilder();
    	ArrayDeque<Character> st = new ArrayDeque<Character>();
    	
    	for (char exp : exps) {
    		if (Character.isAlphabetic(exp)) {
    			sb.append(exp);
    			continue;
    		}
    		
    		if (exp == '(') {
    			st.push(exp);
    			continue;
    		}
    		
    		if (exp == ')') {
    			while (st.peek() != '(')
    				sb.append(st.pop());
    			st.pop();
    			continue;
    		}
    		
    		while (!st.isEmpty() && pr(exp) <= pr(st.peek())) {
    			sb.append(st.pop());
    		}
    		st.push(exp);
    	}
    	
    	while (!st.isEmpty())
    		sb.append(st.pop());

    	System.out.println(sb.toString());
    	sc.close();
    }

	private static int pr(char c) {
    	if (c == '+' || c == '-') return 1;
    	else if (c == '*' || c == '/') return 2;
    	return 0;
    }
}
