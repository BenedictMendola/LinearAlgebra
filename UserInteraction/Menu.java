package UserInteraction;

import javax.management.InvalidAttributeValueException;

import MatrixBasics.*;
import UtilityClasses.Numb;

public class Menu {
    
    public static void runMenu() throws InvalidAttributeValueException{
        printWelcome();
        boolean running = true;
        AdvancedScanner scanner = new AdvancedScanner();
        String userInput;
        Boolean matrixIsLastSaved = true;
        Matrix savedMatrix = Matrix.identity(3);
        Numb savedNumb = new Numb(0);

        while(running){
            printLine();
            printMenu();
            userInput = scanner.getString("Input: ").toLowerCase();
            while(userInput == null){
                userInput = scanner.getString("Input: ").toLowerCase();
            }
            printLine();
            switch(userInput){
                case "1" :
                    matrixIsLastSaved = true;
                    savedMatrix = rowReduce(scanner);
                    break;
                case "2":
                    matrixIsLastSaved = true;
                    savedMatrix = inverse(scanner);
                    break;
                case "3":
                    matrixIsLastSaved = false;
                    savedNumb = findDeterminant(scanner);
                    break;
                case "4" :  
                    matrixIsLastSaved = true;
                    savedMatrix = findAdjugate(scanner);
                    break;
                case "5" :
                    matrixIsLastSaved = true;
                    savedMatrix = matrixAddition(scanner);
                    break;
                case "6":
                    matrixIsLastSaved = true;
                    savedMatrix = matrixSubtraction(scanner);
                    break;
                case "7":
                    matrixIsLastSaved = true;
                    savedMatrix = matrixMultiplicationWithScalar(scanner);
                    break;
                case "8":
                    matrixIsLastSaved = true;
                    savedMatrix = matrixMultiplication(scanner);
                    break;
                case "9":
                    if(matrixIsLastSaved){
                        printSavedMatrix(savedMatrix);
                        break;
                    }
                    printSavedNum(savedNumb);
                    break;
                case "exit" :
                    running = false;
                    break;
                default:
                    System.out.println("\nInput not a valid input.\nPlease enter a input as listed above.\n");

            }
        }
        printGoodbye();
    }

    private static Matrix rowReduce(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat = scanner.getMatrix("A");
        System.out.println("A:");
        mat.printMatrix();
        System.out.println("A in RREF:");
        Matrix reducedMat = RowReduction.rowReduce(mat);
        reducedMat.printMatrix();
        return reducedMat;
    }

    private static Matrix inverse(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix matA = scanner.getMatrix("A");
        if(!CommonMatrixOpperations.isInvertibe(matA)){
            System.out.println("Matrix A is not invertible");
            return new Matrix(matA);
        }

        Matrix invA = CommonMatrixOpperations.matrixInverse(matA);
        System.out.printf("\nA = %36s\n",matA.rowToString(0));
        for(int i = 1; i < matA.getRowAmount(); i++){
            System.out.printf("%40s\n",matA.rowToString(i));
        }
        System.out.printf("\nA inverse = %28s\n",invA.rowToString(0));
        for(int i = 1; i < invA.getRowAmount(); i++){
            System.out.printf("%40s\n",invA.rowToString(i));
        }
        
        return invA;
    }

    private static Numb findDeterminant(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix matA = scanner.getMatrix("A");
        if(matA.getColumnAmount() != matA.getColumnAmount()){
            System.out.println("A does not have a determinate as it is not a square matrix.");
            return new Numb(0);
        }
        System.out.println("A:");
        matA.printMatrix();
        Numb detA = CommonMatrixOpperations.determinant(matA);
        System.out.println("detA : " + detA);
        return detA;
    }

    private static Matrix findAdjugate(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix matA = scanner.getMatrix("A");
        if(!CommonMatrixOpperations.isInvertibe(matA)){
            System.out.println("A is not invertible");
            return matA;
        }
        System.out.println("Matrix A :");
        matA.printMatrix();
        System.out.println("Abjugate of A :");
        Matrix newMat = CommonMatrixOpperations.adjugate(matA);
        newMat.printMatrix();
        return newMat;
    }

