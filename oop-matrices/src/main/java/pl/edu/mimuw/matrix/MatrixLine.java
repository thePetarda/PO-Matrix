package pl.edu.mimuw.matrix;

public abstract class MatrixLine extends Matrix implements IDoubleMatrix{
    public double[] numb;

    public MatrixLine(double... Values){
        super(new Shape(Values.length, Values.length));
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = Values[i];
        }
    }

    public MatrixLine(int size, int rows, int columns){
        super(new Shape(rows, columns));
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = 1;
        }
    }

    public MatrixLine(int size){
        super(new Shape(size, size));
        numb = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++){
            numb[i] = 1;
        }
    }

//    public IDoubleMatrix plus(double scalar){
//        if (scalar == 0){
//            return this;
//        }
////        double[][] data = new double[shape.rows][shape().columns];
//        double[][] data = this.data();
//        MatrixCellValue[] ans = new MatrixCellValue[shape.columns * shape.rows];
//        for (int i = 0; i < shape.rows; i++){
//            for (int j = 0; j < shape.columns; j++){
//                ans[i * shape.rows + j] = new MatrixCellValue(i, j,this.get(i, j) + scalar);
//            }
//        }
//        return new MatrixSparse(shape, scalar, ans);
//    }
//
//    public IDoubleMatrix minus(double scalar){
//        if (scalar == 0){
//            return this;
//        }
////        double[][] data = new double[shape.rows][shape().columns];
//        double[][] data = this.data();
//        MatrixCellValue[] ans = new MatrixCellValue[shape.columns * shape.rows];
//        for (int i = 0; i < shape.rows; i++){
//            for (int j = 0; j < shape.columns; j++){
//                ans[i * shape.rows + j] = new MatrixCellValue(i, j,this.get(i, j) - scalar);
//            }
//        }
//        return new MatrixSparse(shape, scalar, ans);
//    }

    @Override
    public double normOne() {
        double maxim = 0;
        for (int i = 1; i < numb.length; i++){
            if (numb[i] > maxim) maxim = numb[i];
        }
        return maxim;
    }

    @Override
    public double normInfinity(){
        return this.normOne();
    }

    @Override
    public double frobeniusNorm(){
        double ans = 0;
        for (int i = 0; i < numb.length; i++){
            ans += numb[0] * numb[0];
        }
        return Math.sqrt(ans);
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

    @Override
    public double get(int row, int column) {
        assert row < shape.rows;
        assert column < shape.columns;
        assert row >= 0;
        assert column >= 0;
        return 0;
    }
}
