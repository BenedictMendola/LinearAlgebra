package MatrixBasics;

public class CommonMatrixOpperations {
    
    public static Matrix matrixInverse(Matrix matA){
        Matrix augMatrix = Matrix.mergeMatrixesSideways(matA, Matrix.identity(matA.getColumnAmount()));
        Matrix rrefMatrix = RowReduction.rowReduce(augMatrix);
        return rrefMatrix.getSubMatrix(1, matA.getRowAmount(), matA.getColumnAmount()+1, augMatrix.getColumnAmount());
    }

    public static boolean isInvertibe(Matrix matA){
        Matrix rrefMatrix = RowReduction.rowReduce(matA);
        return rrefMatrix.isIdentiy();
    }

}
