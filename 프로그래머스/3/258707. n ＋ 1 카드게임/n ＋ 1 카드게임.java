import java.util.*;
import java.util.Map.*;

class Solution {
    public int solution(int coin, int[] cards) {
        final int TARGET = cards.length+1;
        // n까지를 맵에저장한후, n/3까지는 true로 설정
        Map<Integer, Boolean> map = new HashMap<>();
        // init list
        List<Integer> initList = new ArrayList<>();
        for (int i = 1; i <= cards.length; i++) 
            map.put(i, false);
        
        for (int i = 0; i < cards.length/3; i++) {
            map.put(cards[i], true);
            initList.add(cards[i]);
        }
        
        // 읽으면서 첫번째와 기존을 비교하고 맞으면 2개 없애고
        int idx = cards.length/3;
        List<Integer> tempList = new ArrayList<>();
        int first = 0, second = 0, match = 0;
        int stage = 1;
        
        O:while (coin >= 0 && idx < cards.length) {
            tempList.add(cards[idx++]);
            tempList.add(cards[idx++]);
            for (int i = 0; i < initList.size(); i++) {
                int num = initList.get(i);
                if (map.get(TARGET - num)) {
                    map.put(TARGET - num, false);
                    map.put(num, false);
                    int cmpIdx = initList.indexOf(TARGET - num);
                    int bIdx = Math.max(i, cmpIdx);
                    int sIdx = Math.min(i, cmpIdx);
                    initList.remove(bIdx);
                    initList.remove(sIdx);
                    stage++;
                    continue O;
                }
            }
            
            if (coin == 0) break;
            // 여기부턴 행위에 코인이 소모됌
            // tempList.add(cards[idx++]);
            // tempList.add(cards[idx++]);
            
            // 새로 뽑은 2장에서 결과가 없으면 지금까지 가져온거에서 찾아봐야함
            // 임시리스트 1개랑 저장소 1개에서 찾으면 coin 1개
            for (int i = 0; i < tempList.size(); i++) {
                int tmp = tempList.get(i);
                if (map.get(TARGET - tmp)) {
                    map.put(TARGET - tmp, false);
                    coin--;
                    stage++;
                    tempList.remove(i);
                    continue O;
                }
            }
            
            // 임시리스트에서만 찾으면 코인 2개
            if (coin < 2) break O;
            for (int i = 0; i < tempList.size(); i++) {
                int tmp = tempList.get(i);
                for (int j = i+1; j < tempList.size(); j++) {
                    int tmp2 = tempList.get(j);
                    if (tmp + tmp2 == TARGET) {
                        tempList.remove(j);
                        tempList.remove(i);
                        coin -= 2;
                        stage++;
                        continue O;
                    }
                }
            }
            break;
        }
        return stage;
    }
}