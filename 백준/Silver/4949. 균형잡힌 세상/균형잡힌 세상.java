import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String sentence;
    	String bracket = "([)]";
    	StringBuilder sb = new StringBuilder();
    	Deque<String> st = new ArrayDeque<String>();
    	while (!(sentence = br.readLine()).equals(".")) {
    		String brackets = Arrays.stream(sentence.trim().split(""))
    				.filter(bracket::contains).collect(Collectors.joining());
    		st.clear();
    		String result = "yes\n";
    		for (int i = 0; i < brackets.length(); i++) {
    			String b = brackets.charAt(i) + "";
    			if (bracket.indexOf(b) < 2) {
    				st.push(b);
    			} 
    			else {
    				if (st.isEmpty() || (b.equals(")") && !st.peek().equals("(")) 
    						|| (b.equals("]") && !st.peek().equals("["))) {
    					result = "no\n";
    					break;
    				}
    				st.pop();
    			}
    		}
    		if (!st.isEmpty()) result = "no\n";
    		sb.append(result);
    	}
    	System.out.println(sb.toString());
    }
}
