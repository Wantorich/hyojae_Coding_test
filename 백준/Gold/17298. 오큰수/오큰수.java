import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int[] NGE = new int[N];
        Arrays.fill(NGE, -1);
        
        int num;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek()[0] < num) {
            	NGE[stack.pop()[1]] = num;
            }
            stack.push(new int[] {num, i});
        }
        
        String answer = Arrays.stream(NGE).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(answer);
    }
}
