import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int cr, cc, hr, hc, N, result;
	static int [][] clients;
	static boolean [] v;

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int test_case = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	
    	for (int t = 1; t <= test_case; t++) {
    		N = Integer.parseInt(br.readLine());
    		st = new StringTokenizer(br.readLine());
    		cr = Integer.parseInt(st.nextToken());
    		cc = Integer.parseInt(st.nextToken());
    		hr = Integer.parseInt(st.nextToken());
    		hc = Integer.parseInt(st.nextToken());
    		clients = new int[N][2];
    		v = new boolean[N];
    		result = Integer.MAX_VALUE;
    		
    		for (int i = 0; i < N; i++) {
    			clients[i][0] = Integer.parseInt(st.nextToken());
    			clients[i][1] = Integer.parseInt(st.nextToken());
    		}
    		
    		permutation(new int[N], 0);
    		System.out.printf("#%d %d\n", t, result);
    	}
    }
    
    static void permutation(int [] sel, int idx) {
    	if (idx == sel.length) {
    		// result 값 계산
    		int dis_sum = 0;
    		int sr = cr;
    		int sc = cc;
    		for (int i = 0; i < sel.length; i++) {
				dis_sum += Math.abs(sr - clients[sel[i]][0]) + Math.abs(sc - clients[sel[i]][1]);
				sr = clients[sel[i]][0];
				sc = clients[sel[i]][1];
    		}
    		dis_sum += Math.abs(sr - hr) + Math.abs(sc - hc);
    		result = Math.min(result, dis_sum);
    		return;
    	}
    	
    	for (int i = 0; i < sel.length; i++) {
    		if (!v[i]) {
    			sel[idx] = i;
    			v[i] = true;
    			permutation(sel, idx+1);
    			v[i] = false;
    		}
    	}
    }

}
