import java.util.*;

public class Solution {
	static ArrayDeque<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			sc.nextInt();
			list = new ArrayDeque<Integer>();
			for (int i = 0; i < 8; i++) list.add(sc.nextInt());
			// 반복 수행
			int minus = 1;
			while (true) {
				int curr = list.pollFirst();
				int next = curr - minus;
				if (next <= 0) {
					list.addLast(0);
					break;
				}
				
				list.addLast(next);
				// 1 2 3 4 5 0
				minus = (minus + 1) % 6 == 0 ? 1 : (minus + 1) % 6;
			}
			
			System.out.print("#" + t + " ");
			for (int num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
		}

		sc.close();
	}
}
