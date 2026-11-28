import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ElectricCircuit {
    private static final int INF = 1_000_000_000;

    private static class Edge {
        int to;
        int resistance;
        int voltage;

        Edge(int to, int resistance, int voltage) {
            this.to = to;
            this.resistance = resistance;
            this.voltage = voltage;
        }
    }

    private static class State {
        int node;
        int voltage;
        int resistance;

        State(int node, int voltage, int resistance) {
            this.node = node;
            this.voltage = voltage;
            this.resistance = resistance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = readNonEmptyLine(br);
        if (firstLine == null) {
            return;
        }

        StringTokenizer st = new StringTokenizer(firstLine);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int source = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        int maxVoltage = Integer.parseInt(st.nextToken());
        int requiredCurrent = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String line = readNonEmptyLine(br);
            if (line == null) {
                break;
            }
            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int resistance = Integer.parseInt(st.nextToken());
            int voltage = Integer.parseInt(st.nextToken());
            int currentLimit = Integer.parseInt(st.nextToken());
            String switchState = st.nextToken();

            if ("ON".equals(switchState) && currentLimit >= requiredCurrent && voltage <= maxVoltage) {
                graph.get(u).add(new Edge(v, resistance, voltage));
                graph.get(v).add(new Edge(u, resistance, voltage));
            }
        }

        if (source == destination) {
            System.out.println(0);
            return;
        }

        int[][] minResistance = new int[n + 1][maxVoltage + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(minResistance[i], INF);
        }
        minResistance[source][0] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(
                Comparator.comparingInt((State s) -> s.resistance)
                        .thenComparingInt(s -> s.voltage)
        );
        pq.add(new State(source, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.resistance > minResistance[cur.node][cur.voltage]) {
                continue;
            }
            if (cur.node == destination) {
                System.out.println((long) cur.voltage * requiredCurrent);
                return;
            }

            for (Edge edge : graph.get(cur.node)) {
                int nextVoltage = cur.voltage + edge.voltage;
                if (nextVoltage > maxVoltage) {
                    continue;
                }
                int nextResistance = cur.resistance + edge.resistance;
                if (nextResistance < minResistance[edge.to][nextVoltage]) {
                    minResistance[edge.to][nextVoltage] = nextResistance;
                    pq.add(new State(edge.to, nextVoltage, nextResistance));
                }
            }
        }

        System.out.println(-1);
    }

    private static String readNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                return line;
            }
        }
        return null;
    }
}
