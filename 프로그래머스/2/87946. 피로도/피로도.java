import java.util.*;

class Solution {
    static int N, result, K, map[][];
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        K = k;
        map = dungeons;
        permutation(0, new int[N], new boolean[N]);
        
        return result;
    }
    
    static int explore(int sel[]) {
        int point = K;
        int cnt = 0;
        for (int index : sel) {
            int required = map[index][0];
            int used = map[index][1];
            if (point >= required) {
                point -= used;
                cnt++;
            }
        }
        return cnt;
    }
    
    static void permutation(int k, int [] sel, boolean [] v) {
        if (k == N) {
            result = Math.max(result, explore(sel));
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            v[i] = true;
            sel[k] = i;
            permutation(k+1, sel, v);
            v[i] = false;
        }
    }
}