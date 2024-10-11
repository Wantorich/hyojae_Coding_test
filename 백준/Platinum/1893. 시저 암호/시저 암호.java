import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        sc.nextLine();
        
        for (int t = 1; t <= tc; t++) {
        	String alpha = sc.nextLine();
        	String pattern = sc.nextLine();
        	String text = sc.nextLine();
        	
        	int[] pi = new int[pattern.length()];
        	int j = 0;
        	for (int i = 1; i < pi.length; i++) {
        		// 일치하지 않는경우
        		while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
        			j = pi[j-1];
        		if (pattern.charAt(i) == pattern.charAt(j)) {
        			pi[i] = ++j;
        		} 
        	}
        	
        	StringBuilder sb = new StringBuilder();
        	String shiftStr = null;
        	int cnt = 0;
        	List<Integer> shiftList = new ArrayList<Integer>();
        	
        	for (int shift = 0; shift < alpha.length(); shift++) {
        		sb.setLength(0);
        		cnt = 0;
        		
        		for (int i = 0; i < text.length(); i++) {
        			int idx = alpha.indexOf(text.charAt(i));
        			idx = (idx + (alpha.length() - shift)) % alpha.length();
        			sb.append(alpha.charAt(idx));
        		}
        		shiftStr = sb.toString();
        		
        		// KMP
        		j = 0;
        		for (int i = 0; i < shiftStr.length(); i++) {
        			// 일치하지 않는경우
        			while (j > 0 && shiftStr.charAt(i) != pattern.charAt(j))
        				j = pi[j-1];
        			if (shiftStr.charAt(i) == pattern.charAt(j)) {
        				if (++j == pattern.length()) {
        					cnt++;
        					j = pi[j-1];
        				}
        			} 
        		}
        		
        		if (cnt == 1) // 조건에 맞게 딱 한 문자만 만족한다면
        			shiftList.add(shift);
        	}
        	
        	// shiftList가 비어있으면 no Solution
        	// 1개 있으면 unique
        	// 2개 이상있으면 ambiguous
        	switch (shiftList.size()) {
	        	case 0 :
	        		System.out.println("no solution");
	        		break;
	        	case 1 :
	        		System.out.println("unique: " + shiftList.get(0));
	        		break;
        		default :
        			System.out.print("ambiguous: ");
        			for (int shift : shiftList) System.out.print(shift + " ");
        			System.out.println();
        	}
        }
        sc.close();
    }
}