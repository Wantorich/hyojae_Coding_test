import java.util.*;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	int[] attemps = new int[101];
    	Arrays.fill(attemps, -1);
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
			
			if (portal.get(curr) != null) {
				attemps[portal.get(curr)] = attemp;
				queue.offerFirst(new int[] {portal.get(curr), attemp});
				continue;
			}
			
			attemps[curr] = attemp;
			if (curr == 100) {
				System.out.println(attemp);
				return;
			}
			
			for (int i = 1; i <= 6; i++) {
				int next = curr + i;
				if (next <= 100 && attemps[next] == -1)
					queue.offer(new int[] {next, attemp+1});
			}
    	}
    }
}
