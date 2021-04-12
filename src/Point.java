import java.util.Objects;

// this is hf branch 

public class Point {
    private int x;
    private String y;

    public Point(int x, String y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        // this is master later commit
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x &&
            Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
