import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
    // FIFO 위반한 갯수 구하기
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Set<String> inTunnel = new HashSet<>();
        Queue<String> FIFO = new ArrayDeque<>();

        int res = 0;
        for(int i = 0; i<2*N; i++){
            String input = in.readLine();
            if(!inTunnel.contains(input)){
                inTunnel.add(input);
                FIFO.add(input);
            }
            else{
                if(FIFO.peek().equals(input)){
                    inTunnel.remove(input);
                    // 추월때문에 꼬인애들 다 빼내기
                    while (!FIFO.isEmpty() && !inTunnel.contains(FIFO.peek()))
                        FIFO.poll();
                }
                else{
                    res++;
                    inTunnel.remove(input);
                }
            }
        }

        System.out.println(res);
    }
}
