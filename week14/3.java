import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 1차원 IMOS로 풀 수 있을 것 같은데
    // 석순은 1 ---- -1
    // 종유석은      1 ---- -1
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] imos = new int[H+1];
        for(int i = 0; i<N; i++){
            int h = Integer.parseInt(in.readLine());
            // 석순
            if(i % 2 == 0){
                imos[0] += 1;
                imos[h] -= 1;
            }

            // 종유석
            else{
                imos[H] -= 1;
                imos[H-h] += 1;
            }
        }

        int[] accumSum = new int[H];
        accumSum[0] = imos[0];
        for(int i = 1; i<H; i++){
            accumSum[i] = accumSum[i-1] + imos[i];
        }

        Arrays.sort(accumSum);
        int min = accumSum[0];
        int minCnt = 1;
        int idx = 1;
        while(min == accumSum[idx]){
            idx++;
            minCnt++;
        }

        System.out.println(min+" "+minCnt);
    }
}
