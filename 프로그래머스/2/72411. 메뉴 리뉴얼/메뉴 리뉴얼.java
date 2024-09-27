import java.util.*;
import java.util.Map.*;

class Solution {
    static HashMap<String, Integer>[] hmaps = new HashMap[11];
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        for (int i = 0; i < hmaps.length; i++)
            hmaps[i] = new HashMap<String, Integer>();
        
        for (String order : orders) {
            char[] sortedChars = order.toCharArray();
            Arrays.sort(sortedChars);
            String sortedorder = new String(sortedChars);
            powerSet(0, new boolean[order.length()], sortedorder.toCharArray());
        }
        
        // 문자길이 map에서 카운트가 가장 큰거를 가져와서 결과 리스트에 넣어줌
        List<String> resultList = new ArrayList();
        for (int i = 1; i < hmaps.length; i++) {
            boolean exist = false;
            for (int idx : course) {
                if (idx == i) exist = true;
            }
            
            if (!exist) continue;
            
            HashMap<String, Integer> hmap = hmaps[i];
            int maxCnt = 0;
            List<String> tmpList = new ArrayList();
            for (Entry<String, Integer> entry : hmap.entrySet()) {
                if (entry.getValue() > maxCnt) {
                    tmpList.clear();
                    maxCnt = entry.getValue();
                    if (maxCnt >= 2)
                        tmpList.add(entry.getKey());
                } else if (entry.getValue() == maxCnt) {
                    if (maxCnt >= 2)
                        tmpList.add(entry.getKey());
                }
            }
            resultList.addAll(tmpList);
        }
        Collections.sort(resultList);
        
        return resultList.toArray(new String[0]);
    }
    
    static void powerSet(int k, boolean[] sel, char[] order) {
        if (k == sel.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sel.length; i++) {
                if (!sel[i]) continue;
                sb.append(order[i]);
            }            
            int mapIdx = sb.length();
            String key = sb.toString();
            if (hmaps[mapIdx].get(key) == null)
                hmaps[mapIdx].put(key, 1);
            else hmaps[mapIdx].compute(key, (prev, v) -> v+1);
            return;
        }
        
        sel[k] = true;
        powerSet(k+1, sel, order);
        sel[k] = false;
        powerSet(k+1, sel, order);
    }
}