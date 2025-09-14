// Interface will methods to be overriden by the Coach and Player classes.

public interface Contract{

    public abstract String getName();

    public abstract String getNationality();

    public abstract int getAge();

    public abstract int getSalary();

    public abstract int getContractLength();

    public abstract void setContractLength(int contractLength);
    
    public abstract void ageIncrement();
    
    public abstract void contractDecrement();

}