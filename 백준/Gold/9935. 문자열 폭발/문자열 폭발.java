import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	String line = sc.nextLine();
    	String bomb = sc.nextLine();
    	Deque<Character> stack = new ArrayDeque<>();
    	Deque<Character> temp = new ArrayDeque<>();
    	
    	for (int i = 0; i < line.length(); i++) {
    		char c = line.charAt(i);
    		stack.push(c);
    		if (bomb.indexOf(c + "") == bomb.length()-1) {
    			int index = bomb.length() - 1;
    			while (!stack.isEmpty() && index >= 0 &&
    					(stack.peek() == bomb.charAt(index--))) {
    				temp.push(stack.pop());
    			}
    			if (temp.size() == bomb.length()) 
    				temp.clear();
    			else {
    				while (!temp.isEmpty())
    					stack.push(temp.pop());
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	while (!stack.isEmpty())
    		sb.append(stack.pollLast());
    	String answer = sb.length() == 0 ? "FRULA" : sb.toString();
    	System.out.println(answer);
    	sc.close();
    }
}
