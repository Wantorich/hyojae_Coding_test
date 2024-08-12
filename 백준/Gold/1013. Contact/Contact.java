import java.util.*;
import java.io.*;

public class Main {
	static boolean find = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		for (int t = 0; t < test_case; t++) {
			find = false;
			findPattern(br.readLine());
			String result = find ? "YES" : "NO";
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void findPattern(String str) {
		if (str.length() == 0) {
			find = true;
			return;
		}
		
		if (find) return;
		
		
		if (str.startsWith("100") && str.length() > 3) {
			int idx = -1;
			for (int i = 3; i < str.length(); i++) {
				if (str.charAt(i) == '1') {
					idx = i;
					break;
				}
			}
			if (idx == -1) return;
			
			for (int j = idx; j < str.length(); j++) {
				if (str.charAt(j) == '1') {
					if (j + 1 < str.length()) {
						findPattern(str.substring(j+1));
					}
					if (j == str.length()-1) {
						find = true;
						return;
					}
				}
				else if (str.charAt(j) == '0') {
					return;
				}
			}
		}
		
		if (str.startsWith("01")) {
			if (str.length() > 2) {
				findPattern(str.substring(2));
				return;
			}
			else if (str.length() == 2) {
				find = true;
				return;
			}
		}
	}
}
