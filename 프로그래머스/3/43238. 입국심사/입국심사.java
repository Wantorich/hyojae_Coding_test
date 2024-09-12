class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        
        long left = 1, right = (long) Math.pow(1000000000,2), mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            long cnt = 0;
            for (int time : times) cnt += mid / time;
            if (cnt >= n) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            }
            else left = mid + 1;
        }
        
        return answer;
    }
}