import java.util.*;

class Solution {
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
        for (int i = r1; i < r2; i++) {
           min = Math.min(min, matrix[i+1][c1]);
           swap(i, c1, i+1, c1, matrix);
        }
        for (int j = c1; j < c2; j++) {
            min = Math.min(min, matrix[r2][j+1]);
            swap(r2, j, r2, j+1, matrix);
        }
        for (int i = r2; i > r1; i--) {
            min = Math.min(min, matrix[i-1][c2]);
           swap(i, c2, i-1, c2, matrix);
        }
        for (int j = c2; j > c1 + 1; j--) { // 마지막 1개는 스왑안함
            min = Math.min(min, matrix[r1][j-1]);
            swap(r1, j, r1, j-1, matrix);
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