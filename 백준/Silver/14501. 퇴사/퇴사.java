import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static int N, sum = 0;
	static int result = Integer.MIN_VALUE;
	static int[] m, t;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new int[N];
		t = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			m[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0);
		System.out.println(result);
	}
	
	static void dfs(int t_idx, int money) {
		if (t_idx >= N) {
			result = Math.max(result, money);
			return;
		}
		
		int nt = t_idx + t[t_idx];
		
		if (nt <= N) {
			// t_idx 시간에 일을 했을때
			dfs(nt, money+m[t_idx]);
			
			// t_idx 시간에 일을 안했을때
			dfs(t_idx + 1, money);
		}
		else {
			// 배열 범위 넘어갈때는 출력
			dfs(nt, money);
            
            dfs(t_idx+1, money);
		}
	}
	
	
}

