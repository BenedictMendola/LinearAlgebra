package MatrixBasics;

import javax.management.InvalidAttributeValueException;
import UtilityClasses.*;

import UtilityClasses.Numb;

public class Matrix {

    private Numb[][] arr; // it is an array of rows. // numb[collum spot][row spot]
    private int m;
    private int n;

    public Matrix(int m, int n, Numb[] nums) throws InvalidAttributeValueException{ // m is amount of rows, n is for columns
        this.m = m;
        this.n = n;
        if(m * n != nums.length){
            throw new InvalidAttributeValueException("Length of Numb array not equal to m * n");
        }
        arr = new Numb[m][n];
        int r = -1;
        for(int i = 0; i < nums.length;i++){
            if(i%n == 0){
                r++;
            }
            arr[r][i%n] = nums[i];
            
        }

    }

    public Matrix(Matrix other){
        this.m = other.m;
        this.n = other.n;
        arr = new Numb[m][n];
        for(int i = 0; i < other.arr.length; i++){
            for(int j = 0; j < other.arr[0].length;j++){
                arr[i][j] = other.arr[i][j];
            }
        }
    }

    public Matrix(Numb[][] grd){
        this.m = grd.length;
        this.n = grd[0].length;
        this.arr = grd;
    }

    public int getColumnAmount(){
        return n;
    }

    public int getRowAmount(){
        return m;
    }

    public void rowSwap(int firstRow, int secondRow){
        Numb[] row1 = getRowArr(firstRow);
        arr[firstRow-1] = arr[secondRow-1];
        arr[secondRow-1] = row1;
    }

    public void rowMul(int row, Numb scalar){
        Numb[] num = getRowArr(row);
        for(int i = 0; i < num.length; i++){
            num[i] = num[i].multiply(scalar);
        }
    }
    public void rowMul(int row, int scalar){
        rowMul(row, new Numb(scalar));
    }
    public void rowMul(int row, Fraction scalar){
        rowMul(row, new Numb(scalar));
    }

    public void rowAdd(int row1, int row2, Numb scalar){
        Numb[] firstRow = getRowArr(row1);
        Numb[] secondRowScaled = new Numb[n];
        Numb[] secondRow = getRowArr(row2);
        for(int i = 0; i < n; i++){
            secondRowScaled[i] = secondRow[i].multiply(scalar);
        }
        for(int i = 0; i < n; i++){
            firstRow[i] = firstRow[i].add(secondRowScaled[i]);
        }
        arr[row1-1] = firstRow;
    }
    public void rowAdd(int row1, int row2, int scalar){
        rowAdd(row1,row2,new Numb(scalar));
    }
    public void rowAdd(int row1, int row2, Fraction scalar){
        rowAdd(row1,row2,new Numb(scalar));
    }

    public Numb[] getRowArr(int row){
        return arr[row-1];
    }

    public Numb[] getColArr(int col){
        col --;
        Numb[] colArr = new Numb[m];
        for(int i = 0; i < m; i++){
            colArr[i] = arr[i][col];
        } 
        return colArr;
    }
 
    public Matrix getRow(int row) throws InvalidAttributeValueException{
        return new Matrix(1,n,arr[row -1]);
    }

    public Matrix getColumn(int column) throws InvalidAttributeValueException{
        column --;
        Numb[] colArr = new Numb[m];
        for(int i = 0; i < m; i++){
            colArr[i] = arr[i][column];
        }

        return new Matrix(m,1,colArr);
    }
    @Override
    public String toString(){
        String str = "[";
        for(int i = 0; i <arr.length; i++){
            str = str + rowToString(i);
        }
        return str + "]";
    }

    public String rowToString(int row){
        String str = "[";
        str = str + spotToString(row, 0);
        for(int i = 1; i < arr[0].length; i++){
            str = str + "," + spotToString(row, i);
        }
        str = str + "]";
        return str;

    }
    private String spotToString(int i,int j){
        if((arr[i][j].toString()).length() > 4){
            return arr[i][j].toString() + " ";
        } 
        String blankSpace = "";
        for(int k = 0; k < 4 - (arr[i][j].toString()).length(); k++){
            blankSpace = blankSpace + " ";
        }
        return blankSpace + arr[i][j].toString() + " ";
    }

