import java.util.*;

public class Main {
	static int N, K, answer = Integer.MAX_VALUE, time[];
	static int INF = Integer.MAX_VALUE;
	static int MAX_NUM = 100000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); K = sc.nextInt();
		boolean[] visit = new boolean[K+1];
		
		if (N >= K) {
			answer = N - K;
			System.out.println(answer);
			return;
		}
		
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> temp = new LinkedList<>();
		q.offer(new int[] {N, 0});
		
		O:while (!q.isEmpty()) {
			// 뽑아서 전부 2배 업 시킴
			while (!q.isEmpty()) {
				int[] info = q.poll();
				int num = info[0];
				while (num <= K && !visit[num]) {
					visit[num] = true;
					temp.offer(new int[] {num, info[1]});
					if (num == K) {
						answer = info[1];
						break O;
					}
					num *= 2;
				}
				
				if (num > K) {
					answer = Math.min(answer, info[1] + num - K);
//					System.out.println("answer : " + answer);
				}
			}
			
			while (!temp.isEmpty()) {
				int[] info = temp.poll();
				int num = info[0];
				
				int left = num-1;
				int right = num+1;
				
				if (left == K || right == K) {
					answer = Math.min(answer, info[1] + 1);
					break O;
				}
				
				if (left >= 0 && !visit[left]) {
//					visit[left] = true;
					q.offer(new int[] {left, info[1]+1});
				}
				
				if (right <= K && !visit[right]) {
//					visit[right] = true;
					q.offer(new int[] {right, info[1]+1});
				}
			}
		}
		
		System.out.println(answer);
//		System.out.println(Arrays.toString(visit));
		sc.close();
	}

}
