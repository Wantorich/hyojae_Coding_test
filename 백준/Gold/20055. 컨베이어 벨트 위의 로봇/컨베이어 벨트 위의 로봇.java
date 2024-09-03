import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        int [] belt = new int[2*N];
        for (int i = 0; i < belt.length; i++) belt[i] = sc.nextInt();
        
        // 인덱스에 로봇 위치 관리
        int [] robots = new int[2*N];
        // 새로운 로봇 위치
        int [] tmp = new int[2*N];
        Queue<Integer> robotQ = new LinkedList<Integer>();
        
        int phase = 1;
        while (true) {
            // 벨트 한칸 회전
            int last = belt[belt.length-1];
            for (int i = belt.length-1; i > 0; i--) {
                belt[i] = belt[i-1];
            }
            belt[0] = last;
            
            // 로봇도 같이 이동
            int qSize = robotQ.size();
            for (int i = 0; i < qSize; i++) {
            	int prevPos = robotQ.poll();
            	int nextPos = (prevPos + 1) % belt.length;
        		tmp[nextPos] = 1;
            	
            	// 컨베이어 N 위치에 도달했다면
            	if (nextPos == N-1) {
            		tmp[nextPos] = 0; // 로봇 내림
            		continue;
            	}
            	robotQ.offer(nextPos);
            }
            robots = tmp.clone();
            Arrays.fill(tmp, 0);
            
            qSize = robotQ.size();
            // 처음 로봇부터 이동 가능한지 확인
            for (int i = 0; i < qSize; i++) {
            	int prevPos = robotQ.poll();
            	int nextPos = (prevPos + 1) % belt.length;
            	if (robots[nextPos] == 1 || belt[nextPos] == 0) {
            		robotQ.offer(prevPos); // 이동안하고 그대로 넣음
            		continue; // 못 옮김
            	}
            	
            	// N번째 벨트위치에 도달했는지 확인
            	if (nextPos == N-1) {
            		robots[prevPos] = 0;
            		belt[N-1]--;
            		continue;
            	} 
            	
            	else {
            		// 로봇 한칸 이동
            		robots[prevPos] = 0;
            		robots[nextPos] = 1;
            		belt[nextPos]--;
            	}
            	
            	// 로봇 업데이트가 안됐구나
            	robotQ.offer(nextPos);
            }
            
            // 올리는 칸이 내구도가 0이 아니라면 로봇 올린다
            if (robots[0] == 0 && belt[0] > 0 ) {
            	robots[0] = 1;
            	belt[0]--;
            	robotQ.offer(0);
            }
            
            int cnt = 0;
            for (int i = 0; i < belt.length; i++) {
            	if (belt[i] == 0) cnt++;
            }
            
            if (cnt >= K) break;
            phase++;
        }
        
        System.out.println(phase);
        sc.close();
    }
}