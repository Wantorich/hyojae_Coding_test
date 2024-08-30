import java.util.*;

class Solution {
    static List<Integer> pickaxs;
    static int mineCnt, result = Integer.MAX_VALUE;
    static String[] mineralArr;
    public int solution(int[] picks, String[] minerals) {
        pickaxs = new ArrayList();
        mineCnt = minerals.length;
        // mineralArr = new String[minerals.length];
        mineralArr = minerals;
        
        // 곡괭이 저장
        for (int i = 0; i < picks.length; i++) {
            for (int j = 0; j < picks[i]; j++) {
                pickaxs.add(i);
            }
        }
        
        // 곡괭이 선택 순서 순열
        permutation(0, new int[pickaxs.size()], new boolean[pickaxs.size()]);
        
        return result;
    }
    
    static void permutation(int k, int [] sel, boolean[] visit) {
        if (k == sel.length) {
            // 광산 파기
            // System.out.println(Arrays.toString(sel));
            result = Math.min(result, mining(sel));
            return;
        }
        
        int prev = -1;
        for (int i = 0; i < pickaxs.size(); i++) {
            if (visit[i]) continue;
            if (pickaxs.get(i) == prev) continue;
            visit[i] = true;
            sel[k] = pickaxs.get(i);
            prev = sel[k];
            permutation(k+1, sel, visit);
            visit[i] = false;
        }
    }
    
    static int mining(int [] order) {
        int costSum = 0;
        int mineralIdx = 0;
        
        for (int i = 0; i < order.length; i++) {
            // 각 곡괭이로 광물 캠
            int pickax = order[i];
            for (int j = 0; j < 5; j++) {
                costSum += getCost(pickax, mineralArr[mineralIdx++]);
                // 광물 다 캤는지 확인
                if (mineralIdx == mineCnt) {
                    return costSum;
                }
            }
        }
        return costSum;
    }
    
    static int getCost(int pickaxNum, String mineral) {
        if (pickaxNum == 0) {// 다이아곡괭이
            return 1;
        } else if (pickaxNum == 1) { // 철 곡괭이
            if (mineral.equals("diamond")) return 5;
            else return 1;
        } else {// 돌 곡괭이
            if (mineral.equals("diamond")) return 25;
            else if (mineral.equals("iron")) return 5;
            else return 1;
        }
    }
}