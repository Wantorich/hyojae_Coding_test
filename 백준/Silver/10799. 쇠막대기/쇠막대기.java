import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	char[] brackets = sc.nextLine().toCharArray();
    	Deque<Integer> stack = new ArrayDeque<>();
    	int answer = 0;
    	for (int i = 0; i < brackets.length; i++) {
			if (brackets[i] == '(') stack.push(i);
			else {
				if (stack.peek() == i - 1) { 
					answer += stack.size() - 1;
					stack.pop();
				} else {
					stack.pop();
					answer++;
				}
			}
		}
    	System.out.println(answer);
    	sc.close();
    }
}
