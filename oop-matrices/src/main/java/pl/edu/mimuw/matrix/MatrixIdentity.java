package pl.edu.mimuw.matrix;

public class MatrixIdentity extends MatrixDiagonal implements IDoubleMatrix{
    public MatrixIdentity(int size){
        super(size);
    }

    public IDoubleMatrix times(Matrix other){
        return other;
    }

    @Override
    public double get(int row, int column) {
        assert row < shape.rows;
        assert column < shape.columns;
        assert row >= 0;
        assert column >= 0;
        if (row == column) return 1;
        return 0;
    }
}
