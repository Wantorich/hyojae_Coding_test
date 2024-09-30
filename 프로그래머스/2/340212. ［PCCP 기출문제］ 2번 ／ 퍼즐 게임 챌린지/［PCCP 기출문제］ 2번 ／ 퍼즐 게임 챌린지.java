class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        long left = 1, right = 100000, mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (solvePuzzle(mid, diffs, times, limit))
                right = mid;
            else 
                left = mid + 1;
        }
        return (int) right;
    }
    
    static boolean solvePuzzle(long level, int[] diffs, int[] times, long limit) {
        long result = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (level >= diffs[i]) 
                result += times[i];
            else 
                result += (diffs[i] - level) * (times[i] + times[i-1]) + times[i];
            if (result > limit) return false;
        }
        return true;
    }
}