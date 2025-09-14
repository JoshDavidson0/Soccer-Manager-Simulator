public class Player implements Contract{

    //attributes of a Player Object.
    private String name;
    private String nationality;
    private int age;
    private int salary;
    private int contractLength;

    // Constructor for a player, parameters will be loaded from a file.
    public Player(String name, String nationality, int age, int salary, int contractLength) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.salary = salary;
        this.contractLength = contractLength;
    }

    // Getter and Setter methods of Player attributes for outside usage, implemented from Contract Interface.
    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getNationality(){
        return nationality;
    }

    @Override
    public int getAge(){
        return age;
    }
    
    @Override
    public int getSalary(){
        return salary;
    }

    @Override
    public int getContractLength(){
        return contractLength;
    }

    @Override
    public void setContractLength(int newContractLength) {
        this.contractLength = newContractLength;
    }

    @Override
    public void ageIncrement(){
        this.age++;
    }
    
    @Override
    public void contractDecrement(){
        this.contractLength--;
    }
    
}
