import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char [] charArr = sc.nextLine().trim().toCharArray();
		int cnt = 0;
		
		for (int i = 0; i < charArr.length; i++) {
			char c = charArr[i];
			if (!Character.isAlphabetic(c)) continue;
			cnt++;
			
			if (c == 'j') {
				if (i > 0 && (charArr[i-1] == 'l' || charArr[i-1] == 'n')) cnt--;
			}
			
			else if (c == 'z') {
				boolean p_cond = i > 0 && charArr[i-1] == 'd';
				boolean n_cond = i < charArr.length-1 && charArr[i+1] == '=';
				if (p_cond && n_cond) cnt--;
			}
		}
		System.out.println(cnt);
		
		sc.close();
	}

}
