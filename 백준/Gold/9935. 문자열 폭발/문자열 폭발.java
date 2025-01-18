import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기
        String input = sc.nextLine(); // 원본 문자열
        String bomb = sc.nextLine(); // 폭발 문자열
        
        int bombLength = bomb.length();
        char lastChar = bomb.charAt(bombLength - 1); // 폭발 문자열의 마지막 문자
        
        // 스택 역할을 하는 StringBuilder
        StringBuilder stack = new StringBuilder();
        
        for (char c : input.toCharArray()) {
            stack.append(c); // 현재 문자를 스택에 추가
            
            // 스택의 끝부분이 폭발 문자열과 일치하는지 확인
            if (stack.length() >= bombLength && stack.charAt(stack.length() - 1) == lastChar) {
                if (stack.substring(stack.length() - bombLength).equals(bomb)) {
                    stack.delete(stack.length() - bombLength, stack.length()); // 폭발 문자열 제거
                }
            }
        }
        
        // 결과 출력
        if (stack.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(stack.toString());
        }
    }
}
