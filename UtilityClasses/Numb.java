package UtilityClasses;

public class Numb {
    private final boolean isInt;
    
    private int intValue;
    private Fraction fractionValue;

    public Numb(int value){
        isInt = true;
        intValue = value;
    }

    public Numb(Fraction value){
        isInt = false;
        fractionValue = value;
    }

    public boolean isInt(){
        return isInt;
    }

    public int getIntValue(){
        return intValue;
    }
    public Fraction getFractionValue(){
        return fractionValue;
    }

    public Numb multiply(Numb o){
        if(isInt){
            if(o.isInt()){
                return new Numb(intValue * o.getIntValue());
            }
            Fraction newFraction = o.fractionValue.multiply(intValue);
            if(newFraction.isInt()){
                return new Numb(newFraction.toInteger());
            }
            return new Numb(newFraction);
        }
        if(o.isInt()){
            Fraction newFraction = fractionValue.multiply(o.getIntValue());
            if(newFraction.isInt()){
                return new Numb(newFraction.toInteger());
            }
            return new Numb(newFraction);
        }
        Fraction newFraction = fractionValue.multiply(o.getFractionValue());
        if(newFraction.isInt()){
            return new Numb(newFraction.toInteger());
        }
        return new Numb(newFraction);
    }

    public Numb add(Numb o){
        if(isInt){
            if(o.isInt()){
                return new Numb(intValue + o.getIntValue());
            }
            Fraction newFraction = o.getFractionValue().add(intValue);
            if(newFraction.isInt()){
                return new Numb(newFraction.toInteger());
            }
            return new Numb(newFraction);
        }
        if(o.isInt()){
            Fraction newFraction = fractionValue.add(o.getIntValue());
            if(newFraction.isInt()){
                return new Numb(newFraction.toInteger());
            }
            return new Numb(newFraction);
        }
        Fraction newFraction = fractionValue.add(o.getFractionValue());
        if(newFraction.isInt()){
            return new Numb(newFraction.toInteger());
        }
        return new Numb(newFraction);
    }

    public Numb subtract(Numb o){
        return add(o.multiply(new Numb(-1)));
    }

    @Override
    public String toString(){
        if (isInt()){
            return Integer.toString(intValue);
        }
        return fractionValue.toString();
    }

    public boolean isZero(){
        if(isInt){
            return intValue == 0;
        }
        return (fractionValue.equals(0));
    }

    public static Numb zero(){
        return new Numb(0);
    }

    public Numb inverse(){
        if(isInt){
            return new Numb(new Fraction(1,intValue));
        }
        return new Numb(fractionValue.inverse());
    }
}
