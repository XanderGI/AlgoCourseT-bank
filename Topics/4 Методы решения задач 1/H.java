import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class TaskH4GreedyOlympics {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();

    static class Pair implements Comparable<Pair> {
        int begin;
        int duration;
        int numberOfTask;
        long end;

        public Pair(int begin, int duration, int numberOfTask) {
            this.begin = begin;
            this.duration = duration;
            this.numberOfTask = numberOfTask;
            this.end = ((long) begin) + ((long) duration);
        }

        @Override
        public int compareTo(Pair p) {
            return Long.compare(this.end, p.end);
        }

        @Override
        public String toString() {
            return "start: " + this.begin + " end: " + this.end + " index: " + this.numberOfTask;
        }
    }

    public static void findMaxCost(int n, List<Pair> listOfTasks, long cost) {
        long currentTime = 0;
        List<Pair> selectedTasks = new ArrayList<>();
        Collections.sort(listOfTasks);
        StringJoiner listOfSelectedTasks = new StringJoiner(" ");
        for (Pair task : listOfTasks) {
            if (currentTime <= task.begin) {
                currentTime = task.end;
                selectedTasks.add(task);
                listOfSelectedTasks.add(String.valueOf(task.numberOfTask));
            }
        }
        result.append(BigInteger.valueOf(selectedTasks.size()).multiply(BigInteger.valueOf(cost))).append("\n");
        result.append(selectedTasks.size()).append("\n");
        result.append(listOfSelectedTasks);
    }

    public static void main(String[] args) throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int cost = Integer.parseInt(firstLine[1]);
        List<Pair> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            Pair task = new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1]), i + 1);
            tasks.add(task);
        }
        findMaxCost(n, tasks, (long) cost);
        System.out.println(result);
    }
}