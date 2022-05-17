package pl.edu.mimuw.matrix;

public class MatrixConstant extends Matrix implements IDoubleMatrix{
    private final double numb;

    public MatrixConstant(Shape shape, double c){
        super(shape);
        numb = c;
    }

    @Override
    public IDoubleMatrix times(MatrixConstant other) {
        assert this.shape.equals(other.shape);
        return new MatrixConstant(this.shape, this.numb * other.get(0,0) * shape.rows);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        return super.times(other);
    }

    @Override
    public double get(int row, int column) {
        assert row <= shape.rows;
        assert column <= shape.columns;
        assert row >= 0;
        assert column >= 0;
        return numb;
    }

    @Override
    public double[][] data() {
        double[][] ans = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++){
            for (int j = 0; j < shape.columns; j++){
                ans[i][j] = numb;
            }
        }
        return ans;
    }

    public IDoubleMatrix plus(MatrixConstant other){
        assert shape.equals(other.shape());
        return new MatrixConstant(this.shape(), this.numb + other.get(0,0));
    }

    public IDoubleMatrix plus(MatrixRow other){
        return other.plus(this);
    }

    public IDoubleMatrix plus(MatrixColumn other){
        return other.plus(this);
    }

    public IDoubleMatrix plus(MatrixLine other){
        return other.plus(this);
    }

    @Override
    public IDoubleMatrix plus(MatrixZero other) {
        return super.plus(other);
    }

    public IDoubleMatrix minus(MatrixConstant other){
        assert shape.equals(other.shape());
        return new MatrixConstant(this.shape(), this.numb - other.get(0,0));
    }

    public IDoubleMatrix minus(MatrixRow other){
        return other.minus(this);
    }

    public IDoubleMatrix minus(MatrixColumn other){
        return other.minus(this);
    }

    public IDoubleMatrix minus(MatrixLine other){
        return other.minus(this);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        return super.minus(other);
    }

    @Override
    public double normOne() {
        return shape.rows * Math.abs(numb);
    }

    @Override
    public double normInfinity() {
        return shape.rows * Math.abs(numb);
    }

    @Override
    public double frobeniusNorm() {
        return Math.sqrt(shape.rows * shape.columns * numb * numb);
    }
}
