import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        // 이분탐색으로 숙련도를 정하고
        int left = 1;
        int right = Arrays.stream(diffs).max().getAsInt();
        
        while (left <= right) {
            int mid = (left + right) / 2;
            // 각 숙련도마다 되는지 안되는지 판단하는 로직    
            long time = 0;
            int level = mid;
            
            for (int i = 0; i < diffs.length; i++) {
                if (diffs[i] <= level) {
                    time += times[i];
                } else {
                    int mul = diffs[i] - level;
                    time += mul * (times[i] + times[i-1]) + times[i];
                }
                
                if (time > limit) {
                    break;
                }
            }
            
            if (time <= limit) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            
        }
        return answer;
    }
}