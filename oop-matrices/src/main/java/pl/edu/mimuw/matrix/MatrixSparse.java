package pl.edu.mimuw.matrix;

import java.util.ArrayList;

public class MatrixSparse extends Matrix implements IDoubleMatrix{
    private Shape shape;
    private ArrayList<MatrixCellValue>[] numb;
    double val;

    public MatrixSparse(Shape shape, double val, MatrixCellValue... values){
        super(shape);
        this.val = val;
        numb = new ArrayList[shape.rows];
        for (int i = 0; i < shape.rows; i++) {
            ArrayList<MatrixCellValue> row = new ArrayList<MatrixCellValue>();
            for (int j = 0; j < shape.columns; j++){
                assert values[i * shape.columns + j].row >= 0;
                assert values[i * shape.columns + j].column >= 0;
//                assert values[i * shape.columns + j].row < 0;
//                assert values[i * shape.columns + j].column < 0;
                assert values[i * shape.columns + j].row < shape.rows;
                assert values[i * shape.columns + j].column < shape.columns;
                if ((values[i * shape.columns + j].value != val) && values[i * shape.columns + j].row == i) {
                    row.add(values[i * shape.columns + j]);
                }
            }
            numb[i] = row;
        }
    }

    public MatrixSparse(Shape shape, MatrixCellValue... values){
        super(shape);
        this.val = 0;
        numb = new ArrayList[shape.rows];
        for (int i = 0; i < shape.rows; i++) {
            ArrayList<MatrixCellValue> row = new ArrayList<MatrixCellValue>();
            for (int j = 0; j < shape.columns; j++){
                assert values[i * shape.columns + j].row >= 0;
                assert values[i * shape.columns + j].column >= 0;
                assert values[i * shape.columns + j].row < shape.rows;
                assert values[i * shape.columns + j].column < shape.columns;
                if ((values[i * shape.columns + j].value != val) && values[i * shape.columns + j].row == i) {
                    row.add(values[i * shape.columns + j]);
                }
            }
            numb[i] = row;
        }
    }

//    @Override
//    public IDoubleMatrix times(double scalar){
//        if (scalar == 0){
//            return new MatrixZero(shape);
//        }
//        if (scalar == 1){
//            return this;
//        }
//        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
//        for  (int i = 0; i < shape.rows; i++){
//            for (int j = 0; j < shape.columns; j++){
//                ans[i * shape.rows + j] = new MatrixCellValue(i, j,scalar * this.get(i, j));
//            }
//        }
//        return new MatrixSparse(this.shape, scalar * this.val, ans);
//    }

    public IDoubleMatrix plus(MatrixSparse other){
        assert shape.equals(other.shape());
        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[i * shape.columns + j]= new MatrixCellValue(j, i, this.get(j, i) + other.get(j, i));
            }
        }
        return new MatrixSparse(this.shape ,ans);
    }

    public IDoubleMatrix plus(MatrixLine other){
        assert shape.equals(other.shape());
        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[i * shape.columns + j]= new MatrixCellValue(j, i, this.get(j, i) + other.get(j, i));
            }
        }
        return new MatrixSparse(this.shape ,ans);
    }

    public IDoubleMatrix plus(IDoubleMatrix other){
        return super.plus(other);
    }

//    @Override
//    public IDoubleMatrix plus(double scalar){
//        if (scalar == 0){
//            return this;
//        }
//        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
//        for  (int i = 0; i < shape.rows; i++){
//            for (int j = 0; j < shape.columns; j++){
//                ans[i * shape.rows + j] = new MatrixCellValue(i, j,scalar + this.get(i, j));
//            }
//        }
//        return new MatrixSparse(this.shape, scalar * this.val, ans);
//    }

    public IDoubleMatrix minus(MatrixSparse other){
        assert shape.equals(other.shape());
        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[i * shape.columns + j]= new MatrixCellValue(j, i, this.get(j, i) - other.get(j, i));
            }
        }
        return new MatrixSparse(this.shape ,ans);
    }

    public IDoubleMatrix minus(MatrixLine other){
        assert shape.equals(other.shape());
        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
        for (int i = 0; i < shape.columns; i++){
            for (int j = 0; j < shape.rows; j++){
                ans[i * shape.columns + j]= new MatrixCellValue(j, i, this.get(j, i) - other.get(j, i));
            }
        }
        return new MatrixSparse(this.shape ,ans);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        return super.minus(other);
    }

    //    @Override
//    public IDoubleMatrix minus(double scalar){
//        if (scalar == 0){
//            return this;
//        }
//        MatrixCellValue[] ans = new MatrixCellValue[shape.rows * shape.columns];
//        for  (int i = 0; i < shape.rows; i++){
//            for (int j = 0; j < shape.columns; j++){
//                ans[i * shape.rows + j] = new MatrixCellValue(i, j, this.get(i, j) - scalar);
//            }
//        }
//        return new MatrixSparse(this.shape, scalar * this.val, ans);
//    }

    @Override
    public double get(int row, int column) {
        assert row < shape.rows;
        assert column < shape.columns;
        assert row >= 0;
        assert column >= 0;
//        return numb[row].get(column).value;
        for (int i = 0; i < numb[row].size(); i++){
            if (numb[row].get(i).column == column) return numb[row].get(i).column;
            if (numb[row].get(i).column > column) return val;
        }
        return val;
    }

    @Override
    public double[][] data() {
        double[][] ans = new double[shape.rows][shape.columns];
        for  (int i = 0; i < shape.rows; i++){
            for (int j = 0; j < shape.columns; j++){
                if(numb[i].get(j).column != j){ans[i][j] = val;}
                else {ans[i][j] = numb[i].get(j).value;}
            }
        }
        return ans;
    }
}
