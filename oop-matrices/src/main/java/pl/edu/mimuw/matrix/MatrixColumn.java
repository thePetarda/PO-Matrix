package pl.edu.mimuw.matrix;

public class MatrixColumn extends Matrix implements IDoubleMatrix{
    public double[] numb;

    MatrixColumn(Shape shape, double... ColumnValues){
        super(shape);
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = ColumnValues[i];
        }
    }

    MatrixColumn(double... ColumnValues){
        super(new Shape(ColumnValues.length, ColumnValues.length));
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = ColumnValues[i];
        }
    }

    @Override
    public IDoubleMatrix times(MatrixConstant other) {
        assert shape.columns == other.shape().rows;
        double sum = 0;
        for(int i = 0; i < other.shape().columns; i++){
            sum += other.get(0, i);
        }
        return new MatrixConstant(new Shape(shape.rows, other.shape().columns), sum * other.get(0,0));
    }

    public IDoubleMatrix times(MatrixColumn other) {
        assert shape.columns == other.shape().rows;
        double sum = 0;
        for(int i = 0; i < other.shape().columns; i++){
            sum += other.get(0, i);
        }
        double[] ans = new double[other.shape().columns];
        for(int i = 0; i < other.shape().columns; i++){
            ans[i] = this.get(i, 0) * sum;
        }
        return new MatrixColumn(new Shape(shape.rows, other.shape().columns), ans);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        return super.times(other);
    }

    public IDoubleMatrix plus(MatrixColumn other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(0, i) + other.get(0, i);
        }
        return new MatrixColumn(shape, ans);
    }

    public IDoubleMatrix plus(MatrixConstant other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(0, i) + other.get(0, 0);
        }
        return new MatrixColumn(shape, ans);
    }

    @Override
    public IDoubleMatrix plus(MatrixZero other) {
        return super.plus(other);
    }

    public IDoubleMatrix minus(MatrixColumn other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(0, i) - other.get(0, i);
        }
        return new MatrixColumn(shape, ans);
    }

    public IDoubleMatrix minus(MatrixConstant other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(0, i) - other.get(0, 0);
        }
        return new MatrixColumn(shape, ans);
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
        return numb[column];
    }

    @Override
    public double[][] data() {
        double[][] ans = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++){
            for (int j = 0; j < shape.columns; j++){
                ans[i][j] = get(i, j);
            }
        }
        return ans;
    }

    public double normOne(){
        double maxim = this.get(0,0);
        for (double v : numb) {
            if (Math.abs(v) > maxim) maxim = Math.abs(v);
        }
        return maxim;
    }

    public double normInfinity(){
        double sum = this.get(0,0);
        for (int i = 1; i < numb.length; i++){
            sum += Math.abs(numb[i]);
        }
        return sum;
    }

    public double frobeniusNorm(){
        double sum = this.get(0,0);
        for (double v : numb) {
            sum += Math.abs(v) * Math.abs(v) * shape.rows;
        }
        return Math.sqrt(sum);
    }
}
