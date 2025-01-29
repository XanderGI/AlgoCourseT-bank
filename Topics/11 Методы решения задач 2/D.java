import java.util.*;

public class TaskD11DPMinCostSplitWood {
    public static int minCost(int L, int[] cuts) {
        int N = cuts.length;
        int[] newCuts = new int[N + 2];
        System.arraycopy(cuts, 0, newCuts, 1, N);
        newCuts[0] = 0;
        newCuts[N + 1] = L;
        int[][] memo = new int[N + 2][N + 2];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return minCostRecursive(newCuts, 0, N + 1, memo);
    }

    private static int minCostRecursive(int[] cuts, int i, int j, int[][] memo) {
        if (i + 1 == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int minCost = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = cuts[j] - cuts[i]
                    + minCostRecursive(cuts, i, k, memo)
                    + minCostRecursive(cuts, k, j, memo);
            minCost = Math.min(minCost, cost);
        }
        memo[i][j] = minCost;
        return minCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        int N = sc.nextInt();
        int[] cuts = new int[N];
        for (int i = 0; i < N; i++) {
            cuts[i] = sc.nextInt();
        }
        System.out.print(minCost(L, cuts));
    }
}