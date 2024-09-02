import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> truckQ = new LinkedList<Integer>();
		Queue<int[]> roadQ = new ArrayDeque<int[]>();

		int N = sc.nextInt();
		int W = sc.nextInt();
		int L = sc.nextInt();
		
		for (int i = 0; i < N; i++) truckQ.offer(sc.nextInt());
		
		int time = 0;
		while (!truckQ.isEmpty() || !roadQ.isEmpty()) {
			if (roadQ.isEmpty()) {
				roadQ.offer(new int[] {truckQ.poll(), 1});
			}
			else {
				// road에 1개 이상 있음
				// road truck 먼저 이동
				int qSize = roadQ.size();
				int roadWeight = 0;
				for (int i = 0; i < qSize; i++) {
					int [] info = roadQ.poll();
					if (info[1] + 1 <= W) {
						roadQ.offer(new int[] {info[0], info[1]+1});
						roadWeight += info[0];
					}
				}
				
				if (!truckQ.isEmpty()) {
					// 다리에 더 올릴수 있는지 확인
					if (roadQ.isEmpty() || roadWeight + truckQ.peek() <= L) {
						// 올릴수 있음
						roadQ.offer(new int[] {truckQ.poll(), 1});
					}
				}
				
			}
			time++;
		}
		
		System.out.println(time);
		sc.close();
	}
}
