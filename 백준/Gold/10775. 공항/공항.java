import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	
    	TreeSet<Integer> ts = IntStream.rangeClosed(1, N).boxed()
    			.collect(Collectors.toCollection(TreeSet::new));
    	
    	int answer = 0;
    	int M = Integer.parseInt(br.readLine());
    	int gate;
    	for (int i = 0; i < M; i++) {
    		gate = Integer.parseInt(br.readLine());
    		if (ts.floor(gate) == null) 
    			break;
    		ts.remove(ts.floor(gate));
    		answer++;
    	}
    	System.out.println(answer);
    }
}