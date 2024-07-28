import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int R, C, N, time;
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

    
    time = 2;
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
        for (int i = 0; i < mov.length; i++) {
            int nr = r + mov[i][0];
            int nc = c + mov[i][1];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                // 폭탄이 터질때 주변에 폭탄이 아닌것만 터트린다, 폭탄은 안바꿈
                if (grid[nr][nc] != time) {
                    grid[nr][nc] = -1;
                }
            }
        }
    }


}