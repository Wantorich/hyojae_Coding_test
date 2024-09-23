import java.util.*;

public class Main {
    static int N, S, M, musics[];
    static int result = -1;
    static boolean [][] visit;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        M = sc.nextInt();
        musics = new int[N+1];
        visit = new boolean[N+2][M+1];
        
        for (int i = 1; i <= N; i++) musics[i] = sc.nextInt();
        recur(1, S);
        System.out.println(result);
        sc.close();
    }
    
    private static void recur(int k, int vol) {
    	if (k == N+1) {
    		result = Math.max(result, vol);
    		return;
    	}
    	
    	if (vol + musics[k] <= M && !visit[k+1][vol + musics[k]]) {
    		visit[k+1][vol + musics[k]] = true;
    		recur(k+1, vol + musics[k]);
    	}
    	if (vol - musics[k] >= 0 && !visit[k+1][vol - musics[k]]) {
    		visit[k+1][vol - musics[k]] = true;
    		recur(k+1, vol - musics[k]);
    	}
    }
}