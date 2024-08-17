import java.util.*;
import java.io.*;


public class Solution {
	static int N, K;
	static Collection<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			set.clear();
			
			sb = new StringBuilder(br.readLine());
			for (int k = 0; k < N / 4; k++) {
				for (int i = 0; i < N; i += N / 4) {
					String substr = sb.substring(i, i + N / 4);
					set.add(Integer.parseInt(substr, 16));
				}
				sb.insert(0, sb.charAt(N-1)).setLength(N);
			}
			
			List<Integer> list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder());
			
			System.out.printf("#%d %d\n", t, list.get(K-1));
		}
	}
}