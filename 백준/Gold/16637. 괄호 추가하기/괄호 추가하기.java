import java.util.*;

public class Main {
    static int N, answer = Integer.MIN_VALUE;
    static Stack<Integer> numStack = new Stack<Integer>();
    static Stack<Character> opStack = new Stack<Character>();
	static List<Integer> operand;
	static List<Character> operator;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        operand = new ArrayList<Integer>();
        operator = new ArrayList<Character>();
        sc.nextLine();
        
        String input = sc.nextLine();
        for (int i = 0; i < input.length(); i++) {
        	char c = input.charAt(i);
        	if (i % 2 == 0) operand.add(c - '0');
        	else operator.add(c);
        }
        // 첫 수식은 괄호치는게 의미가없고
        // 두번째 수식부터 마지막 전까지 즉 1 <= range <= operand.lenth - 1
        if (operand.size() == 1) {
        	System.out.println(operand.get(0));
        	return;
        }
        
        subset(1, new boolean[operand.size()-1]);
        System.out.println(answer);
        
        sc.close();
    }

	private static void subset(int k, boolean [] sel) {
		if (k == sel.length) {
			// 여기서 수식만들고 값 도출
//			System.out.println(Arrays.toString(sel));
			calculate(sel);
			return;
		}
		
		if (!sel[k-1]) {
			// 인접해서는 넣을 수 없다
			sel[k] = true;
			subset(k+1, sel);
		}
		
		sel[k] = false;
		subset(k+1, sel);
	}

	private static void calculate(boolean[] sel) {
		// sel에는 어디에 괄호를 쳐야하는지에 대한 정보가 있음
		for (int i = 0; i < operator.size(); i++) {
			if (sel[i]) {
				// 괄호 있으면
				numStack.push(operand.get(i));
				opStack.push(operator.get(i));
				continue;
			} 
			else {
				numStack.push(operand.get(i));
			}
			
			while (numStack.size() >= 2) {
				// calculate
				int a = numStack.pop();
				int b = numStack.pop();
				char op = opStack.pop();
				numStack.push(compute(b, a, op));
//				System.out.println("peek : " + numStack.peek());
			}
			
			if (!sel[i])
				opStack.push(operator.get(i));
		}
		
		numStack.push(operand.get(operand.size()-1));
		while (numStack.size() >= 2) {
			int a = numStack.pop();
			int b = numStack.pop();
			char op = opStack.pop();
			numStack.push(compute(b, a, op));
//			System.out.println("peek : " + numStack.peek());
		}
		
		int result = numStack.pop();
//		System.out.println("result : " + result);
		answer = Math.max(answer, result);
	}
	
	private static int compute(int a, int b, char op) {
		int result = 0;
		switch(op) {
			case '+': result = a + b; break;
			case '-': result = a - b; break;
			case '*': result = a * b; break;
		}
		return result;
	}
}