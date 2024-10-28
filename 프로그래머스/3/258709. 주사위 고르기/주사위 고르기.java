import java.util.*;
import java.util.Map.*;

class Solution {
    static int diceLen, myDice[][], MAX_WIN = 0;
    static TreeMap<Integer, Integer> mapA = new TreeMap<>((a, b) -> Integer.compare(a, b));
    static TreeMap<Integer, Integer> mapB = new TreeMap<>((a, b) -> Integer.compare(a, b));
    static int[] answerIdx;
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        myDice = new int[dice.length][6];
        for (int i = 0; i < dice.length; i++) 
            myDice[i] = dice[i].clone();
        
        diceLen = dice.length;
        answerIdx = new int[dice.length/2];
        comb(new int[diceLen/2], 0, 0);
        
        // for (int ans : answerIdx) System.out.print(ans + " ");
        System.out.println();
        
        return answerIdx;
    }
    
    static int calScore() {
        // MapA를 하나씩 꺼내면서 mapB를 비교해봄
        int win = 0;
        for (Entry<Integer, Integer> entry : mapA.entrySet()) {
            int sumA = entry.getKey();
            int cntA = entry.getValue();
            
            for (Entry<Integer, Integer> cmpEntry : mapB.headMap(sumA).entrySet()) {
                win += cntA * cmpEntry.getValue();            
            }
        }
        return win;
    }
    
    static void test(List<int[]> list, int idx, int sum, char team) {
        if (idx == list.size()) {
            // sum에 값이 담겨있음
            Map<Integer, Integer> map = team == 'A' ? mapA : mapB;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }
        
        int[] temp = list.get(idx);
        for (int j = 0; j < temp.length; j++) {
            test(list, idx+1, sum + temp[j], team);
        }
    }
    
    
    
    static void comb(int[] sel, int idx, int k) {
        if (k == sel.length) {
            System.out.println(Arrays.toString(sel));
            List<int[]> listA = new ArrayList<>();            
            List<int[]> listB = new ArrayList<>();            
            boolean[] diceSel = new boolean[diceLen];
            for (int i = 0; i < sel.length; i++) diceSel[sel[i]] = true;
            for (int i = 0; i < diceSel.length; i++) {
                if (diceSel[i]) listA.add(myDice[i]);
                else listB.add(myDice[i]);
            }
            
            // A테이블 구하기
            test(listA, 0, 0, 'A');
            // for (Integer key : mapA.keySet()) {
            //     System.out.println("key : " + key + ", value : " + mapA.get(key));
            // }
            
            // B테이블 구하기
            test(listB, 0, 0, 'B');
            
            // 테이블간 비교해서 승-무-패 따져야함
            int result = calScore();
            // System.out.println("result : " + result);
            if (result > MAX_WIN) {
                MAX_WIN = result;
                for (int t = 0; t < answerIdx.length; t++) 
                    answerIdx[t] = sel[t] + 1;
            }
            
            mapA.clear();
            mapB.clear();
            return;
        }
        
        if (idx == diceLen) return;
        
        sel[k] = idx;
        comb(sel, idx+1, k+1);
        comb(sel, idx+1, k);
    }
}