import java.util.*;

class Solution {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        int[][] matrix = new int[rows+2][columns+2];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = num++;
            }
        }
        
        for (int[] query : queries) {
            int min = rotate(query[0], query[1], query[2], query[3], matrix);
            // for (int[] row : matrix) System.out.println(Arrays.toString(row));
            // System.out.println("===============");
            answer.add(min);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static int rotate(int r1, int c1, int r2, int c2, int[][] matrix) {
        int start = matrix[r1][c1];
        int min = start;
        int sr = r1, sc = c1;
        int rowLen = r2 - r1;
        int colLen = c2 - c1;
        List<Integer> lens = Arrays.asList(rowLen, colLen, rowLen, colLen - 1);
        
        for (int i = 0; i < dr.length; i++) {
            for (int j = 0; j < lens.get(i); j++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                min = Math.min(min, matrix[nr][nc]);
                swap(sr, sc, nr, nc, matrix);
                sr = nr;
                sc = nc;
            }
        }
        matrix[r1][c1+1] = start;
        return min;
    } 
    
    static void swap(int r1, int c1, int r2, int c2, int[][] matrix) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}