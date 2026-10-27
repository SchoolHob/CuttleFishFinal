package vector;

import java.util.Objects;

public class Vector3 implements Vector<Vector3> {
    public final double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(double x, double y) { this(x, y, 0);}


    public final static Vector3 ZERO =  new Vector3(0, 0, 0);
    public final static Vector3 ONE = new Vector3(1, 1, 1);
    public static Vector3 at(double x, double y, double z) { return new Vector3(x, y, z);}
    public static Vector3 at(double x, double y) { return new Vector3(x, y);}
    public static Vector3 at(int x, int y, int z) { return at((double)x, (double)y, (double)z);}
    public static Vector3 at(int x, int y) { return at((double)x, (double)y);}

    public int intx() {return (int) Math.round(x);}
    public int inty() {return (int) Math.round(y);}
    public int intz() {return (int) Math.round(z);}


    public Vector3(Vector3 v) { this(v.x, v.y, v.z); }
    public Vector3 copy() {
        return new Vector3(this);
    }

    @Override
    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    @Override
    public Vector3 scale(double scalar) {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    @Override
    public Vector3 invert() {
        return new Vector3(-x, -y, -z);
    }

    @Override
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    @Override
    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    @Override
    public Vector3 normalize() {
        double length = length();
        if (length == 0.0) { return ZERO; }
        return new Vector3(x / length, y / length,  z / length);
    }

    @Override
    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector3 other)) return false;

        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3(" + x + ", " + y + ", " + z + ")";
    }
}
