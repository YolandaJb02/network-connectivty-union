//Yolanda Exalus

import java.util.*;

public class Main {

    static class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;
        int[] size;
        long connectivity;

        DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            size = new int[n + 1];

            connectivity = n;

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);

            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY)
                return;

            long sizeX = size[rootX];
            long sizeY = size[rootY];

            connectivity -= sizeX * sizeX;
            connectivity -= sizeY * sizeY;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];

                long newSize = size[rootY];
                connectivity += newSize * newSize;
            }
            else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];

                long newSize = size[rootX];
                connectivity += newSize * newSize;
            }
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
                size[rootX] += size[rootY];

                long newSize = size[rootX];
                connectivity += newSize * newSize;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            edges[i] = new Edge(u, v);
        }

        int[] destroy = new int[d];
        boolean[] removed = new boolean[m];

        for (int i = 0; i < d; i++) {
            destroy[i] = sc.nextInt() - 1; // convert to 0-based
            removed[destroy[i]] = true;
        }

        DisjointSet ds = new DisjointSet(n);

        // Build graph with all non-deleted edges
        for (int i = 0; i < m; i++) {
            if (!removed[i]) {
                ds.union(edges[i].u, edges[i].v);
            }
        }

        long[] answer = new long[d + 1];

        answer[d] = ds.connectivity;

        // Add deleted edges back in reverse order
        for (int i = d - 1; i >= 0; i--) {

            Edge e = edges[destroy[i]];

            ds.union(e.u, e.v);

            answer[i] = ds.connectivity;
        }

        // Print answers
        for (int i = 0; i <= d; i++) {
            System.out.println(answer[i]);
        }

        sc.close();
    }
}