package org.objectsNotPrimitives.lab;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class FXSurface {
    private IntegerProperty id;
    private DoubleProperty radius;
    private DoubleProperty d;
    private DoubleProperty n;

    public FXSurface(int id, double radius, double d, double n) {
        this.id = new SimpleIntegerProperty(id);
        this.radius = new SimpleDoubleProperty(radius);
        this.d = new SimpleDoubleProperty(d);
        this.n = new SimpleDoubleProperty(n);
    }

    public FXSurface() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public double getRadius() {
        return radius.get();
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }

    public double getD() {
        return d.get();
    }

    public DoubleProperty dProperty() {
        return d;
    }

    public double getN() {
        return n.get();
    }

    public DoubleProperty nProperty() {
        return n;
    }
}