import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] score = {6, 6, 5, 4, 3, 2, 1};
        Set<Integer> winSet = Arrays.stream(win_nums).boxed().collect(Collectors.toSet());
        int cnt = 0, zeroCnt = 0;
        for (int lotto : lottos) {
            if (winSet.contains(lotto)) {
                winSet.remove(lotto);
                cnt++;
            } else {
                if (lotto == 0) 
                    zeroCnt++;
            }
        }
        int min = cnt;
        int max = min + zeroCnt;
        answer[0] = score[max];
        answer[1] = score[min];
        return answer;
    }
}