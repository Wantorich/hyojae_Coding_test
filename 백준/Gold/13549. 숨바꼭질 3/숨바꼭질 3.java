import java.util.*;

public class Main {
    static int N, K, answer;
    static int MAX_NUM = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        boolean[] visit = new boolean[MAX_NUM + 1];

        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {N, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int position = current[0];
            int time = current[1];

            if (position == K) {
                System.out.println(time);
                return;
            }

            if (position * 2 <= MAX_NUM && !visit[position * 2]) {
                visit[position * 2] = true;
                q.addFirst(new int[] {position * 2, time});
            }
            if (position - 1 >= 0 && !visit[position - 1]) {
                visit[position - 1] = true;
                q.offer(new int[] {position - 1, time + 1});
            }
            if (position + 1 <= MAX_NUM && !visit[position + 1]) {
                visit[position + 1] = true;
                q.offer(new int[] {position + 1, time + 1});
            }
        }
    }
}
