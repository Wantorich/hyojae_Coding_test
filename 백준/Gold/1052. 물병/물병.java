import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    	
    	if (N <= K) {
    		System.out.println(0);
    		return;
    	}
    	
    	int num = Integer.parseInt(Integer.toBinaryString(N), 2);
    	int answer = 0;
    	
    	int p;
    	while (Integer.bitCount(num) > K) {
    		// 가장 낮은 1을 1칸 이동하는데 필요한 물병의 개수
    		p = Integer.lowestOneBit(num);
    		answer += p;
    		num += p;
    	}
    	
    	System.out.println(answer);
    	sc.close();
    }
}
