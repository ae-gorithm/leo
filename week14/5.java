import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // visit을 비트마스킹으로 관리하려고 했는데 문제가있다.
    // visit여부는 각 비트의 순서가 상관 없다고 생각했는데
    // 그렇지 않았다.
    // 2번을 뒤집고 3번 뒤집기 != 3번 뒤집고 2번 뒤집기
    // 따라서 그냥 해야하며, arr[] 자체를 visit state로 사용해야함.
    // 14352687 -> 이걸 visit으로 사용하는것으로 바꾸자.
    static String answerString;
    static class State {
        String arrangement;
        int reverseCnt;

        State(String a, int r) {
            this.arrangement = a;
            this.reverseCnt = r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        makeAns(N);

        // 비트마스킹을 이용해 visit 관리 한 BFS
        int[] arr = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // {visit, cnt}
        Queue<State> queue = new ArrayDeque<>();
        String initialString = arrToString(arr);
        queue.add(new State(initialString, 0));
        Set<String> visited = new HashSet<>();
        visited.add(initialString);
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (isIncreasing(cur.arrangement)) {
                System.out.println(cur.reverseCnt);
                return;
            }

            for (int i = 0; i <= N - K; i++) {
                String nextState = makeState(cur.arrangement, i, K);
                if(visited.contains(nextState))
                    continue;

                visited.add(nextState);
                queue.add(new State(nextState, cur.reverseCnt+1));
            }
        }
        System.out.println(-1);
    }

    private static String makeState(String arrangement, int i, int k) {
        StringBuilder newArrangement = new StringBuilder();
        StringBuilder reversed = new StringBuilder(arrangement.substring(i, i + k)).reverse();
        newArrangement.append(arrangement.substring(0,i)).append(reversed).append(arrangement.substring(i+k));
        return newArrangement.toString();
    }

    private static void makeAns(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=n; i++)
            sb.append(i);
        answerString = sb.toString();
    }

    private static String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) sb.append(j);

        return sb.toString();
    }

    private static boolean isIncreasing(String state) {
        return state.equals(answerString);
    }
}
