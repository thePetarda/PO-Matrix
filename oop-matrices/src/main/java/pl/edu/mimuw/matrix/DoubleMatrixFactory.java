package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values){
    return new MatrixSparse(shape, values); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix full(double[][] values) {
    return new MatrixFull(values); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix identity(int size) {
    return new MatrixIdentity(size); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    return new MatrixDiagonal(diagonalValues); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    return new MatrixAntidiagonal(antiDiagonalValues); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix vector(double... values){
    return new MatrixColumn(new Shape(values.length, 1), values); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix row(double... values){
    return new MatrixRow(values); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix column(double... values){
    return new MatrixColumn(values); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix constant(Shape shape, double c){
    return new MatrixConstant(shape, c); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix zero(Shape shape) {
    return new MatrixZero(shape); // Tu trzeba wpisać właściwą instrukcję
  }
}
