import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int end = attacks[attacks.length-1][0];
        int idx = 0;
        int MAX_HEALTH = health;
        int healCnt = 0;
        for (int i = 1; i <= end; i++) {
            if (attacks[idx][0] == i) {
                health -= attacks[idx++][1];
                if (health <= 0) return -1;
                healCnt = 0;
                continue;
            }
            
            health = Math.min(MAX_HEALTH, health + bandage[1]);
            healCnt++;
            if (healCnt == bandage[0]) {
                health = Math.min(MAX_HEALTH, health + bandage[2]);
                healCnt = 0;
            }
        }
        return health;
    }
}