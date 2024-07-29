import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] people = new int[N];
        String [] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
        	people[i] = Integer.parseInt(line[i]);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        long cnt = N;
        for (int p_num : people) {
        	p_num -= B;
        	if (p_num > 0) {
        		cnt += p_num % C == 0 ? p_num / C : p_num / C + 1;
        	}
        }
        System.out.println(cnt);
    }
}