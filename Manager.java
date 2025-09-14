import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager{
    // Collections to store the players, opponents, and players in need of contract renewal/
    private List<Player> playerList = new ArrayList<>();
    private List<String> opponentsList = new ArrayList<>();
    private ArrayList<Player> renewalList = new ArrayList<>();

    Random rand = new Random();
    int[] randomContractLengths = {1,2,3,4,5};

    public void generateTeam(){

        playerList.clear(); 

        // Arrays to store random attributes that will be assigned to each player.
        Random rand = new Random(); 
        int[] randomContractLengths = {1,2,3,4,5};
        int[] randomSalaries = {100000,150000,200000,250000,300000,350000};
        int[] randomAges = {18,19,20,21,22,23,24,25,26,27,28,29,30};

        // Creating players by taking data from a csv and assigning them to player attributes as well as the random data.
        try{

            File file = new File("DefaultPlayers.csv");
            Scanner scnr = new Scanner(file);

            while (scnr.hasNext()){

                String line = scnr.nextLine();
                String [] lineArray = line.split(",");

                int randomContractIndex = rand.nextInt(randomContractLengths.length);
                int randomSalaryIndex = rand.nextInt(randomSalaries.length);
                int randomAgeIndex = rand.nextInt(randomAges.length);


                int randomContract = randomContractLengths[randomContractIndex];
                int randomSalary = randomSalaries[randomSalaryIndex];
                int randomAge = randomAges[randomAgeIndex];

                Player player = new Player(lineArray[0],lineArray[1],randomAge,randomSalary,randomContract);
                playerList.add(player); 
            }
            scnr.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    // Method to view all players and Coach (if created) data.
    public void viewTeam(Coach coach) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        if (coach != null && coach.getName() != null && !coach.getName().isEmpty()) {
            System.out.printf("%s | Name: %s | Nationality: %s | Age: %d | Salary: %d | Contract Length: %d years%n",
                "Coach", coach.getName(), coach.getNationality(), coach.getAge(), coach.getSalary(), coach.getContractLength());
        } else {
            System.out.println("No coach has been created yet.");
        }

        System.out.println("Here are the players on your team:");
        int num = 1;
        for (Player p : playerList) {
            System.out.printf("%d | Name: %s | Nationality: %s | Age: %d | Salary: %d | Contract Length: %d years%n",
                num++, p.getName(), p.getNationality(), p.getAge(), p.getSalary(), p.getContractLength());
        }
    }

    // Method to verify if all expiring Contracts have been renewed before proceeding with season simulation.
    public boolean allContractsRenewed(){
        renewalList.clear();
        for (Player p : playerList) {
            if (p.getContractLength() == 1) {
            renewalList.add(p);
            }
        }

        if (!renewalList.isEmpty()) {
            System.out.println("Before playing through a season, renew the following players' contracts:");
            for (Player p : renewalList) {
                System.out.println(p.getName());
            }
            return false;
        }

        return true;
    }
    
    // Method to renew expiring player contracts.
    public void renewContract(){
        renewalList.clear();
        Scanner scnr = new Scanner(System.in);
        int num = 1;
        for (Player p : playerList){
            if (p.getContractLength() == 1){
                renewalList.add(p);
                System.out.println(num + ". " + p.getName());
                num++;
            }

        }
        
        if (renewalList.size() == 0){
            System.out.println("You have no players to renew!");
            return;
        }
        System.out.println("-------------------------");
        System.out.print("Which player would you like to renew? (Enter the number): ");

        while (!scnr.hasNextInt()){
            System.out.println("You must enter a valid number");
            scnr.next();
        }
        int renewalPlayerChoice = scnr.nextInt(); // variable holding which player will have their contract renewed.

        System.out.println("");

        int randomContractIndex = rand.nextInt(randomContractLengths.length);
        int newContractLength = randomContractLengths[randomContractIndex];

        renewalList.get(renewalPlayerChoice - 1).setContractLength(newContractLength);
        System.out.println("");
        System.out.println(renewalList.get(renewalPlayerChoice - 1).getName() + " and the office staff have agreed a " + newContractLength + " year extension!");
        
    }

    // Method to simulate season against oppponent teams.
    public void runSeason(Coach coach){
        System.out.println("-------------------------");
        int winCount = 0;
        int lossCount = 0;
        int tieCount = 0;
        if (coach.getName() == null || coach.getName().isEmpty()){
            System.out.println("You must create a coach before playing a season");
            return;
        }
        if (coach.getContractLength() == 1) {
            int randomContractIndex = rand.nextInt(randomContractLengths.length);
            int newContractLength = randomContractLengths[randomContractIndex];

            coach.renewContract(newContractLength);
        }

        if (allContractsRenewed() == false){
            return;
        } else{
                opponentsList.clear();
                try{
                    File file = new File("Opponents.txt");
                    Scanner scnr = new Scanner(file);

                    while (scnr.hasNext()){
                        String line = scnr.nextLine();
                        opponentsList.add(line);
                    }

                    
                    int[] randomNum = {1,2,3,4};

                    for(String s : opponentsList){
                        int randomIndex = rand.nextInt(randomNum.length);
                        int myScore = randomNum[randomIndex];
                        randomIndex = rand.nextInt(randomNum.length);
                        int oppScore = randomNum[randomIndex];
                        

                        if(myScore > oppScore){
                            System.out.println(myScore + " - " + oppScore + " vs. " +s+" (W)");
                            winCount++;
                        } if(myScore < oppScore){
                            System.out.println(myScore + " - " + oppScore + " vs. " +s+" (L)");
                            lossCount++;
                        } if (myScore == oppScore){
                            System.out.println(myScore + " - " + oppScore + " vs. " +s+" (T)");
                            tieCount++;

                        }
                    }

                System.out.println("You won " + winCount + " game(s) / You lost "  +lossCount+" game(s) / You tied " + tieCount+ " game(s)");
                scnr.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            }   

            seasonDone(coach);
    }

    // End of season modifications to player/coach ages and contract lengths/
    public void seasonDone(Coach coach){
        for (Player p : playerList){
            p.ageIncrement();
            p.contractDecrement();
        }

        if(coach != null){
            coach.contractDecrement();
            coach.ageIncrement();
        }

        
    }
}
