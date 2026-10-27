package main.java.vector;


public class IntVector2 {
    public final int x, y;

    public IntVector2 (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final static IntVector2 ZERO =  new IntVector2(0, 0);
    public final static IntVector2 ONE = new IntVector2(1, 1);
    public static IntVector2 at(int x, int y) { return new IntVector2(x, y);}

    public IntVector2(IntVector2 v) { this(v.x, v.y); }
    public IntVector2 copy() {
        return new IntVector2(this);
    }

}
