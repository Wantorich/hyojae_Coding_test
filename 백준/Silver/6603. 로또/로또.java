import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K, S[]; 
		
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			K = Integer.parseInt(st.nextToken());
			if (K == 0) break;
			S = new int[K];
			for (int i = 0; i < K; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			// index, visit, cnt, array
			backtracking(0, 0, new int[6], S);
			System.out.println();
		}
	}

	private static void backtracking(int start, int cnt, int[] selected, int[] S) {
    if (cnt == 6) {
        print(selected);
        return;
    }

    for (int i = start; i < S.length; i++) {
        selected[cnt] = S[i];
        backtracking(i + 1, cnt + 1, selected, S);
    }
	}
	
	private static void print(int[] selected) {
	    StringBuilder sb = new StringBuilder();
	    for (int num : selected) {
	        sb.append(num).append(" ");
	    }
	    System.out.println(sb.toString().trim());
	}

}

/*
 * 기본적으로 조합임, 오름차순으로 정렬되어있음
 * k > 6인데 이중에 6개를 뽑는거니까 kC6 
 * 
 * 
 * 
 */