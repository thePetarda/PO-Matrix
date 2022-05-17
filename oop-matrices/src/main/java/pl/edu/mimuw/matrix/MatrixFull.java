package pl.edu.mimuw.matrix;

import javax.lang.model.type.NullType;

public class MatrixFull extends Matrix implements IDoubleMatrix{
    private final double[][] numb;

    public MatrixFull(double[][] values){
        super(values);
        assert values != null;
        assert values.length > 0;
        assert values[0].length > 0;
        int size = values[0].length;
        this.numb = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.columns; i++){
            assert values[i].length == size;
            assert values[i] != null;
            for (int j = 0; j < shape.rows; j++){
                numb[j][i] = values[j][i];
            }
        }
    }

    @Override
    public double get(int row, int column) {
//        if ((row > shape.rows) || (column > shape.columns)){
//            return null;
//        }\
        shape.assertInShape(row, column);
        assert row >= 0;
        assert column >= 0;
        assert row < shape.rows;
        assert column < shape.columns;
        return numb[row][column];
    }

    @Override
    public double[][] data() {
        return numb;
    }
}
