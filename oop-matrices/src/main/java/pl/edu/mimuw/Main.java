package pl.edu.mimuw;

import pl.edu.mimuw.matrix.*;

import static pl.edu.mimuw.matrix.DoubleMatrixFactory.full;

public class Main {

  public static void main(String[] args) {
    assert 1 == 2 : "1==2";
    // Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
    Shape s = new Shape(2, 2);
    MatrixCellValue a = new MatrixCellValue(1, 1, 4);
    MatrixCellValue b = new MatrixCellValue(1, 2, 5);
    MatrixCellValue c = new MatrixCellValue(2, 1, 6);
    MatrixCellValue d = new MatrixCellValue(2, 2, 7);
//    MatrixSparse m = new MatrixSparse(s, a, b, c, d);
    double[][] val = new double[2][2];
    val[0][0] = 4;
    val[0][1] = 5;
    val[1][0] = 6;
    val[1][1] = 7;
    MatrixFull m = new MatrixFull(val);
//    System.out.println(m.get(0,0));
    System.out.println(m.toString());
    double[][] chuj = new double[][]{};
    System.out.println(chuj.length);

    assert 1 == -1;
//    m.get(0, -1);
    MatrixFull p = (MatrixFull) full(new double[][]{new double[]{1, 2, 3}, new double[]{4, 5, 6}});
    System.out.println(p.toString());
    System.out.println(p.get(1,1));
    System.out.println(p.get(-1,1));
  }
}
