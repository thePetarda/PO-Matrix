package pl.edu.mimuw.matrix;

public class MatrixDiagonal extends MatrixLine implements IDoubleMatrix{
    public MatrixDiagonal(double... diagonalValues){
        super(diagonalValues);
    }

    public MatrixDiagonal(int size){
        super(size);
    }

    public IDoubleMatrix times(MatrixAntidiagonal other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows ; i++){
            ans[i] = get(i, i) * other.get(shape.rows - i, i);
        }
        return new MatrixAntidiagonal(ans);
    }

    public IDoubleMatrix times(MatrixDiagonal other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows ; i++){
            ans[i] = get(i, i) * other.get(i, i);
        }
        return new MatrixDiagonal(ans);
    }

    public IDoubleMatrix times(MatrixRow other){
        assert shape.columns == other.shape().rows;
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows ; i++){
            ans[i] = get(i, i) * other.get(i, i);
        }
        return new MatrixRow(ans);
    }

    public IDoubleMatrix times(MatrixColumn other){
        assert shape.columns == other.shape().rows;
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows ; i++){
            ans[i] = get(i, i) * other.get(i, i);
        }
        return new MatrixColumn(ans);
    }

    public IDoubleMatrix times(MatrixConstant other){
        assert shape.columns == other.shape().rows;
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows ; i++){
            ans[i] = get(i, i) * other.get(i, i);
        }
        return new MatrixRow(ans);
    }

    public IDoubleMatrix times(double scalar){
        if (scalar == 0){
            return new MatrixZero(shape);
        }
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = scalar * numb[i];
        }
        return new MatrixDiagonal(ans);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        return super.times(other);
    }

    public IDoubleMatrix plus(MatrixDiagonal other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, i) + other.get(i, i);
        }
        return new MatrixDiagonal(ans);
    }

    @Override
    public IDoubleMatrix plus(MatrixZero other) {
        return super.plus(other);
    }

    public IDoubleMatrix minus(MatrixDiagonal other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, i) - other.get(i, i);
        }
        return new MatrixDiagonal(ans);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        return super.minus(other);
    }

    @Override
    public double get(int row, int column) {
        assert row < shape.rows;
        assert column < shape.columns;
        assert row >= 0;
        assert column >= 0;
        if (row == column) return numb[row];
        return 0;
    }
}
