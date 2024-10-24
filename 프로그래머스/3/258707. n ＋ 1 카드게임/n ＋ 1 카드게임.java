import java.util.*;
import java.util.Map.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        final int TARGET = cards.length+1;
        // n까지를 맵에저장한후, n/3까지는 true로 설정
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 1; i <= cards.length; i++) 
            map.put(i, false);
        
        for (int i = 0; i < cards.length/3; i++)
            map.put(cards[i], true);
        
        // 읽으면서 첫번째와 기존을 비교하고 맞으면 2개 없애고
        int idx = cards.length/3;
        List<Integer> tempList = new ArrayList<>();
        int first = 0, second = 0, match = 0;
        int stage = 1, stageSave = 0;
        O:while (coin >= 0 && idx < cards.length) {
            for (Entry<Integer, Boolean> entry : map.entrySet()) {
                // 먼저 기존의 저장소에서 찾으면 coin이 안드니까 여기서찾고
                if (entry.getValue() && map.get(TARGET - entry.getKey())) {
                    // 짝이 맞는게 있다면
                    // entry.setValue(false);
                    map.put(entry.getKey(), false);
                    map.put(TARGET - entry.getKey(), false);
                    stage++;
                    System.out.println("제출 : " + entry.getKey() + " - " + (TARGET - entry.getKey()));
                    tempList.add(cards[idx++]);
                    tempList.add(cards[idx++]);
                    continue O;
                }
            }
            
            if (coin == 0) break;
            
            first = cards[idx++];
            match = TARGET - first;
            if (map.get(match)) {
                // map에 존재하면
                map.put(match, false);
                coin--;
                tempList.add(cards[idx++]);
                stage++;
                System.out.println("제출 : " + first + " - " + match);
                continue;
            } else {
                tempList.add(cards[idx-1]);
            }
            
            second = cards[idx++];
            match = TARGET - second;
            if (map.get(match)) {
                // map에 존재하면
                map.put(match, false);
                coin--;
                stage++;
                System.out.println("제출 : " + second + " - " + match);
                continue;
            } else {
                tempList.add(cards[idx-1]);
            }
            
            // 새로 뽑은 2장에서 결과가 없으면 지금까지 가져온거에서 찾아봐야함
            // 임시리스트 1개랑 저장소 1개에서 찾으면 coin1개
            for (int i = 0; i < tempList.size(); i++) {
                int tmp = tempList.get(i);
                if (map.get(TARGET - tmp)) {
                    map.put(TARGET - tmp, false);
                    coin--;
                    stage++;
                    tempList.remove(i);
                    System.out.println("제출 : " + tmp + " - " + (TARGET - tmp));
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
                        System.out.println("제출 : " + tmp + " - " + tmp2);
                        continue O;
                    }
                }
            }
            break;
        }
        // System.out.println("Stage : " + stage);        
        return stage;
    }
}