import java.util.*;

class Solution {
    static int userInfo[][], result[];
    
    public int[] solution(int[][] users, int[] emoticons) {
        result = new int[2]; // 결과값 배열
        userInfo = new int[users.length][users[0].length];
        for (int i = 0; i < users.length; i++) {
            userInfo[i] = users[i].clone();
        }
       
        repeat(0, new int[emoticons.length], emoticons);
        return result;
    }
    
    static void repeat(int k, int [] sel, int[] emoticons) {
        if (k == sel.length) {
            int [] prices = new int[emoticons.length];
            for (int i = 0; i < prices.length; i++) {
                prices[i] = emoticons[i] - ((int) (emoticons[i] * sel[i] * 0.01));
            }            
            purchase(prices, sel);
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            sel[k] = 10 * i;
            repeat(k+1, sel, emoticons);
        }
    }
    
    static void purchase(int[] prices, int[] sales) {
        int sum = 0, ecnt = 0, etotal = 0;
        for (int k = 0; k < userInfo.length; k++) {
            // 남은 cnt를 다 더해도 현재 최대값보다 못넘으면 더 할 필요가 없음
            if (userInfo.length - k + ecnt < result[0]) return;
            
            int [] user = userInfo[k];
            sum = 0;
            for (int i = 0; i < prices.length; i++) {
                if (sales[i] >= user[0]) sum += prices[i];
            }
            
            if (sum >= user[1]) ecnt++;
            else etotal += sum;
        }
        
        // update
        if (result[0] < ecnt) {
            result[0] = ecnt;
            result[1] = etotal;
        } else if (result[0] == ecnt) {
            result[1] = Math.max(result[1], etotal);
        }
    }
}