
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine().trim());
		Queue<Integer> queue = new LinkedList<>();
		
		for (int num = 1; num <= N; num++) {
			queue.add(num);
		}
		
		while (queue.size() > 1) {
			queue.poll();
			queue.add(queue.poll());
		}
		
		System.out.println(queue.poll());
		
		
		sc.close();
	}

}

/*

*/