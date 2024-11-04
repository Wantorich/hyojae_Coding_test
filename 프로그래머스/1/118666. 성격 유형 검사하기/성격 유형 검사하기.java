import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        for (char type : types) map.put(type, 0);
        
        int result = 0, point = 0;
        for (int i = 0; i < choices.length; i++) {
            result = choices[i] - 4;
            point = Math.abs(result);
            if (result < 0) map.put(survey[i].charAt(0), map.get(survey[i].charAt(0)) + point);
            else map.put(survey[i].charAt(1), map.get(survey[i].charAt(1)) + point);
        }
        
        String answer = "";
        for (int i = 0; i < types.length; i += 2) {
            if (map.get(types[i]) >= map.get(types[i+1]))
                answer += types[i];
            else if (map.get(types[i]) < map.get(types[i+1]))
                answer += types[i+1];
        }
        
        return answer;
    }
}