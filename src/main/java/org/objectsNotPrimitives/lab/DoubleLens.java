package org.objectsNotPrimitives.lab;

public class DoubleLens {
    Surface s1;
    Surface s2;
    Surface s3;

    public Surface getS1() {
        return s1;
    }

    public Surface getS2() {
        return s2;
    }

    public Surface getS3() {
        return s3;
    }

    public DoubleLens(Surface s1, Surface s2, Surface s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }
}
