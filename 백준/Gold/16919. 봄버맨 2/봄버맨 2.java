import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int R, C, N, time, b_cnt;
    static long result = Long.MAX_VALUE;
    static boolean[] v;
    static int [][] grid;
    static int [][] mov = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        grid = new int[R][C];
        for (int i = 0; i < R; i++) {
            char [] input = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (input[j] == '.') {
                    grid[i][j] = -1;
                }
                else {
                    // 폭탄 'O'
                    grid[i][j] = 3;
                }
            }
        }
        // 1 제외
        // 3 5 7 9 11 13 터지는거 홀수
        // 2 4 6 8 10 12 심는거 짝수
        // 1, 2(심음), 3(터짐), 4(심음), 5, 6(터짐), 7(심기)

    
        // 5가 되면 첫 화면이랑 똑같아지네? 그럼 나머지 5를하면
        // 5는 5임 6은 1의 결과
        // 5의 남아있는 폭탄은 7에터짐
        // 6이면 남아있는곳에 폭탄을 박겠지 이게 2랑 똑같음
        // 즉 1~4가 다르고 5~8이 다른데 주기가 반복됌
        // 근데 N이 4의 배수이면 
        // 7의 결과는 3의 결과와 똑같음? 
        // 8의 결과는 4의 결과와 똑같음 N이
    // N이 짝수면 무조건 다 채워야됌
    // N이 홀수일땐? 1이면 받은대로 바로 나가고
    // 2일땐 빈공간 채우고
    // 3일땐 초기에 세팅해놓은 폭탄이 터짐
    // 근데 폭탄이 맵 전체를 터트리게되면 4일때 다 채워놓고, 5 6 똑같음 7터지고 8세팅 9 10 똑같고 11터지고
    // 다 터지는 경우가 아니면 반복되는게 맞음
    
    
    
    time = 2;
    b_cnt = 0;
    while (time <= N) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (time % 2 == 0) {
                    // 심기
                    if (grid[i][j] == -1) { 
                        grid[i][j] = time + 3; // 터지는 시간 설정
                    }
                }
                
                else if (time % 2 == 1) {
                    // 터트리기
                    if (grid[i][j] == time) { // 터트리기
                        bomb(i,j);
                    }
                }
                
            }
        }
//        System.out.println("b_cnt : " + b_cnt);
        if (b_cnt == R*C) {
        	// 폭탄을 터트렸는데 모두가 폭탄이면
        	// 나머지가 3일땐 다 .
        	// 나머지가 0일때 채워놓고 1, 2 쉬고 7은 터지고
    		for (int i = 0; i < R; i++) {
    			for (int j = 0; j < C; j++) {
    				if (N % 4 == 3) {
    					grid[i][j] = -1;
    				}
    				else {
    					grid[i][j] = 1; // 아무거나
    				}
    			}
    		}
    		break;
        }
        else {
        	N = N % 4 == 0 ? 4 : N % 4 + 4;
        }
        
        b_cnt = 0;
        
        time++;
    }
    
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            if (grid[i][j] == -1) {
            	bw.write('.');
            }
            else {
            	bw.write('O');
            }
            }
        bw.flush();
        bw.newLine();
        }
    bw.close();
    }

    static void bomb(int r, int c) {
        grid[r][c] = -1;
        b_cnt++;
        for (int i = 0; i < mov.length; i++) {
            int nr = r + mov[i][0];
            int nc = c + mov[i][1];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                // 폭탄이 터질때 주변에 폭탄이 아닌것만 터트린다, 폭탄은 안바꿈
            	// 이미 터진곳도 안터트림
                if (grid[nr][nc] != time && grid[nr][nc] != -1) {
                    grid[nr][nc] = -1;
                    b_cnt++;
                }
            }
        }
    }


}