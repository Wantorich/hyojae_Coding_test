import java.util.*;
import java.util.stream.*;
import java.util.stream.IntStream;

class Solution {
    static int MAX_VAL;
    static List<String> resList = new ArrayList();
    
    public String[] solution(String[] expressions) {
        List<String[]> expList = new ArrayList();
        for (String expression : expressions) {
            String[] params = getParameters(expression);
            expList.add(params);
        }
        
        List<Integer> maxIdxList = new ArrayList();
        O:for (int r = MAX_VAL+1; r <= 9; r++) {
            for (int i = 0; i < expList.size(); i++) {
                String [] exps = expList.get(i);
                if (exps[4].equals("X")) continue;
                
                int a = radixInt(exps[0], r);
                int b = radixInt(exps[2], r);
                int c = radixInt(exps[4], r);

                int leftExp = exps[1].equals("+") ? a + b : a - b;
                if (leftExp != c) continue O;                    
            }
            maxIdxList.add(r);
        }
        
        for (int i = 0; i < expList.size(); i++) {
            String[] exp = expList.get(i);
            if (!exp[4].equals("X")) continue;
            judgeExp(exp, maxIdxList);
        }

        return resList.toArray(new String[0]);
    }
    
    static int radixInt(String origin, int radix) {
        int result = 0;
        int num = Integer.parseInt(origin);
        for (int i = 2; i >= 0; i--) {
            int tenPow = (int) Math.pow(10, i);
            if (num >= tenPow) {
                result += num / (int) Math.pow(10, i) * (int) Math.pow(radix, i);
                num %= tenPow;
            }
        }
        return result;
    }
    
    static void judgeExp(String[] exp, List<Integer> idxList) {
        Set<String> resSet = new HashSet();
        for (int idx : idxList) {
            int a = radixInt(exp[0], idx);
            int b = radixInt(exp[2], idx);
            int leftExp = exp[1].equals("+") ? a + b : a - b;
            resSet.add(Integer.toString(leftExp, idx));
        }

        Iterator<String> it = resSet.iterator();
        String result = String.format("%s %s %s = X", exp[0], exp[1], exp[2]);
        result = resSet.size() > 1 ? result.replace("X", "?") : result.replace("X", it.next());
        resList.add(result);        
    }
    
    static String[] getParameters(String expression) {
        StringTokenizer st = new StringTokenizer(expression, " ");
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            String token = st.nextToken().trim();
            result[i] = token;
            if (i == 0 || i == 2 || (i == 4 && !token.equals("X"))) {
                for (int j = 0; j < token.length(); j++) 
                    MAX_VAL = Math.max(MAX_VAL, token.charAt(j) - '0');
            }
        }
        return result;
    }
}