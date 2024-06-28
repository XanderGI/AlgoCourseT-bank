//package Tink.less6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task6GMinMaxDistanceWithFloyd {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int result = 2;

    public static void main(String... args) throws IOException {
        String[] edges = reader.readLine().split(" ");
        int n = Integer.parseInt(edges[0]);
        int m = Integer.parseInt(edges[1]);
        int[][] matrixOfAdjacency = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixOfAdjacency[i][j] = Integer.MAX_VALUE; //если не добраться => inf
            }
            matrixOfAdjacency[i][i] = 0; // сама в себя = 0
        }

        for (int i = 0; i < m; i++) {
            String[] line = reader.readLine().split(" ");
            int v = Integer.parseInt(line[0]);
            int u = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);
            matrixOfAdjacency[v-1][u-1] = w; // v->u
            matrixOfAdjacency[u-1][v-1] = w; //u->v
        }

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                for (int i =0; i < n; i++) {
                    if (matrixOfAdjacency[i][k] != Integer.MAX_VALUE && matrixOfAdjacency[k][j] != Integer.MAX_VALUE) {
                        matrixOfAdjacency[i][j] = Math.min(matrixOfAdjacency[i][j], matrixOfAdjacency[i][k] + matrixOfAdjacency[k][j]);
                    }
                }
            }
        }

        int minMaxDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int maxDistance = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                maxDistance = Math.max(maxDistance,matrixOfAdjacency[i][j]);
            }
            if (minMaxDistance > maxDistance) {
                minMaxDistance = maxDistance;
                result = i+1;
            }
        }
        System.out.println(result);
    }
}