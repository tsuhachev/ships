import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    private static final int X_LENGTH = 6;
    private static final String DOT = ".";
    private static final String MISS = "O";
    private static final String MATCH = "X";
    private static final String[] Y_CHARS = {"A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {

        final Point hit = new Point(1, "A"); // succeeding attempt
        final Point hitAtLastChar = new Point(5, "E");

        final List<Point> ships = Arrays.asList(
            hit,
            hitAtLastChar,
            new Point(5, "C"));

        final Point miss = new Point(1, "B"); // failing attempt
        final Point missAtLastCharacter = new Point(5, "D"); // failing attempt

        final long start1 = System.currentTimeMillis();
        System.out.println("===== A hit =======");
        play(hit, ships);
        final long end1 = System.currentTimeMillis();
        System.out.println("Took: " + (end1 - start1));

        final long start2 = System.currentTimeMillis();
        System.out.println("===== A hit (functional style) =======");
        playFunctionalStyle(hit, ships);
        final long end2 = System.currentTimeMillis();
        System.out.println("Took: " + (end2 - start2));

        System.out.println("===== A miss =======");
        play(miss, ships);
        System.out.println("===== A miss (functional style) =======");
        playFunctionalStyle(miss, ships);
        System.out.println("===== A hit at last char =======");
        play(hitAtLastChar, ships);
        System.out.println("===== A miss at last char =======");
        play(missAtLastCharacter, ships);
    }

    private static void playFunctionalStyle(Point hitAttempt, List<Point> playerShips) {
        final HashMap<Point, String> attemptStore = new HashMap<>();

        streamOfAllPoints()
            .parallel()
            .filter(hitAttempt::equals)
            .findAny()
            .ifPresentOrElse(p -> attemptStore.put(p, playerShips.contains(p) ? MATCH : MISS),
                () -> System.out.println("Hit attempt is out of playground"));

        printMatrix(attemptStore);
    }

    private static void printMatrix(HashMap<Point, String> attemptStore) {
        streamOfAllPoints()
            .sorted(Comparator
                .comparing(Point::getY)
                .thenComparingInt(Point::getX))
            .forEach(point -> {
                    System.out.print(attemptStore.getOrDefault(point, DOT));
                    newLine(point);
                }
            );
    }

    private static void newLine(Point point) {
        if (point.getX() == X_LENGTH - 1) {
            System.out.println();
        }
    }

    private static void play(Point hitAttempt, List<Point> playerShips) {
        for (String yChar : Y_CHARS) {
            for (int i = 0; i < X_LENGTH; i++) {
                final Point currentPoint = new Point(i, yChar);
                if (hitAttempt.equals(currentPoint)) {
                    if (playerShips.contains(hitAttempt)) {
                        System.out.print(MATCH);
                    } else {
                        System.out.print(MISS);
                    }
                } else {
                    System.out.print(DOT);
                }
            }
            System.out.println(); // new line
        }
    }

    private static Stream<Point> streamOfAllPoints() {
        return Stream.of(Y_CHARS).flatMap(y -> Stream.iterate(0, n -> n + 1)
            .limit(X_LENGTH).map(x -> new Point(x, y)));
    }

}
