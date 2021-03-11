import java.util.Arrays;
import java.util.List;

public class Main {

    public static final String DOT = ".";
    public static final int X_LENGTH = 6;
    public static final String MISS = "O";
    public static final String MATCH = "X";
    public static final String[] Y_CHARS = {"A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {

        final Point hit = new Point(1, "A"); // succeeding attempt
        final Point hitAtLastChar = new Point(5, "E");

        final List<Point> ships = Arrays.asList(
            hit,
            hitAtLastChar,
            new Point(5, "C"));

        final Point miss = new Point(1, "B"); // failing attempt
        final Point missAtLastCharacter = new Point(5, "D"); // failing attempt

        System.out.println("===== A hit =======");
        play(hit, ships);
        System.out.println("===== A hit at last char =======");
        play(hitAtLastChar, ships);
        System.out.println("===== A miss =======");
        play(miss, ships);
        System.out.println("===== A miss at last char =======");
        play(missAtLastCharacter, ships);

    }

    private static void play(Point hitAttempt, List<Point> ships) {
        for (String yChar : Y_CHARS) {
            for (int i = 0; i < X_LENGTH; i++) {
                final boolean isLastDot = i == X_LENGTH - 1;
                final Point currentPoint = new Point(i, yChar);
                if (hitAttempt.equals(currentPoint)) {
                    printNewOrSameLine(isLastDot, ships.contains(hitAttempt) ? MATCH : MISS);
                } else {
                    printNewOrSameLine(isLastDot, DOT);
                }
            }
        }
    }

    private static void printNewOrSameLine(boolean isLastDot, String character) {
        if (isLastDot) {
            System.out.println(character);
        } else {
            System.out.print(character);
        }
    }
}
