import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int r, c;
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
	static Point [] store;
    static int sr, sc;
    static int N, hr, hc, er, ec;
    static boolean [] visit;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        for (int t = 0; t < test_case; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            hr = Integer.parseInt(st.nextToken());
            hc = Integer.parseInt(st.nextToken());
            store = new Point[N+1];
            visit = new boolean[N+1];
            result = "sad";

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                sr = Integer.parseInt(st.nextToken());
                sc = Integer.parseInt(st.nextToken());
                store[i] = new Point(sr, sc);
            }

            st = new StringTokenizer(br.readLine());
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());
            store[store.length-1] = new Point(er, ec);
            
            bfs(hr, hc);
            System.out.println(result);
        }
        // 50미터에 한병, 한박스에 20병 -> 1000m (한 박스로)
        // 순열로 구하는데 각 점마다의 거리 차이가 1000m 이하여야 함

}


static void bfs(int r, int c) {
    Queue<Point> q = new ArrayDeque<Point>();
    q.offer(new Point(r, c));
    
    while (!q.isEmpty()) {
        Point p = q.poll();
        
        if (p.r == er && p.c == ec) {
        	// 목적지에 도착하면
        	result = "happy";
        	return;
        }
        
        for (int i = 0; i <= N; i++) {
        	if (!visit[i]) {
        		Point s = store[i];
        		if (Math.abs(p.r - s.r) + Math.abs(p.c - s.c) <= 1000) {
//        			System.out.println(s.r + ":" + s.c);
        			// 이동할 수 있으면
        			q.offer(s);
        			visit[i] = true;
        		}
        	}
        }
    }
}
}