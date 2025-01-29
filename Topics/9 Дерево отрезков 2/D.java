import java.util.*;
import java.io.*;


public class D9Testing {
    private static final int N = 524288;
    private static Node[] tree = new Node[2 * N - 1];
    private static final int offset = 200000;

    private static class Node {
        int max;
        int index;
        int promiseSum;
    }

    private static class Square {
        int y1;
        int y2;
        int x;
        boolean isOpen;

        public Square(int x, int y1, int y2, boolean isOpen) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.isOpen = isOpen;
        }
    }

    private static void buildTree() {
        for (int i = 0; i < 2 * N - 1; ++i) {
            tree[i] = new Node();
        }
        for (int i = N - 1; i < 2 * N - 1; ++i) {
            tree[i].max = 0;
            tree[i].index = i - N + 1;
            tree[i].promiseSum = 0;
        }
        for (int i = N - 2; i >= 0; --i) {
            tree[i].max = 0;
            tree[i].index = tree[i * 2 + 1].index;
            tree[i].promiseSum = 0;
        }
    }

    private static void update(int L, int R, int v, int left, int right, int x) {
        if (R <= left || right <= L) {
            return;
        }
        if (left >= L && right <= R) {
            tree[v].promiseSum += x;
            tree[v].max += x;
            return;
        }
        push(v);
        int m = (right + left) >>> 1;
        update(L, R, v * 2 + 1, left, m, x);
        update(L, R, v * 2 + 2, m, right, x);
        tree[v].max = Math.max(tree[v * 2 + 1].max, tree[v * 2 + 2].max);
        tree[v].index = tree[v * 2 + 1].max >= tree[v * 2 + 2].max ? tree[v * 2 + 1].index : tree[v * 2 + 2].index;
    }

    private static void push(int v) {
        int left = v * 2 + 1;
        int right = v * 2 + 2;
        tree[left].max += tree[v].promiseSum;
        tree[right].max += tree[v].promiseSum;
        tree[left].promiseSum += tree[v].promiseSum;
        tree[right].promiseSum += tree[v].promiseSum;
        tree[v].promiseSum = 0;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Square> requests = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; ++i) {
            String[] req = reader.readLine().split(" ");
            int x1 = Integer.parseInt(req[0]);
            int y1 = Integer.parseInt(req[1]);
            int x2 = Integer.parseInt(req[2]);
            int y2 = Integer.parseInt(req[3]);
            requests.add(new Square(x1 + offset, y1 + offset, y2 + offset, true));
            requests.add(new Square(x2 + offset, y2 + offset, y1 + offset, false));
        }
        Collections.sort(requests, (a, b) -> {
            if (a.x != b.x) {
                return Integer.compare(a.x, b.x);
            } else {
                return Boolean.compare(!a.isOpen, !b.isOpen);
            }
        });
        buildTree();

        int x = 0, y = 0, max = 0;
        for (Square event : requests) {
            if (event.isOpen) {
                update(event.y1 + N - 1, event.y2 + N, 0, N - 1, 2 * N - 1, 1);
            } else {
                update(event.y2 + N - 1, event.y1 + N, 0, N - 1, 2 * N - 1, -1);
            }
            Node result = tree[0];
            if (result.max > max) {
                y = result.index;
                x = event.x;
                max = result.max;
            }
        }
        System.out.println(max);
        System.out.println((x - offset) + " " + (y - offset));
    }
}