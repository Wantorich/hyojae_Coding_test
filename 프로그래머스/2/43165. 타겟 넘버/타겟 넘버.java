import java.util.*;

class Solution {
    static int answer;
    static int[] plusSum, minusSum;
    public int solution(int[] numbers, int target) {
        plusSum = new int[numbers.length];
        minusSum = new int[numbers.length];
        plusSum[0] = numbers[0];
        minusSum[0] = -numbers[0];
        
        for (int i = 1; i < numbers.length; i++) {
            plusSum[i] = plusSum[i-1] + numbers[i];
            minusSum[i] = minusSum[i-1] - numbers[i];
        }
        
        dfs(0, numbers, target, 0);
        return answer;
    }
    
    static void dfs(int k, int [] numbers, int target, int sum) {
        if (k == numbers.length) {
            if (sum == target) answer++;
            return;
        }
        
        if (k > 1 && sum + plusSum[numbers.length-1] - plusSum[k-1] < target) return;
        if (k > 1 && sum + minusSum[numbers.length-1] - minusSum[k-1] > target) return;
        
        dfs(k+1, numbers, target, sum + numbers[k]);
        dfs(k+1, numbers, target, sum - numbers[k]);
    }
}