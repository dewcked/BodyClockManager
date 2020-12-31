package com.example.capston_project;

public class AHP {
    public static double[][] initialize_matrix(double[] p) {
        //initialize the matrix a
        double a[][] = new double[p.length][p.length];
        int k = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p.length; j++) {
                if (i == j)
                    a[i][j] = 1;
                else if (i < j) {

                    a[i][j] = p[k];
                    k++;
                } else if (i > j)
                    a[i][j] = 1 / a[j][i];
            }
        }
        return a;
    }
}