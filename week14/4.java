import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 트리 구조
        List<Integer>[] childs = new ArrayList[n+1];
        int[] praise = new int[n+1];
        for(int i = 1; i<=n ;i ++){
            childs[i] = new ArrayList<>();
        }

        st = new StringTokenizer(in.readLine());
        for(int i = 1 ; i<=n; i++){
            int parent= Integer.parseInt(st.nextToken());
            if (parent == -1)
                continue;

            childs[parent].add(i);
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(in.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            praise[idx] += p;
        }

        dfs(childs, praise, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=n; i++)
            sb.append(praise[i]).append(" ");

        System.out.println(sb);
    }

    private static void dfs(List<Integer>[] childs, int[] praise, int curPos) {
        for(int x : childs[curPos]){
            praise[x] += praise[curPos];
            dfs(childs,praise,x);
        }
    }
}
