import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        List<String> wordList = Arrays.stream(words).collect(Collectors.toList());
        
        Map<String, Boolean> wordMap = new HashMap<>();
        wordList.forEach(word -> wordMap.put(word, false));
        
        dfs(0, begin, wordMap, target);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    static void dfs(int cnt, String word, Map<String, Boolean> wordMap, String target) {
        if (word.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for (Entry<String, Boolean> entry : wordMap.entrySet()) {
            if (entry.getValue() || !Strcmp.isMatched(word, entry.getKey())) 
                continue;
            entry.setValue(true);
            dfs(cnt + 1, entry.getKey(), wordMap, target);
            entry.setValue(false); // backtracking
        } 
    }
    
    // 문자열 일치 확인 함수, 1개만 다른지
    static class Strcmp {
        static boolean isMatched(String src, String dest) {
            int diffCnt = 0;
            for (int i = 0; i < src.length(); i++) {
                if (src.charAt(i) != dest.charAt(i)) {
                    diffCnt++;
                }
            }
            return diffCnt == 1 ? true : false;
        }
    }
}