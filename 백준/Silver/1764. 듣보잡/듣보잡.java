import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	Set<String> set = new HashSet<>();
    	for (int i = 0; i < N; i++)
    		set.add(br.readLine());
    	
    	List<String> list = new ArrayList<>();
    	String name;
    	for (int i = 0; i < M; i++) {
    		name = br.readLine();
    		if (set.contains(name))
    			list.add(name);
    	}
    	
    	list.sort(Comparator.naturalOrder());
    	System.out.println(list.size());
    	System.out.println(list.stream().collect(Collectors.joining("\n")));
    }
}
