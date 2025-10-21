package MatrixBasics;

import UtilityClasses.Numb;

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

    public static Numb determinant(Matrix matrixA){
        if(matrixA.getColumnAmount() == 1){
            return matrixA.getColArr(0)[0];
        }
        if(matrixA.getColumnAmount() == 2){
            return (matrixA.getRowArr(1)[0].multiply(matrixA.getRowArr(2)[1])).subtract(matrixA.getRowArr(1)[1].multiply(matrixA.getRowArr(2)[0]));
        }

        Numb sum = new Numb(0);
        Numb[] topRow = matrixA.getRowArr(1);

        for(int i = 0; i < topRow.length; i++){
            sum = sum.add(topRow[i].multiply(determinant(getNotInCross(matrixA, i)).multiply(new Numb((int)Math.pow(-1, i+2)))));
        }

        return sum;
    }

    private static Matrix getNotInCross(Matrix matrixA, int column){
        Numb[][] newMat = new Numb[matrixA.getColumnAmount()-1][matrixA.getColumnAmount()-1];
        for(int row = 1; row < matrixA.getColumnAmount(); row++){
            Numb[] rowNums = matrixA.getRowArr(row+1);
            for(int col = 0; col < matrixA.getColumnAmount(); col++){
                if(col != column){
                    if(col < column){
                        newMat[row-1][col] = rowNums[col];
                    } else {
                        newMat[row-1][col-1] = rowNums[col];
                    }
                }
            }
        }    

        return new Matrix(newMat);
    }

}
