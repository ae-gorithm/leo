import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int min = 987654321;

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BFS + visited DP -> 이미 겪어본 판은 다시 겪지 않도록
        // 비트마스킹을 사용하는것도 괜찮을듯.
        // 혹은 1차원으로
        // Map에 대한 State를 어떻게 저장할것인가... -> 비마를 활용한 10개짜리 int?

        // 비트마스킹 DP + BFS 적용해봤지만
        // 1 1 1 1 1 1 1 1 을 막을 방법이 없음.
        // 나이브하게 생각하면 6^100이므로 이걸 메모리적으로 풀려고 해도 무리가 있는듯
        // 그냥 DFS + prunning + Backtracking 큰것부터 쓰는 DFS 으로 가야 할 것 같은데??
        // -> imos로 계산을 좀 더 빠르게 할 수 있도록 전처리하고
        // 5 by 5 타일부터 채우는 방향으로 해보자

        int[][] map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // imos 전처리
        int[][] imosMap = findImos(map);

        dfs(imosMap, -1, new int[]{0, 5, 5, 5, 5, 5}, 0);

        System.out.println(min == 987654321 ? -1 : min);
    }
    
    // ------- 규칙들 --------
    // 1. 1이 있으면 없애고 가야한다.
    // 2. (1 초과의 수나 || 음수를 만났거나 || numOfTiles가 부족하면) 잘못됐으므로 BT
    // 3. 이미 본 곳은 안봐도 된다.
    // 4. idx 100까지 다 해봤을 때 imosMap의 요소가 모두 0이면 성공
    private static void dfs(int[][] imosMap, int idx, int[] numOfTiles, int depth) {


        if (depth >= min)
            return;

        for (int i = idx + 1; i < 100; i++) {
            int r = i / 10;
            int c = i % 10;
            if (imosMap[r][c] < 0 || imosMap[r][c] > 2)
                return;

            if (imosMap[r][c] == 1) {
                for (int l = 5; l >= 1; l--) {
                    if (r + l >= 11 || c + l >= 11 || numOfTiles[l] <= 0)
                        continue;

                    imosMap[r][c]--;
                    imosMap[r + l][c + l]--;
                    imosMap[r + l][c]++;
                    imosMap[r][c + l]++;
                    numOfTiles[l]--;

                    dfs(imosMap, r * 10 + c, numOfTiles, depth + 1);

                    imosMap[r][c]++;
                    imosMap[r + l][c + l]++;
                    imosMap[r + l][c]--;
                    imosMap[r][c + l]--;
                    numOfTiles[l]++;
                }
            }
        }

        if (checkValidImos(imosMap)) {
            min = Math.min(depth, min);
        }
    }

    // 모두다 0인지 확인하기
    private static boolean checkValidImos(int[][] imosMap) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(imosMap[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    // superposition을 이용한 imos 전처리
    private static int[][] findImos(int[][] map) {
        int[][] result = new int[11][11];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1) {
                    result[i][j]++;
                    result[i][j + 1]--;
                    result[i + 1][j]--;
                    result[i + 1][j + 1]++;
                }
            }
        }

        return result;
    }


}
