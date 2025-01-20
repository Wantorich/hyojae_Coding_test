import java.util.*;
import java.io.*;

public class Main {
	static int zeroCnt, oneCnt;
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int tc = sc.nextInt();
    	
    	long[] zeroCnts = new long[41];
    	long[] oneCnts = new long[41];
    	
    	zeroCnts[0] = 1; zeroCnts[1] = 0;
    	oneCnts[0] = 0; oneCnts[1] = 1;
    	
    	for (int i = 2; i <= 40; i++) {
    		zeroCnts[i] = zeroCnts[i-1] + zeroCnts[i-2];
    		oneCnts[i] = oneCnts[i-1] + oneCnts[i-2];
    	}
    	
    	for (int i = 0; i < tc; i++) {
    		int N = sc.nextInt();
    		System.out.printf("%d %d\n", zeroCnts[N], oneCnts[N]);
    	}
    	
    	sc.close();
    }
}
