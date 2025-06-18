import java.time.LocalDate;
import java.util.Scanner;

public class EnergyRecording {

    private final User currUser;
    private final Scanner input;

    //constructor to save current user to record energy
    public EnergyRecording(User user, Scanner in){
        currUser=user;
        input=in;
    }

    //creates new Energy instance and saves to current user
    public void recordEnergy(){
        LocalDate date = null;
        double energy = 0;

        //outputs page formatting
        System.out.println();
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nEnergy Recording\n\n");

        //handles recording date and energy
        boolean dateSuccess=false;
        while (!dateSuccess){
            System.out.print("Enter date in the form YYYY-MM-DD: ");
            try {
                date = LocalDate.parse(input.next());
            } catch (Exception e){
                System.out.println("Please enter the date in the format YYYY-MM-DD.");
                continue;
            }
            if(!currUser.checkExisting(date)){
                dateSuccess=true;
            } else {
                System.out.println("Please enter a date that has not been entered before.");
            }
        }

        boolean energySuccess = false;
        while(!energySuccess){
            System.out.print("Enter energy used in kWh: ");
            try {
                energy = Double.parseDouble(input.next());
            } catch (Exception ignored){}

            if (energy<0) {
                System.out.println("Please enter a non zero number.");
            } else {
                energySuccess=true;
            }
        }

        //records energy to current user
        Energy recordedEnergy = currUser.recordEnergy(date, energy);

        //calculates and increments points of user
        int points = calculatePoints(energy, recordedEnergy);
        currUser.addPoints(points);

        System.out.println("Energy for "+date+" successfully recorded. \nYou gained "+points+" points! \n");
    }

    //calculates points that user should gain based on amount saved from previous records
    private int calculatePoints(Double energy, Energy recordedEnergy){
        //gets the previous energy saved of user
        double prevEnergy = currUser.getPreviousEnergy(recordedEnergy);

        if(prevEnergy==0||prevEnergy<energy){
            return 0;
        }

        //points calculation
        return (int) ((prevEnergy-energy)*10);
    }

}
