import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        long sum1 = 0, sum2 = 0;
        
        for (int num : queue1) {
            deque1.add(num);
            sum1 += num;
        }
        for (int num : queue2) {
            deque2.add(num);
            sum2 += num;
        }   
        
        int cnt = 0, poll = 0;
        while (sum1 != sum2 && deque1.size() > 0 && deque2.size() > 0) {
            if (cnt > Math.max(deque1.size(), deque2.size()) * 2) return -1;
            
            if (sum1 < sum2) {
                poll = deque2.pollFirst();
                sum2 -= poll;
                deque1.offerLast(poll);
                sum1 += poll;
            } else {
                poll = deque1.pollFirst();
                sum1 -= poll;
                deque2.offerLast(poll);
                sum2 += poll;
            }
            cnt++;
        }        
        
        if (sum1 != sum2) return -1;
        return cnt;
    }
}