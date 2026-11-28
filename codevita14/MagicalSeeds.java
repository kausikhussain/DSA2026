import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;

public class MagicalSeeds {
    private static final int INF = 1_000_000_000;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static int rows;
    private static int columns;
    private static int cellCount;
    private static char[] map;
    private static boolean[] ordinaryPath;
    private static boolean[] plantable;
    private static int[] ordinaryComponent;
    private static SeedArea[] seedAreas;

    private static final class SeedArea {
        final int[] cells;
        final int[] region;
        final int regionCount;

        SeedArea(int[] cells, int[] region, int regionCount) {
            this.cells = cells;
            this.region = region;
            this.regionCount = regionCount;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();
        rows = input.nextInt();
        if (rows == Integer.MIN_VALUE) {
            return;
        }
        columns = input.nextInt();

        if (rows <= 0 || columns <= 0) {
            System.out.println(-1);
            return;
        }

        cellCount = rows * columns;
        map = readMap(input, cellCount);
        if (map == null) {
            System.out.println(-1);
            return;
        }

        int start = -1;
        int destination = -1;
        ordinaryPath = new boolean[cellCount];
        plantable = new boolean[cellCount];

        for (int cell = 0; cell < cellCount; cell++) {
            char tile = map[cell];
            if (tile == 'S') {
                start = cell;
            } else if (tile == 'D') {
                destination = cell;
            }

            ordinaryPath[cell] = tile == 'S' || tile == 'L' || tile == 'D';
            plantable[cell] = tile != '#' && tile != 'D';
        }

        if (start == -1 || destination == -1) {
            System.out.println(-1);
            return;
        }

        ordinaryComponent = findOrdinaryComponents();
        seedAreas = new SeedArea[cellCount];
        for (int cell = 0; cell < cellCount; cell++) {
            if (plantable[cell]) {
                seedAreas[cell] = buildSeedArea(cell);
            }
        }

        System.out.println(minimumSeeds(start, destination));
    }

    private static char[] readMap(FastInput input, int expectedCells) throws IOException {
        char[] result = new char[expectedCells];
        int used = 0;

        while (used < expectedCells) {
            String token = input.next();
            if (token == null) {
                return null;
            }

            for (int i = 0; i < token.length() && used < expectedCells; i++) {
                result[used++] = token.charAt(i);
            }
        }
        return result;
    }

    private static int[] findOrdinaryComponents() {
        int[] component = new int[cellCount];
        int[] queue = new int[cellCount];
        int numberOfComponents = 0;

        for (int first = 0; first < cellCount; first++) {
            if (!ordinaryPath[first] || component[first] != 0) {
                continue;
            }

            numberOfComponents++;
            int head = 0;
            int tail = 0;
            queue[tail++] = first;
            component[first] = numberOfComponents;

            while (head < tail) {
                int current = queue[head++];
                int row = current / columns;
                int column = current % columns;

                for (int direction = 0; direction < 4; direction++) {
                    int nextRow = row + DR[direction];
                    int nextColumn = column + DC[direction];
                    if (!inside(nextRow, nextColumn)) {
                        continue;
                    }

                    int next = id(nextRow, nextColumn);
                    if (ordinaryPath[next] && component[next] == 0) {
                        component[next] = numberOfComponents;
                        queue[tail++] = next;
                    }
                }
            }
        }
        return component;
    }

    /*
     * A seed covers a Manhattan-distance-two diamond.  Walls split the diamond
     * into separate regions; an existing L/S/D path can join two regions.
     */
    private static SeedArea buildSeedArea(int centre) {
        int centreRow = centre / columns;
        int centreColumn = centre % columns;
        int[] nearby = new int[13];
        int nearbyCount = 0;

        for (int rowOffset = -2; rowOffset <= 2; rowOffset++) {
            int maxColumnOffset = 2 - Math.abs(rowOffset);
            for (int columnOffset = -maxColumnOffset;
                 columnOffset <= maxColumnOffset;
                 columnOffset++) {
                int row = centreRow + rowOffset;
                int column = centreColumn + columnOffset;
                if (inside(row, column)) {
                    int cell = id(row, column);
                    if (map[cell] != '#') {
                        nearby[nearbyCount++] = cell;
                    }
                }
            }
        }

        int[] cells = Arrays.copyOf(nearby, nearbyCount);
        int[] parent = new int[nearbyCount];
        for (int i = 0; i < nearbyCount; i++) {
            parent[i] = i;
        }

        // Adjacent covered cells are immediately connected.
        for (int left = 0; left < nearbyCount; left++) {
            for (int right = left + 1; right < nearbyCount; right++) {
                if (manhattan(cells[left], cells[right]) == 1) {
                    unite(parent, left, right);
                }
            }
        }

        // Covered cells touching the same original walkable component connect too.
        int[] seenComponents = new int[nearbyCount * 5];
        int[] firstCellForComponent = new int[nearbyCount * 5];
        int seenCount = 0;

        for (int index = 0; index < nearbyCount; index++) {
            int row = cells[index] / columns;
            int column = cells[index] % columns;

            for (int direction = -1; direction < 4; direction++) {
                int nextRow = row;
                int nextColumn = column;
                if (direction >= 0) {
                    nextRow += DR[direction];
                    nextColumn += DC[direction];
                }

                if (!inside(nextRow, nextColumn)) {
                    continue;
                }

                int component = ordinaryComponent[id(nextRow, nextColumn)];
                if (component == 0) {
                    continue;
                }

                int knownAt = -1;
                for (int k = 0; k < seenCount; k++) {
                    if (seenComponents[k] == component) {
                        knownAt = k;
                        break;
                    }
                }

                if (knownAt == -1) {
                    seenComponents[seenCount] = component;
                    firstCellForComponent[seenCount] = index;
                    seenCount++;
                } else {
                    unite(parent, index, firstCellForComponent[knownAt]);
                }
            }
        }

        int[] region = new int[nearbyCount];
        int[] roots = new int[nearbyCount];
        Arrays.fill(roots, -1);
        int regionCount = 0;

        for (int index = 0; index < nearbyCount; index++) {
            int root = find(parent, index);
            int assignedRegion = -1;
            for (int k = 0; k < regionCount; k++) {
                if (roots[k] == root) {
                    assignedRegion = k;
                    break;
                }
            }

            if (assignedRegion == -1) {
                roots[regionCount] = root;
                assignedRegion = regionCount++;
            }
            region[index] = assignedRegion;
        }

        return new SeedArea(cells, region, regionCount);
    }

