import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int F, S, G, U, D, mov;
	static long result = Long.MAX_VALUE;
	static boolean[] v;
	static int [] dis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		v = new boolean[F+1];
		dis = new int[F+1];
		
		bfs();
		
		if (result == Long.MAX_VALUE) {
			System.out.println("use the stairs");
		}
		else {
			System.out.println(result);
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(S);
		int n;
		while (!q.isEmpty()) {
			n = q.poll();
			
			if (n < 1 || n > F || v[n]) continue;
			
			v[n] = true;
			if (n == G) {
				result = dis[n];
				return;
			}
			
			if (n+U <= F) {
				if (!v[n+U]) {
					q.add(n+U);
					dis[n+U] = dis[n]+1;
				}
			}
			
			if (n-D >= 1) {
				if (!v[n-D]) {
					q.add(n-D);
					dis[n-D] = dis[n]+1;
				}
			}
		}
	}
}

