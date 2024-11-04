import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int[][] dpTable = new int[600][600];
        for (int [] row : dpTable)
            Arrays.fill(row, Integer.MAX_VALUE);
        
        int alpMax = Arrays.stream(problems).mapToInt(arr -> arr[0]).max().getAsInt();
        int copMax = Arrays.stream(problems).mapToInt(arr -> arr[1]).max().getAsInt();
        // System.out.println(alpMax + ":" + copMax);
        
        dpTable[alp][cop] = 0;
        for (int i = alp; i <= 500; i++) {
            for (int j = cop; j <= 500; j++) {
                // 해결할 수 있는 problem 해결하기
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1]) continue;
                    int ni = i + problem[2];
                    int nj = j + problem[3];
                    dpTable[ni][nj] = Math.min(dpTable[ni][nj], dpTable[i][j] + problem[4]);
                }
                dpTable[i+1][j] = Math.min(dpTable[i+1][j], dpTable[i][j] + 1);
                dpTable[i][j+1] = Math.min(dpTable[i][j+1], dpTable[i][j] + 1);
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = alpMax; i <= 500; i++) {
            for (int j = copMax; j <= 500; j++) {
                answer = Math.min(answer, dpTable[i][j]);
            }
        }
        
        // System.out.println(dpTable[alpMax][copMax]);
        return answer;
    }
}