    private static int minimumSeeds(int start, int destination) {
        int[] distance = new int[cellCount];
        Arrays.fill(distance, INF);
        boolean[] settled = new boolean[cellCount];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        distance[start] = 0;
        deque.addFirst(start);

        while (!deque.isEmpty()) {
            int current = deque.removeFirst();
            if (settled[current]) {
                continue;
            }
            settled[current] = true;

            if (current == destination) {
                return distance[current];
            }

            int row = current / columns;
            int column = current % columns;

            // Moving on an existing path has no seed cost.
            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DR[direction];
                int nextColumn = column + DC[direction];
                if (!inside(nextRow, nextColumn)) {
                    continue;
                }

                int next = id(nextRow, nextColumn);
                if (ordinaryPath[next] && distance[current] < distance[next]) {
                    distance[next] = distance[current];
                    deque.addFirst(next);
                }
            }

            /*
             * A seed is usable when its radius-two diamond touches the current
             * location.  Therefore its centre can be at Manhattan distance three.
             */
            for (int rowOffset = -3; rowOffset <= 3; rowOffset++) {
                int maxColumnOffset = 3 - Math.abs(rowOffset);
                for (int columnOffset = -maxColumnOffset;
                     columnOffset <= maxColumnOffset;
                     columnOffset++) {
                    int seedRow = row + rowOffset;
                    int seedColumn = column + columnOffset;
                    if (!inside(seedRow, seedColumn)) {
                        continue;
                    }

                    SeedArea area = seedAreas[id(seedRow, seedColumn)];
                    if (area == null) {
                        continue;
                    }

                    boolean[] reachableRegion = new boolean[area.regionCount];
                    for (int i = 0; i < area.cells.length; i++) {
                        if (manhattan(current, area.cells[i]) <= 1) {
                            reachableRegion[area.region[i]] = true;
                        }
                    }

                    for (int region = 0; region < area.regionCount; region++) {
                        if (!reachableRegion[region]) {
                            continue;
                        }

                        for (int i = 0; i < area.cells.length; i++) {
                            if (area.region[i] != region) {
                                continue;
                            }

                            int next = area.cells[i];
                            if (distance[current] + 1 < distance[next]) {
                                distance[next] = distance[current] + 1;
                                deque.addLast(next);
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static int find(int[] parent, int value) {
        while (parent[value] != value) {
            parent[value] = parent[parent[value]];
            value = parent[value];
        }
        return value;
    }

    private static void unite(int[] parent, int left, int right) {
        int leftRoot = find(parent, left);
        int rightRoot = find(parent, right);
        if (leftRoot != rightRoot) {
            parent[rightRoot] = leftRoot;
        }
    }

    private static int manhattan(int first, int second) {
        return Math.abs(first / columns - second / columns)
                + Math.abs(first % columns - second % columns);
    }

    private static boolean inside(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    private static int id(int row, int column) {
        return row * columns + column;
    }

    private static final class FastInput {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int pointer;
        private int length;

        int nextInt() throws IOException {
            String value = next();
            return value == null ? Integer.MIN_VALUE : Integer.parseInt(value);
        }

        String next() throws IOException {
            StringBuilder value = new StringBuilder();
            int character;

            do {
                character = read();
            } while (character <= ' ' && character != -1);

            if (character == -1) {
                return null;
            }

            while (character > ' ') {
                value.append((char) character);
                character = read();
            }
            return value.toString();
        }

        private int read() throws IOException {
            if (pointer >= length) {
                length = in.read(buffer);
                pointer = 0;
                if (length <= 0) {
                    return -1;
                }
            }
            return buffer[pointer++];
        }
    }
}

