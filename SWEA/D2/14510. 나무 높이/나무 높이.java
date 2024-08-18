import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			N = Integer.parseInt(br.readLine());
			int[] trees = new int[N];
			// pq를 오름차순으로 정렬
			pq = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o1, o2));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max_h = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				int h = Integer.parseInt(st.nextToken());
				max_h = Math.max(max_h, h);
				trees[i] = h;
			}

			// 물을 줘야하는 높이로 배열을 바꾸고 pq에 집어넣음
			for (int i = 0; i < N; i++) {
				trees[i] = max_h - trees[i];
				if (trees[i] != 0) {
					pq.offer(trees[i]);
				}
			}

			int result = water();
			System.out.printf("#%d %d\n", t, result);
		}
	}

	private static int water() {
		Queue<Integer> q = new LinkedList<Integer>();
		int time = 1; // 1 or 2
		while (!pq.isEmpty()) {
			int curr;
			// time이 1인 경우와 2인 경우
			
			int currTime = time % 2;
			while (!pq.isEmpty() && pq.peek() == currTime+1) q.offer(pq.poll());
			
			if (!pq.isEmpty()) {
				curr = pq.poll();
				
				// curr이 1인 경우는 다시 안넣고, 그 외에 경우는 다시 넣음
				if (curr - (2 - currTime) > 0) {
					pq.offer(curr - (2 - currTime)); // 물 준거임
				}
			}
			
			time++;
			while (!q.isEmpty())
				pq.offer(q.poll()); // 임시 큐에 넣은걸 pq에 넣어줌
		}
		return time - 1;
	}
}