import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int F, S, G, U, D, mov;
	static long result = Long.MAX_VALUE;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		v = new boolean[F+1];
		
		dfs(S, 0);
		
		if (result == Long.MAX_VALUE) {
			System.out.println("use the stairs");
		}
		else {
			System.out.println(result);
		}
	}
	
	static void dfs(int curr, int cnt) {
		if (curr > F || curr < 1 || v[curr]) {
			// out of boundary
			return;
		}
		
		if (curr == G) {
			result = Math.min(result, cnt);
			return;
		}
		
		if (G > curr + U) {
			if (U != 0) {
				mov = (G - curr) / U;
				dfs(curr+mov*U, cnt+mov);
			}
		}
		
		if (G < curr - D) {
			if (D != 0) {
				mov = (curr - G) / D;
				dfs(curr-mov*D, cnt+mov);
			}
		}
		
		// 위층 이동
		v[curr] = true;
		dfs(curr+U, cnt+1);

		// 아래층 이동
		dfs(curr-D, cnt+1);
	}
	
}