    public void printMatrix(){
        System.out.println();
        for(int i = 0; i < arr.length; i++){
            System.out.println(rowToString(i));
        }
        System.out.println();
    }

    public static Matrix mergeMatrixesSideways(Matrix m1, Matrix m2){
        Numb[][] newGid = new Numb[m1.getRowAmount()][m1.getColumnAmount() + m2.getColumnAmount()]; 
        for(int i = 0; i < newGid.length; i++){
            Numb[] m1Row = m1.getRowArr(i+1);
            for(int j = 0; j < m1.getColumnAmount(); j++){
                newGid[i][j] = m1Row[j];
            }
            Numb[] m2Row = m2.getRowArr(i+1);
            for(int j = m1.getColumnAmount(); j < m1.getColumnAmount() + m2.getColumnAmount(); j++){
                newGid[i][j]= m2Row[j-m1.getColumnAmount()];
            }
        }


        return new Matrix(newGid);
    }

    public static Matrix mergeMatrixesVerticaly(Matrix m1, Matrix m2){
        Numb[][] newGrid = new Numb[m1.getRowAmount() + m2.getRowAmount()][m1.getColumnAmount()];
        for(int i = 0; i < m1.getRowAmount() + m2.getRowAmount(); i++){
            if(i < m1.getRowAmount()){
                Numb[] m1Row = m1.getRowArr(i+1);
                for(int j = 0; j < m1.getColumnAmount(); j++){
                    newGrid[i][j] = m1Row[j];
                }
            } else {
                Numb[] m2Row = m2.getRowArr(i+1-m1.getRowAmount());
                for(int j = 0; j < m2.getColumnAmount(); j++){
                    newGrid[i][j] = m2Row[j];
                }
            }
        }


        return new Matrix(newGrid);
    }

    public static Matrix addMatrixes(Matrix m1, Matrix m2){
        Numb[][] m1Grid = m1.arr;
        Numb[][] m2Grid = m2.arr;
        Numb[][] newGrid = new Numb[m1Grid.length][m1Grid[0].length];

        for(int i = 0; i < m1Grid.length; i++){
            for(int j = 0; j < m1Grid[0].length; j++){
                newGrid[i][j] = m1Grid[i][j].add(m2Grid[i][j]);
            }
        }

        return new Matrix(newGrid);
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2){
        return addMatrixes(m1, multiplyMatrix(m2, new Numb(-1)));
    }

    public static Matrix multiplyMatrix(Matrix mat, Numb scalar){
        Numb[][] orgGrid = mat.arr;
        Numb[][] newGrid = new Numb[orgGrid.length][orgGrid[0].length];
        for(int i = 0; i < orgGrid.length; i++){
            for(int j = 0; j < orgGrid[0].length; j++){
                newGrid[i][j] = orgGrid[i][j].multiply(scalar);
            }
        }

        return new Matrix(newGrid);
    }

    public static Matrix multiplyMatrixes(Matrix matA, Matrix matB) throws InvalidAttributeValueException{
        // m x n n x h   (amount of collums from A have to equal amount of rows of B)
        Matrix newMatrix = multipySubB(matA, matB.getColumn(1));
        for(int i = 2; i <= matB.getColumnAmount(); i++){
            newMatrix = Matrix.mergeMatrixesSideways(newMatrix, multipySubB(matA, matB.getColumn(i)));
        }
        return newMatrix;
    }

    private static Matrix multipySubB(Matrix matA, Matrix colB){
        Numb[][] newGridCollum = new Numb[matA.getRowAmount()][1];
        Numb[] numsB = colB.getColArr(1);
        for(int i = 0; i < newGridCollum.length;i++){
            Numb sum = new Numb(0);
            Numb[] rowA = matA.getRowArr(i+1);
            for(int j = 0; j < numsB.length; j++){
                sum = sum.add(numsB[j].multiply(rowA[j]));
            }
            newGridCollum[i][0] = sum;
        }

        return new Matrix(newGridCollum);
    }



}