    private static Matrix matrixAddition(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat1 = scanner.getMatrix("A");
        System.out.println();
        Matrix mat2 = scanner.getMatrix("B");
        if(!(mat1.getColumnAmount() == mat2.getColumnAmount() && mat1.getRowAmount() == mat2.getRowAmount())){
            System.out.println("\nMatrix Dimensions do not Match. Invalid Opperation.");
            return mat1;
        }
        Matrix mat3 = Matrix.addMatrixes(mat1, mat2);
        System.out.println();
        for(int i = 0; i < mat1.getRowAmount(); i++){
            System.out.printf("%20s + %20s = %20s\n", mat1.rowToString(i),mat2.rowToString(i),mat3.rowToString(i));
        }
        System.out.println("\n");
        return mat3;
    }

    private static Matrix matrixSubtraction(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat1 = scanner.getMatrix("A");
        System.out.println();
        Matrix mat2 = scanner.getMatrix("B");
        if(!(mat1.getColumnAmount() == mat2.getColumnAmount() && mat1.getRowAmount() == mat2.getRowAmount())){
            System.out.println("\nMatrix Dimensions do not Match. Invalid Opperation.");
            return mat1;
        }
        Matrix mat3 = Matrix.subtractMatrix(mat1, mat2);
        System.out.println();
        for(int i = 0; i < mat1.getRowAmount(); i++){
            System.out.printf("%20s - %20s = %20s\n", mat1.rowToString(i),mat2.rowToString(i),mat3.rowToString(i));
        }
        System.out.println("\n");
        return mat3;
    }

    private static Matrix matrixMultiplicationWithScalar(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat1 = scanner.getMatrix("A");
        System.out.println();
        Numb scalar = scanner.getNumber("Number: ");
        Matrix mat3 = Matrix.multiplyMatrix(mat1, scalar);
        System.out.println();
        System.out.printf("%20s%s   = %20s\n", mat1.rowToString(0),scalar,mat3.rowToString(0));
        for(int i = 1; i < mat1.getRowAmount(); i++){
            System.out.printf("%20s   = %20s\n", mat1.rowToString(i),mat3.rowToString(i));
        }
        System.out.println("\n");
        return mat3;
    }

    private static Matrix matrixMultiplication(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix matA = scanner.getMatrix("A");
        Matrix matB = scanner.getMatrix("B");
        if(matA.getColumnAmount() != matB.getRowAmount()){
            System.out.println("Matrix dimensions need to be in A: m x n  B: n x p");
            return matA;
        }
        Matrix matAB = Matrix.multiplyMatrixes(matA, matB);
        
        System.out.printf("A = %10s\n",matA.rowToString(0));
        for(int i = 1; i < matA.getRowAmount(); i++){
            System.out.printf("%14s\n",matA.rowToString(i));
        }
        System.out.printf("\nB = %10s\n",matB.rowToString(0));
        for(int i = 1; i < matA.getRowAmount(); i++){
            System.out.printf("%14s\n",matB.rowToString(i));
        }
        System.out.printf("\nAB = %9s\n",matAB.rowToString(0));
        for(int i = 1; i < matA.getRowAmount(); i++){
            System.out.printf("%14s\n",matAB.rowToString(i));
        }
        return matAB;
    }

    private static void printSavedMatrix(Matrix savedMatrix){
        System.out.println("Saved Matrix:");
        savedMatrix.printMatrix();
    }
    
    private static void printSavedNum(Numb savedNumb){
        System.out.println("Saved Number : " + savedNumb);
    }

    private static void printWelcome(){
        System.out.println("\n\n\nWelcome to Benedict's Linear Algebra Calculator.\n");
    }

    private static void printMenu(){
        String toPrint = "What opperation would you like to use?"
        + "\nRow Reduce            : 1"
        + "\nFind Inverse          : 2"
        + "\nFind Determinant      : 3"
        + "\nFind Adjugate Matrix  : 4"
        + "\nAdd Matrixes          : 5"
        + "\nSubtract Matrixes     : 6"  
        + "\nMultiply Matrix and C : 7"
        + "\nMultiply Two Matrixes : 8"
        + "\nPrint Ans             : 9"
        + "\nQuit Program          : exit"
        + "\n";
        System.out.println(toPrint);
    }

    private static void printGoodbye(){
        System.out.println("\nThank you for using Benedict's linear algebra calculator, Have a great day!\n");
    }

    private static void printLine(){
        System.out.println("------------------------------------------------------------------------------------");
    }


}
