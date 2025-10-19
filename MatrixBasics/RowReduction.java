package MatrixBasics;
import UtilityClasses.*;

public class RowReduction {
    
    public static Matrix rowRecduce(Matrix originalMatrix){
        Matrix augmentedMatrix = new Matrix(originalMatrix);

        int minDepth = 1;
        for(int col = 1; col <= augmentedMatrix.getColumnAmount(); col++){
            int pivotRow = getPivotRow(augmentedMatrix,col,minDepth);
            if(pivotRow != -1 && pivotRow <= augmentedMatrix.getRowAmount()){
                minDepth++;
                reduceColumn(augmentedMatrix,col,pivotRow,minDepth);
            }
            
        }



        return augmentedMatrix;
    }

    private static int getPivotRow(Matrix augmentedMatrix, int column, int minDepth){ // returns -1 if no pivot, and the row the pivot is in otherwise
        Numb[] colToCheck = augmentedMatrix.getColArr(column); 
        for(int i = minDepth; i <= colToCheck.length; i++){
            if(!colToCheck[i-1].isZero()){
                return i;
            }
        }
        

        return -1;
    }

    private static void reduceColumn(Matrix aMatrix, int col, int pivRow, int minDepth){ //makes all that are not pivot 0 and 
        aMatrix.rowSwap(minDepth -1, pivRow);
        Numb[] originalCol = aMatrix.getColArr(col);
        Numb inver = originalCol[minDepth-2].inverse();

        for(int i = 1; i <= aMatrix.getRowAmount(); i++){
            if(originalCol[i-1].isZero()){
                continue;
            }
            if(i == minDepth -1){
                continue;
            }
            Numb scalar = originalCol[i-1].multiply(inver);
            aMatrix.rowAdd(i, minDepth-1, scalar.multiply(new Numb(-1)));
        }
        aMatrix.rowMul(minDepth-1, inver);

    }


}
