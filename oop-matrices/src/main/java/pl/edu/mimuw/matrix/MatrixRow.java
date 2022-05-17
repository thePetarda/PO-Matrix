package pl.edu.mimuw.matrix;

public class MatrixRow extends Matrix implements IDoubleMatrix{
    public double[] numb;

    MatrixRow(Shape shape, double... RowValues){
        super(shape);
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = RowValues[i];
        }
    }

    MatrixRow(double... RowValues){
        super(new Shape(RowValues.length, RowValues.length));
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = RowValues[i];
        }
    }

    @Override
    public IDoubleMatrix times(MatrixConstant other) {
        assert shape.columns == other.shape().rows;
        double[] ans = new double[shape.rows];
        for(int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, 0) * other.get(0,0)* shape.rows;
        }
        return new MatrixRow(new Shape(shape.rows, other.shape().columns), ans);
    }

    public IDoubleMatrix times(MatrixRow other) {
        assert shape.columns == other.shape().rows;
        double sum = 0;
        for(int i = 0; i < other.shape().rows; i++){
            sum += other.get(i, 0);
        }
        double[] ans = new double[shape.rows];
        for(int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, 0) * sum;
        }
        return new MatrixRow(new Shape(shape.rows, other.shape().columns), ans);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        return super.times(other);
    }

    public IDoubleMatrix plus(MatrixRow other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, 0) + other.get(i, 0);
        }
        return new MatrixRow(ans);
    }

    public IDoubleMatrix plus(MatrixConstant other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, 0) + other.get(0, 0);
        }
        return new MatrixRow(ans);
    }

    @Override
    public IDoubleMatrix plus(MatrixZero other) {
        return super.plus(other);
    }

    public IDoubleMatrix minus(MatrixRow other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, 0) - other.get(i, 0);
        }
        return new MatrixRow(ans);
    }

    public IDoubleMatrix minus(MatrixConstant other){
        assert shape.equals(other.shape());
        double[] ans = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            ans[i] = this.get(i, 0) - other.get(0, 0);
        }
        return new MatrixRow(ans);
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
        return numb[row];
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

    public double normInfinity(){
        double maxim = this.get(0,0);
        for (double v : numb) {
            if (Math.abs(v) > maxim) maxim = Math.abs(v);
        }
        return maxim;
    }

    public double normOne(){
        double sum = this.get(0,0);
        for (int i = 1; i < numb.length; i++){
            sum += Math.abs(numb[i]);
        }
        return sum;
    }

    public double frobeniusNorm(){
        double sum = this.get(0,0);
        for (double v : numb) {
            sum += Math.abs(v) * Math.abs(v) * shape.columns;
        }
        return Math.sqrt(sum);
    }
}
