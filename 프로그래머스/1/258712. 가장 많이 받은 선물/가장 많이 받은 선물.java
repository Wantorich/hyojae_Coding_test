import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> give = new HashMap<>();
        Map<String, Integer> get = new HashMap<>();
        
        for (String friend : friends) {
            give.put(friend, new HashMap<>());
            get.put(friend, 0);
        }
        
        for (String gift : gifts) {
            String from = gift.split(" ")[0];
            String to = gift.split(" ")[1];
            
            // from -> to
            Map<String, Integer> giveMap = give.get(from);
            giveMap.put(to, giveMap.getOrDefault(to, 0) + 1);
            
            // 받은 선물 개수 늘리기
            get.put(to, get.get(to) + 1);
        }
        
        int[] giftCnt = new int[friends.length];
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = i+1; j < friends.length; j++) {
                String from = friends[i];
                String to = friends[j];
                
                // 누가 누구한테 줘야할지 판단
                Map<String, Integer> giveMap = give.get(from);
                int fromto = giveMap.getOrDefault(to, 0);
                Map<String, Integer> giveMap2 = give.get(to);
                int tofrom = giveMap2.getOrDefault(from, 0);
                
                if (fromto < tofrom) giftCnt[j]++;
                else if (fromto > tofrom) giftCnt[i]++;
                else {
                    // 선물 지수 비교하기
                    int aIndex = 0;
                    for (String key : giveMap.keySet()) aIndex += giveMap.getOrDefault(key, 0);
                    aIndex -= get.get(from);
                    
                    int bIndex = 0;
                    for (String key : giveMap2.keySet()) bIndex += giveMap2.getOrDefault(key, 0);
                    bIndex -= get.get(to);
                    
                    if (aIndex < bIndex) giftCnt[j]++;
                    else if (aIndex > bIndex) giftCnt[i]++;
                }
            }
        }
        
        return Arrays.stream(giftCnt).max().getAsInt();
    }
    
    
}