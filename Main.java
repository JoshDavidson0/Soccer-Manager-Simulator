import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean run = true;

        Manager activeManager = new Manager(); 
        Coach coach = new Coach();
        
        activeManager.generateTeam();

        System.out.println("-------------------------");
        System.out.println("Welcome to Soccer Manager");

        while (run) {
            System.out.println("-------------------------");
            System.out.println("1. View Team");
            System.out.println("2. Create A Coach");
            System.out.println("3. Renew A Contract");
            System.out.println("4. Play A Season");
            System.out.println("5. End the Game");
            System.out.println("-------------------------");
            System.out.print("What would you like to do: ");

            while (!scnr.hasNextInt()) {
                System.out.println("Please enter a valid number");
                scnr.next();
            }

            int choice = scnr.nextInt();
            scnr.nextLine(); 

            switch (choice) {
                case 1:
                    activeManager.viewTeam(coach);
                    break;
                case 2:
                    coach.createCoach();
                    break;
                case 3:
                    activeManager.renewContract();
                    break;
                case 4:
                    activeManager.runSeason(coach);
                    break;
                case 5:
                    System.out.println("Your game has now ended. Thanks for playing");
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
            }
        }

        scnr.close();
    }
}
