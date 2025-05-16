import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int X = sc.nextInt();
    	String binary = Integer.toBinaryString(X);
    	long answer = Arrays.stream(binary.split(""))
    			.filter(str -> str.equals("1"))
    			.count();
    	System.out.println(answer);
    	sc.close();
    }
}
