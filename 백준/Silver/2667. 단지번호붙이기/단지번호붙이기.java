import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int [][] grid;
    static boolean [][] isvisited;
    static int N, result = 2, cnt;
    static int[][] mov = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        grid = new int [N+1][N+1];
        isvisited = new boolean [N+1][N+1];
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String [] line = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = result;
                    ll.add(bfs(i,j));
                    result++; // result 값 할당으로 단지수 구별
                }
            }
        }

        System.out.println(result-2);
        while (!ll.isEmpty()) {
        	int min = ll.peek();
        	int min_idx = 0;
        	for (int i = 0; i < ll.size(); i++) {
        		if (ll.get(i) < min) {
        			min_idx = i;
        			min = ll.get(i);
        		}
        	}
        	System.out.println(ll.remove(min_idx));
        }
    }

    static int bfs(int a, int b) {
        Queue<int []> queue = new LinkedList<int []>();
        queue.offer(new int[] {a, b});
        isvisited[a][b] = true;
        int home_cnt = 1;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int r = tmp[0];
            int c = tmp[1];

            for (int i = 0; i < mov.length; i++) {
                int nr = r + mov[i][0];
                int nc = c + mov[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if (grid[nr][nc] == 1 && !isvisited[nr][nc]) {
                    queue.offer(new int[] {nr, nc});
                    grid[nr][nc] = result;
                    home_cnt++;
                }
            }
        }
        return home_cnt;
    }

}

