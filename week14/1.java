
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // /\ 형태가 돼야함.
    // 가장 큰 값을 기준으로 양옆으로 내림차순만들자.
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            pq.add(new int[]{L,H});
        }

        int minS = 0;
        int[] max = pq.poll();
        minS += max[1];
        int maxHeightIdxLeft = max[0];
        int maxHeightIdxRight = max[0];

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] < maxHeightIdxLeft){
                minS += ((maxHeightIdxLeft - cur[0]) * cur[1]);
                maxHeightIdxLeft = cur[0];
            }
            if(cur[0] > maxHeightIdxRight){
                minS += ((cur[0] - maxHeightIdxRight)) * cur[1];
                maxHeightIdxRight = cur[0];
            }
        }
        System.out.println(minS);
    }
}
