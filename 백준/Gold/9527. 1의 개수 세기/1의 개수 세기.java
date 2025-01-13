import java.util.*;
import java.io.*;

public class Main {
	static long[] oneCnts = new long[55];
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	long A = sc.nextLong();
    	long B = sc.nextLong();
    	
    	// 자릿수 ex) 1111까지의 1의 개수의 합
    	// 111에다가 앞에 1을 더했다고 생각하면, 기존 총합 더하기
    	// 맨 앞의 1의 개수 = 2^n-1
    	oneCnts[1] = 1;
    	for (int i = 2; i < oneCnts.length; i++) {
    		oneCnts[i] = oneCnts[i-1] * 2 + ((long) 1 << i-1);  
    	}
    	
    	long sumB = solve(Long.toBinaryString(B));
    	long sumA = solve(Long.toBinaryString(A-1));
    	System.out.println(sumB - sumA);
    	sc.close();
    }

    // 주어진 문자열까지의 숫자의 1의 개수 세기
	private static long solve(String binary) {
		if (binary.equals("0")) return 0;
		
		long prev = oneCnts[binary.length()-1];
		int index = binary.indexOf("1", 1);
		
		if (index == -1) 
			return prev + 1;
		
		String substr = binary.substring(index);
		return prev + Long.parseLong(substr, 2) + 1 + solve(substr);
	}
}
