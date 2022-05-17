package pl.edu.mimuw.matrix;

public abstract class Matrix implements IDoubleMatrix{
    Shape shape;

    public Matrix(Shape shape){
        assert shape.rows > 0;
        assert shape.columns > 0;
        this.shape = shape;
    }

    public Matrix(double[][] values){
        assert values.length > 0;
        assert values[0].length > 0;
        this.shape = new Shape(values.length, values[0].length);
    }

    @Override
    public Shape shape(){
        return shape;
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
//        if (shape.columns != other.shape().rows){
//            return null;
//        }
        assert shape.rows == other.shape().columns;
        double[][] ans = new double[shape.rows][other.shape().columns];
        for (int i = 0; i < shape.rows; i++){
            for (int j = 0; j < other.shape().columns; j++){
                double el = 0;
                for (int p = 0; p < shape.columns; p++){
                    el += this.get(i, p) * other.get(p, j);
                }
                ans[j][i] = el;
            }
        }
        return new MatrixFull(ans);
    }

    public IDoubleMatrix times(MatrixZero other) {
        assert shape.rows == other.shape().columns;
        return other;
    }

    public IDoubleMatrix times(MatrixConstant other) {
//        if (shape.columns != other.shape().rows){
//            return null;
//        }
        assert shape.rows == other.shape().columns;
        double c = other.get(0, 0);
        double[] ans = new double [shape.rows];
        for (int i = 0; i < shape.rows; i++){
            double sumRow = 0;
            for (int j = 0; j < shape.columns; j++){
                sumRow += this.get(i, j);
            }
            ans[i] = sumRow * c;
        }
        return new MatrixRow(ans);
    }

    public IDoubleMatrix times(MatrixIdentity other) {
        assert shape.rows == other.shape().columns;
        return this;
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        if (scalar == 0){
            return new MatrixZero(this.shape());
        }
        double[][] ans = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[j][i] = this.get(j, i) * scalar;
            }
        }
        return new MatrixFull(ans);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
//        if (!shape.equals(other.shape())){
//            return null;
//        }
        assert shape.equals(other.shape());
        double[][] ans = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[j][i] = this.get(j, i) + other.get(j, i);
            }
        }
        return new MatrixFull(ans);
    }

    public IDoubleMatrix plus(MatrixZero other){
        return this;
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        assert shape.rows == shape.columns;
        if (scalar == 0) {
            return this;
        }
        MatrixIdentity m2 = new MatrixIdentity(shape.rows);
        return this.plus(m2);
    }
//    @Override
//    public IDoubleMatrix plus(double scalar) {
//        if (scalar == 0){
//            return this;
//        }
//        double[][] ans = new double[shape.rows][shape.columns];
//        for (int i = 0; i < shape.columns; i++){
//            for (int j = 0; j < shape.rows; j++){
//                ans[j][i] = this.get(j, i) + scalar;
//            }
//        }
//        return new MatrixFull(ans);
//    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
//        if (!shape.equals(other.shape())){
//            return null;
//        }
        assert shape.equals(other.shape());
        double[][] ans = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[j][i] = this.get(j, i) - other.get(j, i);
            }
        }
        return new MatrixFull(ans);
    }

    public IDoubleMatrix minus(MatrixZero other){
        return this;
    }

    @Override
    public IDoubleMatrix minus(double scalar) {
        assert shape.rows == shape.columns;
        if (scalar == 0) {
            return this;
        }
        MatrixIdentity m2 = new MatrixIdentity(shape.rows);
        return this.minus(m2);
    }

//    @Override
//    public IDoubleMatrix minus(double scalar) {
//        if (scalar == 0){
//            return this;
//        }
//        double[][] ans = new double[shape.rows][shape.columns];
//        for (int i = 0; i < shape.columns; i++){
//            for (int j = 0; j < shape.rows; j++){
//                ans[j][i] = this.get(j, i) - scalar;
//            }
//        }
//        return new MatrixFull(ans);
//    }

    @Override
    public double normOne() {
        double maxim = 0;
        double acc;
        for (int i = 0; i < shape.columns; i++){
            acc = 0;
            for (int j = 0; j < shape.rows; j++){
                acc += Math.abs(this.get(j, i));
            }
            if (acc > maxim) {maxim = acc;}
        }
        return maxim;
    }

    @Override
    public double normInfinity() {
        double maxim = 0;
        double acc;
        for (int i = 0; i < shape.rows; i++){
            acc = 0;
            for (int j = 0; j < shape.columns; j++){
                acc += Math.abs(this.get(i, j));
            }
            if (acc > maxim) {maxim = acc;}
        }
        return maxim;
    }

    @Override
    public double frobeniusNorm() {
        double acc = 0;
        for (int i = 0; i < shape.rows; i++){
            for (int j = 0; j < shape.columns; j++){
                acc += (this.get(i, j) * this.get(i, j));
            }
        }
        return Math.sqrt(acc);
    }

    @Override
    public String toString(){
        String ans = "";
        for (int i = 0; i < shape.rows; i++){
            for (int j = 0; j < shape.columns; j++){
                ans += this.get(i, j);
                ans += " ";
            }
            ans += "\n";
        }
        return ans;
    }

    @Override
    public double get(int row, int column) {
        assert row < shape.rows;
        assert column < shape.columns;
        assert row >= 0;
        assert column >= 0;
        return 0;
    }
}
