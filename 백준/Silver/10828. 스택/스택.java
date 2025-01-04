import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		int N = sc.nextInt();
		sc.nextLine();
		
		String output;
		for (int i = 0; i < N; i++) {
			String[] command = sc.nextLine().split(" ");
			if (command.length == 2) {
				deque.push(Integer.parseInt(command[1]));
				continue;
			}
			
			switch (command[0]) {
			case "top": 
				output = deque.isEmpty() ? "-1" : deque.peek() + "";
				sb.append(output + "\n");
				break;
			case "size":
				sb.append(deque.size() + "\n");
				break;
			case "empty":
				output = deque.isEmpty() ? "1" : "0";
				sb.append(output + "\n");
				break;
			case "pop" :
				output = deque.isEmpty() ? "-1" : deque.pop() + "";
				sb.append(output + "\n");
				break;
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}
