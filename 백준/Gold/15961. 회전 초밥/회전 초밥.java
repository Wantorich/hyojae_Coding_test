import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        String input[] = br.readLine().split(" ");
        
        int N = Integer.parseInt(input[0]);
        int D = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        int C = Integer.parseInt(input[3]);
        
        
        Queue<Integer> sushiQ = new LinkedList<Integer>();
        Queue<Integer> tempQ = new LinkedList<Integer>();
        int [] sushiIdx = new int[D+1];
        for (int i = 0; i < N; i++) sushiQ.offer(Integer.parseInt(br.readLine()));
        
        // K개 채워놓고 시작
        for (int i = 0; i < K; i++) {
        	int sushi = sushiQ.poll();
        	sushiQ.offer(sushi);
        	tempQ.offer(sushi);
        }

        // 초기 세팅
        int result = 0;
        int answer = 0;
        for (Integer idx : tempQ) {
        	if (sushiIdx[idx] == 0) result++;
        	sushiIdx[idx]++;
        }
        
        while (!sushiQ.isEmpty()) {
        	// Q를 한번만 돌게함
        	// 임시큐를 돌면서 최대가짓수 계산
        	int outSushi = tempQ.poll();
        	if (--sushiIdx[outSushi] == 0) result--; // 이제 해당 초밥 없음
        	
        	int inSushi = sushiQ.poll();
        	if (sushiIdx[inSushi]++ == 0) result++;
        	tempQ.offer(inSushi);
        	
        	// 추가 초밥
        	if (sushiIdx[C] == 0) {
        		sushiIdx[C]++;
        		result++;
        	}
        	
        	answer = Math.max(answer, result);
        	if (answer == K+1) break;
        }
        
        System.out.println(answer);
        
        sc.close();
    }
}