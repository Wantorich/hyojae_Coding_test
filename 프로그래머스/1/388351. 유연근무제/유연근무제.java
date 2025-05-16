import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0; i < schedules.length; i++) {
            int curr = schedules[i];
            int min = (curr + 10) % 100;
            schedules[i] = min >= 60 ? (curr + 100) / 100 * 100 + (min % 60) : curr + 10;
            System.out.print(schedules[i] + " ");
        }
        
        // 5, 6은 빼야하고
        boolean[] success = new boolean[schedules.length];
        Arrays.fill(success, true);
        for (int j = 0; j < schedules.length; j++) {
            int[] timelog = timelogs[j]; 
            int currDay = startday - 1;
            for (int i = 0; i < 7; i++, currDay = (currDay + 1) % 7) {
               if (currDay == 5 || currDay == 6) continue;
                if (timelog[i] > schedules[j]) {
                    success[j] = false;
                    break;
                }
            }
        }
        
        for (boolean val : success) {
            if (val) answer++;
        }
        
        return answer;
    }
}

/*
출근 희망시간을 기준으로 + 10분까지 설정을하고
일주일동안, (토, 일) 제외하고 저 기준 시간보다 작은지 판단
*/