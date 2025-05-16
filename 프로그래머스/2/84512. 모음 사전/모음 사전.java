import java.util.*;

class Solution {
    static List<String> wordList = new ArrayList<>();
    static char[] words = {'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        for (int i = 1; i <= 5; i++) 
            pwr(new char[i], 0);
        Collections.sort(wordList);
        // wordList.forEach(w -> System.out.print(w + " "));
        int idx = wordList.indexOf(word) + 1;
        return idx;
    }
    
    static void pwr(char[] sel, int k) {
        if (k == sel.length) {
            wordList.add(new String(sel));
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            sel[k] = words[i];
            pwr(sel, k+1);
        }
    }
}