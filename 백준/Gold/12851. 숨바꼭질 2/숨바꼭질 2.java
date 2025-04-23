import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	final int MAX_VALUE = 100000; 
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    	if (N == K) {
    		System.out.printf("%d\n%d", 0, 1);
    		return;
    	}
    	/*
    	 * bfs를 돌리는데 카운팅 배열도 같이 넣는다
    	 * 1에서 시작할때 +1 하는 경우랑 *2하는 경우랑 똑같구나!!!
    	 */
    	int[] dis = new int[MAX_VALUE+1];
    	ArrayDeque<Integer> queue = new ArrayDeque<>();
    	queue.offer(N);
    	int count = 0;
    	
    	while (!queue.isEmpty()) {
    		int curr = queue.poll();
    		if (curr == K) count++;
    		if (curr + 1 <= MAX_VALUE && (dis[curr + 1] == 0 || dis[curr + 1] > dis[curr])) {
    			dis[curr+1] = dis[curr] + 1;
    			queue.offer(curr+1);
    		}
    		if (curr - 1 >= 0 && (dis[curr - 1] == 0 || dis[curr - 1] > dis[curr])) {
    			dis[curr-1] = dis[curr] + 1;
    			queue.offer(curr-1);
    		}
    		if (curr * 2 <= MAX_VALUE && (dis[curr*2] == 0 || dis[curr*2] > dis[curr])) {
    			dis[curr*2] = dis[curr] + 1;
    			queue.offer(curr*2);
    		}
    	}
    	System.out.println(dis[K]);
    	System.out.println(count);
    	sc.close();
    }
}