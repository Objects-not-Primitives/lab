package org.objectsNotPrimitives.lab;

public class Surface {
    private int id;
    private double radius;
    private double d;
    private double n;

    public Surface(int id, double radius, double d, double n) {
        this.id = id;
        this.radius = radius;
        this.d = d;
        this.n = n;
    }

    public FXSurface surfaceToFX(int id, double radius, double d, double n){
        return new FXSurface(id,radius,d,n);
    }

    public Surface() {
    }

    public int getId() {
        return id;
    }

    public double getRadius() {
        return radius;
    }

    public double getD() {
        return d;
    }

    public double getN() {
        return n;
    }
}
