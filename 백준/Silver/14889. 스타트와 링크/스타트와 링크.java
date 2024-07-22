import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int [][] grid;
    static int N, sum = 0;
    static int result = Integer.MAX_VALUE;
    static boolean [] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        v = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                sum += grid[i][j];
            }
        }

        makeTeam(0, 0);
        System.out.println(result);
    }

    static void makeTeam(int idx, int cnt) {
        if (cnt == N/2) {
            int ta = 0, tb = 0;
            for (int i = 0; i < v.length; i++) {
                for (int j = i+1; j < v.length; j++) {
                	if (v[i] && v[j]) {
                		ta += grid[i][j] + grid[j][i];
                	}
                	else if (!v[i] && !v[j]) {
                		tb += grid[i][j] + grid[j][i];
                	}
                }
            }
            result = Math.min(result, Math.abs(ta - tb));
            return;
        }
        
        if (idx >= N) return;

        v[idx] = true;
        makeTeam(idx+1, cnt+1);
        
        v[idx] = false;
        makeTeam(idx+1, cnt);
    }

}