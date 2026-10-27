package main.java.vector;

public interface Vector<V extends Vector<V>> {
    V add(V other);
    V scale(double scalar);
    V invert();

    double length();
    double lengthSquared();
    V normalize();

    double dot(V other);
}
