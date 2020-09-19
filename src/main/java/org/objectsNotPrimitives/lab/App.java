package org.objectsNotPrimitives.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ejml.simple.SimpleMatrix;

import java.io.*;
import java.util.*;

public class App {
    private static final ObjectMapper mapper = new ObjectMapper();

    public void logicApp() {
        List<SimpleMatrix> matrixList = new ArrayList<>();

        List<Surface> surfaceList = readFile();

        //Общая логика, которая позволит делать вычисления для системы
        //вычисления матриц преломления и переноса

        for (int i = 0; i < surfaceList.size(); i = i + 2) {
            Surface surface1 = surfaceList.get(i);
            Surface surface2 = surfaceList.get(i + 1);
            //Условие для склейки и добавление 3 поверхности
            if (surface1.getN() != 1 && surface2.getN() != 1) {
                Surface surface3 = surfaceList.get(i + 2);
                DoubleLens doubleLens = new DoubleLens(surface1, surface2, surface3);
                matrixList.addAll(getDoubleLensMatrix(doubleLens));
                matrixList.add(getTransMatrix(surface3.getD(), surface3.getN()));
                i++;
            } else {
                matrixList.addAll(getLensMatrix(new Lens(surface1, surface2)));
                matrixList.add(getTransMatrix(surface2.getD(), surface2.getN()));
            }
        }

        //Обработка полученных результатов, перемножение матриц, вывод параксиальных характеристик

        Collections.reverse(matrixList);
        Optional<SimpleMatrix> g = matrixList.stream().reduce(SimpleMatrix::mult);
        Paraxial paraxial = getParaxial(g.get());

        System.out.println(paraxial);
    }

    //Считывание параметров, создание коллекции поверхностей системы
    public List<Surface> readFile() {
        List<Surface> surfaceList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/param.txt"))) {
            String s;
            while ((s = reader.readLine()) != null) {
                Optional<Surface> optionalSurface = jsonToSurface(s);
                Surface surface = optionalSurface.orElse(null);
                surfaceList.add(surface);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return surfaceList;
    }

    private static ArrayList<SimpleMatrix> getLensMatrix(Lens lens) {
        ArrayList<SimpleMatrix> list = new ArrayList<>();

        list.add(getRefractMatrixL(lens.getS1().getRadius(), lens.getS1().getN()));
        list.add(getTransMatrix(lens.getS1().getD(), lens.getS1().getN()));
        list.add(getRefractMatrixR(lens.getS2().getRadius(), lens.getS1().getN()));

        return list;
    }

    private static ArrayList<SimpleMatrix> getDoubleLensMatrix(DoubleLens lens) {
        ArrayList<SimpleMatrix> list = new ArrayList<>();

        list.add(getRefractMatrixL(lens.getS1().getRadius(), lens.getS1().getN()));
        list.add(getTransMatrix(lens.getS1().getD(), lens.getS1().getN()));
        list.add(getRefractMatrixCentral(lens.getS2().getRadius(), lens.getS1().getN(), lens.getS2().getN()));
        list.add(getTransMatrix(lens.getS2().getD(), lens.getS2().getN()));
        list.add(getRefractMatrixR(lens.getS3().getRadius(), lens.getS2().getN()));

        return list;
    }

    private static SimpleMatrix getRefractMatrixR(double rad2, double n) {
        if (rad2 != 0) {
            return new SimpleMatrix(new double[][]{{1, 0}, {((-1 / rad2)) * (1 - n), 1}});
        } else {
            return new SimpleMatrix(new double[][]{{1, 0}, {0, 1}});
        }
    }

    private static SimpleMatrix getRefractMatrixCentral(double rad3, double n1, double n2) {
        if (rad3 != 0) {
            return new SimpleMatrix(new double[][]{{1, 0}, {((-1 / rad3)) * (n2 - n1), 1}});
        } else {
            return new SimpleMatrix(new double[][]{{1, 0}, {0, 1}});
        }
    }

    private static SimpleMatrix getRefractMatrixL(double rad1, double n) {
        if (rad1 != 0) {
            return new SimpleMatrix(new double[][]{{1, 0}, {((-1 / rad1)) * (n - 1), 1}});
        } else {
            return new SimpleMatrix(new double[][]{{1, 0}, {0, 1}});
        }
    }

    private static SimpleMatrix getTransMatrix(double d, double n) {
        return new SimpleMatrix(new double[][]{{1, d / n}, {0, 1}});
    }

    private static Paraxial getParaxial(SimpleMatrix g) {
        double f = ((g.get(0, 0) * g.get(1, 1)) / g.get(1, 0)) - g.get(0, 1);
        double f_ = -1 / (g.get(1, 0));
        double sf = g.get(1, 1) / g.get(1, 0);
        double sf_ = -g.get(0, 0) / g.get(1, 0);
        double sh = sf - f;
        double sh_ = sf_ - f_;
        return new Paraxial(f, f_, sf, sf_, sh, sh_);
    }

    private static Optional<Surface> jsonToSurface(String jsonString) {
        try {
            return Optional.of(mapper.readValue(jsonString, Surface.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
