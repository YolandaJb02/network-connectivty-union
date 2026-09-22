/* Yolanda Exalus
Lab 3
COP 3503 */

import java.util.*;

public class Main {

    // Disjoint Set Union (Union-Find)
    static class DisjointSet {
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];

            // Initially each node is its own parent
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // Find with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union by size
        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            // Already in the same set
            if (rootA == rootB) {
                return false;
            }

            // Attach smaller tree to larger tree
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];

            return true;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();

        // Adjacency list for the graph
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // Read edges
        for (int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        // Order in which barns are closed
        int[] order = new int[N];
        for (int i = 0; i < N; i++) {
            order[i] = in.nextInt();
        }

        // Tracks whether a barn is currently open
        boolean[] open = new boolean[N + 1];

        // Stores answers corresponding to each stage
        boolean[] answer = new boolean[N];

        DisjointSet ds = new DisjointSet(N);

        // Number of connected components among open barns
        int components = 0;

        // Process barns in reverse (adding them back)
        for (int i = N - 1; i >= 0; i--) {

            int barn = order[i];

            // Open this barn
            open[barn] = true;
            components++;

            // Union with all neighbors that are already open
            for (int neighbor : graph[barn]) {
                if (open[neighbor]) {

                    // If union succeeds, two components merge into one
                    if (ds.union(barn, neighbor)) {
                        components--;
                    }
                }
            }

            // If there is exactly one component, all open barns are connected
            answer[i] = (components == 1);
        }

        // Print answers in original order
        for (int i = 0; i < N; i++) {
            if (answer[i]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        in.close();
    }
}