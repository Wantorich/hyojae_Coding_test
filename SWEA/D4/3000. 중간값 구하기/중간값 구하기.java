import java.util.*;

public class Solution {
	static int N, A;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			A = sc.nextInt();
			
			PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
			maxHeap.offer(A);
			int result = 0;
			
			for (int i = 0; i < N; i++) {
//				int a = sc.nextInt() , b = sc.nextInt();
				minHeap.offer(sc.nextInt());
				
				// 유효성 검증
				if (maxHeap.peek() > minHeap.peek()) {
					int max = maxHeap.poll();
					int min = minHeap.poll();
					maxHeap.offer(min);
					minHeap.offer(max);
				}
				
				maxHeap.offer(sc.nextInt());
				
				// 유효성 검증
				if (maxHeap.peek() > minHeap.peek()) {
					int max = maxHeap.poll();
					int min = minHeap.poll();
					maxHeap.offer(min);
					minHeap.offer(max);
				}
				
				result = (result + maxHeap.peek()) % 20171109; 
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}
}
