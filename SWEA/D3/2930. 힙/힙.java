import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				switch (st.nextToken()) {
				case "1" :
					pq.offer(Integer.parseInt(st.nextToken()));
					break;
				case "2" :
					if (pq.isEmpty()) sb.append(-1).append(" ");
					else sb.append(pq.poll()).append(" ");
				}
			}
			System.out.println(sb.toString());
		}
	}
}