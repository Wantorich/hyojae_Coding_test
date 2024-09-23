import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int size = board[0].length;
        Stack<Integer>[] stacks = new Stack[size];
        for (int i = 0; i < size; i++) stacks[i] = new Stack<Integer>();
        
        for (int i = board.length-1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != 0) stacks[j].push(board[i][j]);
            }
        }
        
        Stack<Integer> bracket = new Stack<Integer>();
        
        for (int i = 0; i < moves.length; i++) {
            int index = moves[i] - 1;
            if (!stacks[index].isEmpty()) {
                // 같은지 확인
                if (!bracket.isEmpty() && bracket.peek() == stacks[index].peek()) {
                    stacks[index].pop();
                    bracket.pop();
                    answer += 2;
                } else {
                    bracket.push(stacks[index].pop());
                }
            }
        }
        
        return answer;
    }
}