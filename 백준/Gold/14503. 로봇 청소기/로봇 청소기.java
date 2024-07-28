import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] grid, mov = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int result = 0, nr, nc;
    static boolean [][] v;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        // 0 -> 북, 1 -> 동, 2 -> 남, 3 -> 서

        grid = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        dfs(sr, sc, dir);

        System.out.println(result);
    }

    static void dfs(int r, int c, int dir) {
        if (grid[r][c] == 1) return;
        
        if (!v[r][c]) {
        	v[r][c] = true;
        	result++;
        }

        for (int i = 0; i < mov.length; i++) {
        	nr = r + mov[i][0];
        	nc = c + mov[i][1];
        	
        	if (!v[nr][nc] && grid[nr][nc] == 0) {
        		dir = (dir + 3) % 4;
        		nr = r + mov[dir][0];
        		nc = c + mov[dir][1];
        		if (!v[nr][nc] && grid[nr][nc] == 0) {
        			dfs(nr, nc, dir);
        		}
        		else {
        			dfs(r, c, dir);
        		}
        		return;
        	}
        }
        
        nr = r - mov[dir][0];
        nc = c - mov[dir][1];
        if (grid[nr][nc] == 0) {
        	dfs(nr, nc, dir);
        }
    }
}