import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static int [][] grid;
	static ArrayList<int []> home, chicken;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(tokens[j]);
				switch (grid[i][j]) {
					case 1 :
						home.add(new int[] {i,j});
						break;
					case 2 :
						chicken.add(new int[] {i, j});
				}
			}
		}
		
		for (int i = 1; i <= M; i++) {
			recursive(new int[i], 0, 0);
		}
		
		System.out.println(result);
	}
	
	static void recursive(int [] sel, int idx, int k) {
		if (k == sel.length) { // 다 뽑은 경우, sel에는 치킨집 인덱스 조합번호가 들어있음
			int cr, cc, hr, hc, dis_sum = 0, dis, min_dis;
			for (int [] h_pos : home) { // 집마다 최소치킨거리를 구함
				hr = h_pos[0];
				hc = h_pos[1];
				min_dis = Integer.MAX_VALUE;
				
				for (int i = 0; i < sel.length; i++) {
					cr = chicken.get(sel[i])[0];
					cc = chicken.get(sel[i])[1];
					
					dis = Math.abs(cr - hr) + Math.abs(cc - hc);
					min_dis = Math.min(min_dis, dis);
				} // min_dis는 최소 치킨 거리
				
				dis_sum += min_dis ;
			}
			result = Math.min(result, dis_sum);
			return;
		}
		
		if (idx == chicken.size()) return;
		
		sel[k] = idx;
		recursive(sel, idx+1, k+1); // 뽑을때
		recursive(sel, idx+1, k); // 안뽑을때
		// 조합은 sel 배열에 인덱스를 저장한다
	}
}

