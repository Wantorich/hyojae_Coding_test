import java.util.*;
import java.util.stream.*;
import java.util.stream.IntStream;

class Solution {
    static int MAX_VAL;
    static List<String> opList, resList;
    static int[] radixCnt;
    
    public String[] solution(String[] expressions) {
        String[] answer = {};
        opList = new ArrayList();
        radixCnt = new int[10];
        resList = new ArrayList();
        
        List<String[]> expList = new ArrayList();
        for (String expression : expressions) {
            String[] params = getParameters(expression);
            expList.add(params);
        }
        
        List<Integer> maxIdxList = new ArrayList();
        O:for (int r = MAX_VAL+1; r <= 9; r++) {
            for (int i = 0; i < expList.size(); i++) {
                String [] exps = expList.get(i);
                if (exps[2].equals("X")) continue;
                
                int a = radixInt(exps[0], r);
                int b = radixInt(exps[1], r);
                int c = radixInt(exps[2], r);

                int leftExp = opList.get(i).equals("+") ? a + b : a - b;
                if (leftExp != c) {
                    continue O;                    
                }
            }
            maxIdxList.add(r);
        }
        
        for (int num : maxIdxList)
            System.out.print(num + " ");
        
        for (int i = 0; i < expList.size(); i++) {
            String[] exp = expList.get(i);
            if (!exp[2].equals("X")) continue;
            judgeExp(exp, i, maxIdxList);
        }
        
        for (String str : resList)
            System.out.println(str);
        
        return resList.toArray(new String[0]);
    }
    
    static String convertValue(int origin, int radix) {
        if (origin == 0) return "0";
        
        StringBuilder sb = new StringBuilder("");
        int initVal = 1;
        while (initVal*radix <= origin) initVal *= radix;
        while (initVal > 0) {
            int divide = origin / initVal;
            sb.append(divide);
            origin %= initVal;
            initVal /= radix;
        }
        return sb.toString();
    }
    
    static int radixInt(String origin, int radix) {
        int result = 0;
        int num = Integer.parseInt(origin);
        if (num >= 100) {
            result += radix*radix* (num / 100);
            num %= 100;
        }
        if (num >= 10) {
            result += radix * (num / 10);
            num %= 10;
        }
        result += num;
        
        // for (int i = origin.length()-1; i >= 0; i--) {
        //     result += (origin.charAt(i) - '0') * (int) (Math.pow(radix, origin.length()-1-i));
        // }
        return result;
    }
    
    static void judgeExp(String[] exp, int opIdx, List<Integer> idxList) {
        Set<String> resSet = new HashSet();
        for (int idx : idxList) {
            int a = radixInt(exp[0], idx);
            int b = radixInt(exp[1], idx);
            int leftExp = opList.get(opIdx).equals("+") ? a + b : a - b;
            resSet.add(convertValue(leftExp, idx));
        }

        Iterator<String> it = resSet.iterator();
        
        String result = String.format("%s %s %s = X", exp[0], opList.get(opIdx), exp[1]);
        result = resSet.size() > 1 ? result.replace("X", "?") : result.replace("X", it.next());
        
        resList.add(result);        
    }
    
    static String[] getParameters(String expression) {
        StringTokenizer st = new StringTokenizer(expression, " ");
        List<String> params = new ArrayList();
        
        for (int i = 0; i < 5; i++) {
            String token = st.nextToken().trim();
            if (i == 1) {
                opList.add(token);
                continue;
            }
            if (i == 3) continue;
            params.add(token);
            
            if (!token.equals("X")) {
                for (int j = 0; j < token.length(); j++) {
                    MAX_VAL = Math.max(MAX_VAL, token.charAt(j) - '0');
                }
            }
        }
        return params.toArray(new String[0]);
    }
}