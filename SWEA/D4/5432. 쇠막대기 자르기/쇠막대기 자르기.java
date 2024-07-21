import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        sc.nextLine();

        for (int t = 1; t <= test_case; t++) {
            char [] arr = sc.nextLine().trim().toCharArray();
            int slice = 0, size = 0, iron = 0;
            
            for (int i = 0; i < arr.length; i++) {
            	if (arr[i] == ')') {
            		size--;
            		if (arr[i-1] == '(') {
            			// razor
            			slice += size;
            		}
            		else {
            			iron++;
            		}
            	}
            	else {
            		size++;
            	}
            }
            
            System.out.printf("#%d %d %n", t, slice+iron);

        }
    }

}

/*
쇠막대기가 레이저 총 맞은 수 + 1개로 잘림
처음 쇠막대기 수 구하고 각 쇠막대기가 레이저 맞을때마다 횟수 더해준다 

스택에 남아잇는 개수만큼 더해주고
마지막에 총 쇠막대기 개수 더해준게 정답

*/