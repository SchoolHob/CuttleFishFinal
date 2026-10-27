package main.java.vector;


public class Vector2 implements Vector<Vector2> {
    public final double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public final static Vector2 ZERO =  new Vector2(0, 0);
    public final static Vector2 ONE = new Vector2(1, 1);
    public static Vector2 at(double x, double y) { return new Vector2(x, y);}
    public static Vector2 at(int x, int y) { return Vector2.at((double)x, (double)y);}

    public Vector2(Vector2 v) { this(v.x, v.y); }
    public Vector2 copy() {
        return new Vector2(this);
    }

    public Vector3 toVector3() {
        return new Vector3(x, y, 0);
    }

    public int intx() {return (int) Math.round(x);}
    public int inty() {return (int) Math.round(y);}


    @Override
    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    @Override
    public Vector2 scale(double scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    @Override
    public Vector2 invert() {
        return new Vector2(-x, -y);
    }

    @Override
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    @Override
    public double lengthSquared() {
        return x * x + y * y;
    }

    @Override
    public Vector2 normalize() {
        double length = length();
        if (length == 0.0) { return ZERO; }
        return new Vector2(x / length, y / length);
    }

    @Override
    public double dot(Vector2 other) {
        return x * other.x + y * other.y;
    }
}
