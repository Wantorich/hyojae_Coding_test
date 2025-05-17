import java.util.*;

class Solution {
    static final int ALPHA_SIZE = 26;
    static char[] alphabets;
    static List<Long> delIdxs = new ArrayList<>();
    
    public String solution(long n, String[] bans) {
        alphabets = new char[26];
        char c = 'a';
        for (int i = 0; i < alphabets.length; i++, c++) {
            alphabets[i] = c;
        }
        Arrays.sort(bans, Comparator.comparingInt(String::length)
                    .thenComparing(Comparator.naturalOrder()));
        // System.out.println(Arrays.toString(bans));
        
        for (String ban : bans) {
            long idx = calculateIdx(ban);
            long updated = idx - delIdxs.size();
            if (updated > n) continue;
            delIdxs.add(updated);
            // 삭제될때 인덱스 위치
            // System.out.printf("알파벳 %s , 인덱스 %d\n", ban, updated);
        }
        
        
        String find = findStr(n + delIdxs.size(), new StringBuilder()).toString();
        // System.out.println(find);
        
        
        return find;
    }
    
    static StringBuilder findStr(long idx, StringBuilder sb) {
        if (idx == 0)
            return sb;
        int remain = (int) ((idx - 1) % ALPHA_SIZE); // 0 ~ 25인데 0은 z임
        // remain이 0일때 인덱스가 -1이 됌
        sb.insert(0, alphabets[remain]);
        return findStr((idx - 1) / ALPHA_SIZE, sb);
    }
    
    static long calculateIdx(String str) {
        int len = str.length();
        long index = 0;
        for (int i = 1; i <= len; i++) {
            char c = str.charAt(i-1);
            int alphaIdx = Arrays.binarySearch(alphabets, c) + 1;
            index += (long) Math.pow(ALPHA_SIZE, len - i) * alphaIdx;
        }
        return index;
    }
}

/*
숫자로 문자를 찾는 함수와 가중치 조정

1부터 11까지의 문자열 a-z 26
26^11

알파벳 1개는 26개
2개는 27 ~ 26^2 까지, 즉 26^2 - 26^1
bans를 정렬 (역순으로)
각 인덱스를 계산 후 n과 비교
n보다 작거나 같으면 인덱스 기록
문자열로 인덱스가 계산이 가능해야함
a, b, c, d -> 인덱스 * 26^0
aa, df -> 26^1 * 1 + 26^0 * 1 

*/