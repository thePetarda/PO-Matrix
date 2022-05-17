package pl.edu.mimuw.matrix;

public class MatrixZero extends MatrixConstant implements IDoubleMatrix{
    public MatrixZero(Shape shape) {
        super(shape, 0);
    }

    public IDoubleMatrix times(IDoubleMatrix other){
        return other.times(this);
    }

    public IDoubleMatrix plus(IDoubleMatrix other){
        return other.plus(this);
    }

    public IDoubleMatrix minus(IDoubleMatrix other){
        return other.minus(this);
    }

    public double get(int row, int column) {
        assert row <= shape.rows;
        assert column <= shape.columns;
        assert row >= 0;
        assert column >= 0;
        return 0;
    }
}
