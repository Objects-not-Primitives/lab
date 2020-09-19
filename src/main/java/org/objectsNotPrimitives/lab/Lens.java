package org.objectsNotPrimitives.lab;

public class Lens {
    Surface s1;
    Surface s2;

    public Surface getS1() {
        return s1;
    }

    public Surface getS2() {
        return s2;
    }

    public Lens(Surface s1, Surface s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
}
