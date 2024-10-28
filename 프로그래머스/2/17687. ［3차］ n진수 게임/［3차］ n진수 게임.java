import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t*m; i++) {
            sb.append(Integer.toString(i, n).toUpperCase());
        }
        
        String numStr = sb.toString();
        StringBuilder result = new StringBuilder();
        for (int i = p-1, cnt = 0; cnt < t; i += m, cnt++) {
            result.append(numStr.charAt(i));
        }
        
        return result.toString();
    }
}