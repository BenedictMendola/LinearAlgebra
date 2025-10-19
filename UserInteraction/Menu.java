package UserInteraction;

import javax.management.InvalidAttributeValueException;

import MatrixBasics.Matrix;
import MatrixBasics.RowReduction;
import UtilityClasses.Numb;
public class Menu {
    
    public static void runMenu() throws InvalidAttributeValueException{
        printWelcome();
        boolean running = true;
        AdvancedScanner scanner = new AdvancedScanner();
        String userInput;

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
                    rowReduce(scanner);
                    break;
                case "2" :
                    matrixAddition(scanner);
                    break;
                case "3":
                    matrixSubtraction(scanner);
                    break;
                case "4":
                    matrixMultiplicationWithScalar(scanner);
                    break;
                case "5":
                    matrixMultiplication(scanner);
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

    private static void rowReduce(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat = scanner.getMatrix("A");
        mat.printMatrix();
        Matrix reducedMat = RowReduction.rowRecduce(mat);
        reducedMat.printMatrix();
    }

    private static void matrixAddition(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat1 = scanner.getMatrix("A");
        System.out.println();
        Matrix mat2 = scanner.getMatrix("B");
        if(!(mat1.getColumnAmount() == mat2.getColumnAmount() && mat1.getRowAmount() == mat2.getRowAmount())){
            System.out.println("\nMatrix Dimensions do not Match. Invalid Opperation.");
            return;
        }
        Matrix mat3 = Matrix.addMatrixes(mat1, mat2);
        System.out.println();
        for(int i = 0; i < mat1.getRowAmount(); i++){
            System.out.printf("%20s + %20s = %20s\n", mat1.rowToString(i),mat2.rowToString(i),mat3.rowToString(i));
        }
        System.out.println("\n");
    }

    private static void matrixSubtraction(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix mat1 = scanner.getMatrix("A");
        System.out.println();
        Matrix mat2 = scanner.getMatrix("B");
        if(!(mat1.getColumnAmount() == mat2.getColumnAmount() && mat1.getRowAmount() == mat2.getRowAmount())){
            System.out.println("\nMatrix Dimensions do not Match. Invalid Opperation.");
            return;
        }
        Matrix mat3 = Matrix.subtractMatrix(mat1, mat2);
        System.out.println();
        for(int i = 0; i < mat1.getRowAmount(); i++){
            System.out.printf("%20s - %20s = %20s\n", mat1.rowToString(i),mat2.rowToString(i),mat3.rowToString(i));
        }
        System.out.println("\n");
    }

    private static void matrixMultiplicationWithScalar(AdvancedScanner scanner) throws InvalidAttributeValueException{
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
    }

    private static void matrixMultiplication(AdvancedScanner scanner) throws InvalidAttributeValueException{
        Matrix matA = scanner.getMatrix("A");
        Matrix matB = scanner.getMatrix("B");
        if(matA.getColumnAmount() != matB.getRowAmount()){
            System.out.println("Matrix dimensions need to be in A: m x n  B: n x p");
            return;
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

    }

    private static void printWelcome(){
        System.out.println("\n\n\nWelcome to Benedict's Linear Algebra Calculator.\n");
    }

    private static void printMenu(){
        String toPrint = "What opperation would you like to use?"
        + "\nRow Reduce            : 1"
        + "\nAdd Matrixes          : 2"
        + "\nSubtract Matrixes     : 3"  
        + "\nMultiply Matrix and C : 4"
        + "\nMultipy Two Matrixes  : 5"
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
