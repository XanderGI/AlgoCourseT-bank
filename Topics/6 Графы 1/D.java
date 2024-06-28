//package Tink.less6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;


public class TaskD6HungryHorseAndBFS {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static StringBuilder result = new StringBuilder();

    static class Point {
        int x;
        int y;
        Point parent;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.parent = null;
        }
        public Point(int x, int y, Point parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        public String toString() {
            return this.x + " " + this.y;
        }
    }

    static boolean isCorrectCoords(Point point) {
        return point.x  > 0 && point.x <= n &&  point.y > 0 && point.y <= n;
    }

    static void bfs(ArrayDeque<Point> points, byte[][] matrixOfVisits, Point finishPoint) {

        int[][] movements = {{1,2},{2,1},{-1,-2},{-2,-1},{2,-1},{1,-2},{-1,2},{-2,1}};
        while(!points.isEmpty()) {
            Point currentPoint = points.pollFirst();
            matrixOfVisits[currentPoint.x-1][currentPoint.y-1] = 1;
            if (currentPoint.x == finishPoint.x && currentPoint.y == finishPoint.y) {
                LinkedList<Point> way = new LinkedList<>();
                Point p = currentPoint;
                while (p != null) {
                    way.addFirst(p);
                    p = p.parent;
                }
                result.append(way.size() - 1).append("\n");
                for (Point point : way) {
                    result.append(point.toString()).append("\n");
                }
                return;
            }
            for (int i =0; i < movements.length; i++) {
                Point tempPoint = new Point(currentPoint.x + movements[i][0], currentPoint.y + movements[i][1], currentPoint);
                if (isCorrectCoords(tempPoint) && matrixOfVisits[tempPoint.x-1][tempPoint.y-1] == 0) {
                    points.addLast(tempPoint);
                    matrixOfVisits[tempPoint.x-1][tempPoint.y-1] = 1;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(reader.readLine());
        byte[][] matrixOfVisits = new byte[n][n];
        ArrayDeque<Point> pointArrayDeque = new ArrayDeque<>();
        String[] beginString = reader.readLine().split(" ");
        String[] endString = reader.readLine().split(" ");
        Point beginPoint = new Point(Integer.parseInt(beginString[0]),Integer.parseInt(beginString[1]));
        pointArrayDeque.add(beginPoint);
        Point finishPoint = new Point(Integer.parseInt(endString[0]),Integer.parseInt(endString[1]));
        bfs(pointArrayDeque,matrixOfVisits,finishPoint);
        System.out.println(result);
    }
}