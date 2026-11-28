import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ATMWithdrawal {
    private static final int INF = 1_000_000_000;

    private static class Denom implements Comparable<Denom> {
        int val;
        int total;
        int minReserve;

        Denom(int val, int total, int minReserve) {
            this.val = val;
            this.total = total;
            this.minReserve = minReserve;
        }

        @Override
        public int compareTo(Denom other) {
            // Sort descending by face value for lexicographic tie-breaking
            return Integer.compare(other.val, this.val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = readNonEmptyLine(br);
        if (line == null) {
            return;
        }

        int N = Integer.parseInt(line.trim());
        Denom[] denoms = new Denom[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(readNonEmptyLine(br));
            int d = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            denoms[i] = new Denom(d, q, r);
        }

        Arrays.sort(denoms);

        Map<Integer, Integer> valToIdx = new HashMap<>();
        for (int i = 0; i < N; i++) {
            valToIdx.put(denoms[i].val, i);
        }

        // Tracks currently reserved notes per transaction ID: txId -> int[N]
        Map<String, int[]> reservations = new HashMap<>();
        int[] totalReserved = new int[N];

        int K = Integer.parseInt(readNonEmptyLine(br).trim());
        for (int t = 0; t < K; t++) {
            String txLine = readNonEmptyLine(br);
            if (txLine == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(txLine);
            String type = st.nextToken();
            String txId = st.nextToken();

            if ("RESERVE".equals(type)) {
                int[] req = new int[N];
                boolean valid = true;

                while (st.hasMoreTokens()) {
                    int d = Integer.parseInt(st.nextToken());
                    int qty = Integer.parseInt(st.nextToken());
                    Integer idx = valToIdx.get(d);
                    if (idx == null) {
                        valid = false;
                        break;
                    }
                    req[idx] += qty;
                }

                if (valid) {
                    for (int i = 0; i < N; i++) {
                        int unreserved = denoms[i].total - denoms[i].minReserve - totalReserved[i];
                        if (req[i] > unreserved) {
                            valid = false;
                            break;
                        }
                    }
                }

                if (valid) {
                    int[] txRes = reservations.get(txId);
                    if (txRes == null) {
                        txRes = new int[N];
                        reservations.put(txId, txRes);
                    }
                    for (int i = 0; i < N; i++) {
                        txRes[i] += req[i];
                        totalReserved[i] += req[i];
                    }
                }

            } else if ("RELEASE".equals(type)) {
                int[] txRes = reservations.remove(txId);
                if (txRes != null) {
                    for (int i = 0; i < N; i++) {
                        totalReserved[i] -= txRes[i];
                    }
                }

            } else if ("WITHDRAW".equals(type)) {
                int amount = Integer.parseInt(st.nextToken());
                int maxNotes = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                boolean[] allowed = new boolean[N];
                for (int j = 0; j < m; j++) {
                    int d = Integer.parseInt(st.nextToken());
                    Integer idx = valToIdx.get(d);
                    if (idx != null) {
                        allowed[idx] = true;
                    }
                }

                int[] txRes = reservations.get(txId);
                int[] avail = new int[N];
                for (int i = 0; i < N; i++) {
                    if (!allowed[i]) {
                        avail[i] = 0;
                    } else {
                        int reservedByOthers = totalReserved[i] - (txRes != null ? txRes[i] : 0);
                        int canUse = denoms[i].total - denoms[i].minReserve - reservedByOthers;
                        avail[i] = Math.min(canUse, Math.min(maxNotes, amount / denoms[i].val));
                    }
                }

                // Bounded knapsack from smallest to largest denomination:
                // Using <= when moving toward larger denominations guarantees lexicographic preference
                int[] dp = new int[amount + 1];
                Arrays.fill(dp, INF);
                dp[0] = 0;
                int[][] choice = new int[N][amount + 1];

                for (int i = N - 1; i >= 0; i--) {
                    int val = denoms[i].val;
                    int limit = avail[i];
                    if (limit <= 0) {
                        continue;
                    }
                    for (int a = amount; a >= val; a--) {
                        int maxK = Math.min(limit, a / val);
                        for (int k = 1; k <= maxK; k++) {
                            int prev = dp[a - k * val];
                            if (prev + k <= maxNotes && prev + k <= dp[a]) {
                                dp[a] = prev + k;
                                choice[i][a] = k;
                            }
                        }
                    }
                }

                if (dp[amount] <= maxNotes) {
                    int rem = amount;
                    for (int i = 0; i < N; i++) {
                        int used = choice[i][rem];
                        if (used > 0) {
                            denoms[i].total -= used;
                            rem -= used * denoms[i].val;
                        }
                    }
                    // Clear all reservations under txId once WITHDRAW succeeds
                    int[] removed = reservations.remove(txId);
                    if (removed != null) {
                        for (int i = 0; i < N; i++) {
                            totalReserved[i] -= removed[i];
                        }
                    }
                }
            }
        }

        long remainingValue = 0;
        for (int i = 0; i < N; i++) {
            remainingValue += (long) denoms[i].total * denoms[i].val;
        }
        System.out.println(remainingValue);
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
