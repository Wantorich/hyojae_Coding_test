import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String parsedStr = Integer.toString(n, k);
        
        StringTokenizer st = new StringTokenizer(parsedStr, "0", true);
        List<String> strList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (strList.size() > 0 && strList.get(strList.size()-1).equals(token)) continue;
            strList.add(token);
        }
        
        
        
        // for (String str : strList) System.out.println(str);
        
        for (int i = 0; i < strList.size()-2; i++) {
            long curr = Long.parseLong(strList.get(i));
            long next = Long.parseLong(strList.get(i+1));
            if (curr != 0 || curr == 0 && next == 0) continue;
            
            long futher = Long.parseLong(strList.get(i+2));
            if (futher == 0 && isPrimeNum(next)) 
                answer++;
        }
        
        // 처음과 마지막, P0과 0P찾음
        if (strList.size() > 1) {
            long start = Long.parseLong(strList.get(0));
            long startNext = Long.parseLong(strList.get(1));
            if (startNext == 0 && isPrimeNum(start)) answer++;
            
            long endPrev = Long.parseLong(strList.get(strList.size()-2));
            long end = Long.parseLong(strList.get(strList.size()-1));
            if (endPrev == 0 && isPrimeNum(end)) answer++;
        }
        
        if (strList.size() == 1 && isPrimeNum(Long.parseLong(parsedStr)))
            answer++;
        
        return answer;
    }
    
    static boolean isPrimeNum(long n) {
        if (n <= 1) return false;
        // boolean find = Arrays.stream(Long.toString(n).split("")).anyMatch(s -> s == "0");
        // if (find) return false;
        for (int i = 2; i < (int) (Math.sqrt(n)) + 1; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}