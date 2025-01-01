import java.util.stream.*;
import java.util.*;

class Solution {
    public String solution(String new_id) {
        String first = Arrays.stream(new_id.split(""))
                .map(c -> c.charAt(0))
                .map(c -> Character.isAlphabetic(c) ? Character.toLowerCase(c) : c)
                .filter(c -> isValid(c))
                .map(c -> c + "")
                .collect(Collectors.joining());
        // 3 4 단계
        String three = first.replaceAll("[..]+", ".");
        String four = three.startsWith(".") ? three.substring(1) : three;
        four = three.endsWith(".") ? four.substring(0, four.length()) : four;
        // System.out.println("four : " + four);
        
        String five = four.isEmpty() ? "a" : four;
        String six = five.length() >= 16 ? five.substring(0, 15) : five;
        six = six.endsWith(".") ? six.substring(0, six.length()-1) : six;
        String seven = six;
        if (six.length() <= 2) {
            String last = six.charAt(six.length()-1) + "";
            seven = six + last.repeat(3 - six.length());
        } 
        // System.out.println("seven : " + seven);
        return seven;
    }
    
    static boolean isValid(char c) {
        String str = c + "";
        return str.matches("[a-z0-9_.-]");
    }
}