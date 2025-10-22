package UserInteraction;

import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

import MatrixBasics.Matrix;
import UtilityClasses.*;

public class AdvancedScanner {
    private Scanner inKey;

    public AdvancedScanner(){
        inKey = new Scanner(System.in);
    }

    public String getString(String requestMessage){
        System.out.print(requestMessage);
        return inKey.nextLine();
    }

    public int getInt(String requestMessage){
        System.out.print(requestMessage);
        String inputRaw = inKey.nextLine();
        while(inputRaw.length() == 0 ||!isInt(inputRaw)){
            System.out.println("Invalid input. Please enter a valid integer");
            inputRaw = inKey.nextLine();
        }
        int returnInt = Integer.valueOf(inputRaw);

        return returnInt;
    }

    public Numb getNumber(String requestMessage){
        System.out.print(requestMessage);
        String rawInput = inKey.nextLine();

        while(rawInput.length() == 0 || (!isInt(rawInput) && ! isFraction(rawInput))){
            System.out.println("Invalid input. Please enter a valid integer or fraction");
            rawInput = inKey.nextLine();
        }

        if(isInt(rawInput)){
            return new Numb(Integer.valueOf(rawInput));
        }
        if(isFraction(rawInput)){
            return new Numb(stringToFraction(rawInput));
        }
        return new Numb(-9999);

    }

    public Matrix getMatrix(String matrixName) throws InvalidAttributeValueException{
        System.out.println("Matrix " + matrixName +" Details :\n");
        int m = getInt("        " + matrixName + " Amount of rows   :   ");
        while(m < 1){
            m = getInt("        " + matrixName + " Amount of rows (MUST BE > 1 )   :   ");
        }
        int n = getInt("        " + matrixName + " Amount of Columns:   ");
        while (n<1) {
            n = getInt("        " + matrixName + " Amount of Columns: (MUST BE > 1 )  ");
        }
        Numb[] numbs = new Numb[m*n];
        System.out.println();
        for(int i = 0; i < numbs.length; i++){
            numbs[i] = getNumber("        Enter a number for A " + ((int)(i/m) + 1)+ " " + ((i % n) + 1) + " :    ");
        }

        return new Matrix(m, n, numbs);
    }

    private boolean isInt(String str){
        if(!Character.isDigit(str.charAt(0)) && !(str.charAt(0) == '-')){
            return false;
        }
        for(int i = 1; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    

    private boolean isFraction(String str){
        boolean foundSlash = false;
        if(!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-'){
            return false;
        }
        if(str.length() == 1 && str.charAt(0) == '-'){
            return false;
        }
        for(int i = 1; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i)) && str.charAt(i) != '/'){
                return false;
            }
            if(str.charAt(i) == '/'){
                if(foundSlash){
                    return false;
                }
                foundSlash = true;
            }


        }


        return true;
    }

    private Fraction stringToFraction(String str){
        int sign = 1;
        if(str.charAt(0) == '-'){
            sign = -1;
            str = str.substring(1);
        }

        String numerator = "";
        String denominator = "";
        boolean reachedDenom = false;

        for(int i = 0; i < str.length(); i++){
            if(!reachedDenom){
                if(str.charAt(i) != '/'){
                    numerator = numerator + str.charAt(i);
                } else {
                    reachedDenom = true;
                }
            } else {
                denominator = denominator + str.charAt(i);
            }
        }
        int nInt = sign *  Integer.valueOf(numerator);
        int dInt = Integer.valueOf(denominator);
        return new Fraction(nInt, dInt);
    }
    
}
