import java.util.*;

class Solution {
    static int answer;
    public int solution(int n, int[][] q, int[] ans) {
        comb(new int[5], 1, 0, n, q, ans);
        return answer;
    }
    
    static boolean test(int[] sel, int[][] q, int[] ans) {
        for (int i = 0; i < ans.length; i++) {
            int[] question = q[i];
            int cnt = 0;
            for (int j = 0; j < question.length; j++) {
                if (Arrays.binarySearch(sel, question[j]) >= 0)
                    cnt++;
            }
            if (cnt != ans[i])
                return false;
        }
        
        return true;
    }
    
    static void comb(int[] sel, int idx, int k, int n, int[][] q, int[] ans) {
        if (k == sel.length) {
            // q와 ans로 일치하는지 검사
            if (test(sel, q, ans))
                answer++;
            return;
        }
        
        if (idx > n) {
            return;
        }
        
        sel[k] = idx;
        comb(sel, idx+1, k+1, n, q, ans);
        comb(sel, idx+1, k, n, q, ans);
    }
}

// 최대 30C5 의 경우를 일단 다 구함