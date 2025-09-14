import java.util.Scanner;
import java.util.Random;


public class Coach implements Contract{

    //attributes of a Coach Object.
    private String name;
    private int salary;
    private int age;
    private int contractLength;
    private String nationality;
    private boolean coachCreated = false;

    // Arrays to store random attribute values which will be assigned to the Coach.
    Random rand = new Random();
    int[] randomContractLengths = {1,2,3,4,5};
    int[] randomSalaries = {100000,150000,200000,250000,300000,350000};
    int[] randomAges = {42,67,36,58,70,39,45,62,37,53,66,48};
    String[] randomNationalities = {"Brazil", "South Korea", "Egypt", "Canada", "Germany", "Argentina", "Japan", "Nigeria", "Turkey", "Australia"};


    public void createCoach() {
        Scanner scnr = new Scanner(System.in);

        // If a coach has already been created, the user may opt to rename thier coach.
        if (coachCreated) {
            System.out.print("You have already created a Coach. Would you like to rename your coach? (y/n): ");
            String response = scnr.nextLine().trim().toLowerCase();

            if (response.equals("y")) {
                System.out.print("What would you like to rename your coach: ");
                String newName = scnr.nextLine();
                setCoachName(newName);
                System.out.println("The name of your coach is now " + getName() + "!");
            }
        } else {
            // If a coach has not been created, the user will name the coach and random attribute values will be assigned to the Coach.
            System.out.println("-------------------------");
            System.out.print("What would you like to name your coach: ");
            String coachName = scnr.nextLine();
            setCoachName(coachName);
            coachCreated = true;
            System.out.println("You have created a coach named " + getName() + "!");

            int randomContractIndex = rand.nextInt(randomContractLengths.length);
            int randomSalaryIndex = rand.nextInt(randomSalaries.length);
            int randomAgeIndex = rand.nextInt(randomAges.length);
            int randomNationalityIndex = rand.nextInt(randomNationalities.length);

            int randomContract = randomContractLengths[randomContractIndex];
            int randomSalary = randomSalaries[randomSalaryIndex];
            int randomAge = randomAges[randomAgeIndex];
            String randomNationality = randomNationalities[randomNationalityIndex];

            this.salary = randomSalary;
            this.contractLength = randomContract;
            this.age = randomAge;
            this.nationality = randomNationality;
        }
        
    }

    // Getter and Setter methods of Coach attributes for outside usage, implemented from Contract Interface.
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

    public void setCoachName(String name) {
        this.name = name;
    }

    // Method to renew the expiring contract of a Coach, parameter newLength will be a random number for the Manager Class.
    public void renewContract(int newLength) {
        this.contractLength = newLength;
        System.out.println();
        System.out.println(name + " and the office staff have agreed to a " + newLength + " year extension!");
    }

}

    

    
    

    
    
