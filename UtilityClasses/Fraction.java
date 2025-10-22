package UtilityClasses;


public class Fraction{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator){
        if(denominator < 0){
            denominator = -denominator;
            numerator = -numerator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public boolean isInt(){
        if(denominator == 1){
            return true;
        }
        return false;
    }

    public int toInteger(){
        if(isInt()){
            return numerator;
        }
        return Math.round(numerator/denominator);
    }

    public double toDouble(){
        return ((double)numerator)/((double)denominator);
    }

    private void simplify(){

        if(numerator % denominator == 0){
            numerator = (int)(numerator / denominator);
            denominator = 1;
            return;
        }

        for(int i = (int)(denominator)/2 + 1; i > 1 ; i--){
            if(numerator % i == 0 && denominator % i == 0){
                numerator = (int)(numerator / i);
                denominator = (int)(denominator / i);
                i = denominator -1;
            }
        }
    }

    public Fraction add(Integer num){
        return new Fraction(numerator + num * denominator, denominator);
    }

    public Fraction add(Fraction num){
        return new Fraction(numerator * num.denominator + num.numerator * denominator, denominator * num.denominator);
    }

    public Fraction multiply(Integer num){
        return new Fraction(numerator * num, denominator);
    }

    public Fraction multiply(Fraction num){
        return new Fraction(numerator * num.numerator, denominator * num.denominator);
    }

    public Fraction subtract(Integer num){
        return add(-num);
    }

    public Fraction subtract(Fraction num){
        return add(num.multiply(-1));
    }

    public Fraction inverse(){
        return new Fraction(denominator, numerator);
    }

    @Override
    public String toString(){
        if(denominator == 1){
            return (numerator + "");
        }
        return (numerator+"/"+denominator);
    }

    public boolean equals(Object o){
        if(o instanceof Fraction){
            return this.numerator == ((Fraction)o).numerator && this.denominator == ((Fraction)o).denominator;
        }
        if(o instanceof Integer){
            return denominator == 1 && numerator == (Integer)o;
        }
        if(o instanceof Double){
            return toDouble() == (Double)o;
        }
        return false;
    }


}
