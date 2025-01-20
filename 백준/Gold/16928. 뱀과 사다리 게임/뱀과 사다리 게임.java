import java.util.*;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	boolean[] visit = new boolean[101];
    	
    	Map<Integer, Integer> portal = new HashMap<>();
    	for (int i = 0; i < N+M; i++) {
    		int in = sc.nextInt();
    		int out = sc.nextInt();
    		portal.put(in, out);
    	}
    	
    	Deque<int[]> queue = new ArrayDeque<>();
    	queue.offer(new int[] {1, 0});
    	
    	while (!queue.isEmpty()) {
			int[] info = queue.poll();
			int curr = info[0];
			int attemp = info[1];
			
			if (curr == 100) {
				System.out.println(attemp);
				return;
			}
			
			if (portal.containsKey(curr)) {
				visit[curr] = true;
				queue.offerFirst(new int[] {portal.get(curr), attemp});
				continue;
			}
			
			visit[curr] = true;
			
			for (int i = 1; i <= 6; i++) {
				int next = curr + i;
				if (next <= 100 && !visit[next])
					queue.offer(new int[] {next, attemp+1});
			}
    	}
    }
}
