import java.util.*;

class Solution {
    static int N, result;
    static String[][] map;
    static List<String> keyList;
    public int solution(String[][] relation) {
        N = relation[0].length;
        map = relation;
        keyList = new LinkedList<String>();
        
        for (int i = 1; i <= relation[0].length; i++) {
            combination(0, 0, new int[i]);
        }
        
        // key들의 유효성 검증
        for (int i = 0; i < keyList.size(); i++) {
            String standard = keyList.get(i);
            result++;
            for (int j = i+1; j < keyList.size(); j++) {
                boolean include = true;
                for (int k = 0; k < standard.length(); k++) {
                    if (!keyList.get(j).contains("" + standard.charAt(k))) {
                        include = false;
                        break;
                    }
                }
                if (include) {
                    keyList.remove(j--);
                }
            }
        }
        
        return result;
    }
    
    static void judgeKey(int [] sel) {
        Map<String, Boolean> hmap = new HashMap();
        for (int i = 0; i < map.length; i++) {
            String key = "";
            for (int j = 0; j < sel.length; j++) {
                key += map[i][sel[j]];
            }
            hmap.put(key, true);
        }
        
        
        if (hmap.size() == map.length) {
            // for (String key : hmap.keySet()) System.out.print(key + " ");
            // System.out.println();
            // 중복이 없음
            String key = "";
            for (int index : sel) key += index;
            keyList.add(key);
            // result++;
        }
    }
    
    static void combination(int idx, int k, int [] sel) {
        if (k == sel.length) {
            // System.out.println(Arrays.toString(sel));
            judgeKey(sel);
            return;
        }
        
        if (idx == N) return;
        
        sel[k] = idx;
        combination(idx+1, k+1, sel);
        combination(idx+1, k, sel);
    }
}