import java.time.LocalDate;
import java.util.Scanner;

public class EnergyRecording {

    //Saves current user for method calls
    private final User currUser;
    //Saves scanner to take input for all methods
    private final Scanner input;

    //Constructor to save current user to record energy
    public EnergyRecording(User user, Scanner in){
        currUser=user;
        input=in;
    }

    /**
     * Creates new Energy instance and saves to current user
     *
     * Takes in date and energy from scanner input
     */
    public void recordEnergy(){
        LocalDate date = null;
        double energy = 0;

        //Output formatting
        //Displays border and page heading
        System.out.println();
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nEnergy Recording\n\n");

        //Handles recording date
        //Checks if date has not been entered before and is in correct format
        boolean dateSuccess=false;
        while (!dateSuccess){
            System.out.print("Enter date in the form YYYY-MM-DD: ");
            try {
                date = LocalDate.parse(input.next());
            } catch (Exception e){
                //Exception thrown if date entered is not the correct format
                System.out.println("Please enter the date in the format YYYY-MM-DD.");
                continue;
            }
            if(!currUser.checkExisting(date)){
                dateSuccess=true;
            } else {
                System.out.println("Please enter a date that has not been entered before.");
            }
        }

        //Handles recording energy
        boolean energySuccess = false;
        while(!energySuccess){
            System.out.print("Enter energy used in kWh: ");
            try {
                energy = Double.parseDouble(input.next());
                System.out.println(energy);
            } catch (Exception ignored){
                System.out.println("Please enter a valid decimal value.");
                continue;
            }

            //Makes sure that energy entered is non zero double
            if (energy<0) {
                System.out.println("Please enter a non zero number.");
            } else {
                energySuccess=true;
            }
        }

        //Records energy to current user
        Energy recordedEnergy = currUser.recordEnergy(date, energy);

        //Calculates and adds points to user
        int points = calculatePoints(energy, recordedEnergy);
        currUser.addPoints(points);

        System.out.println("Energy for "+date+" successfully recorded. \nYou gained "+points+" points! \n");
    }


    /**
     * Calculates points that user should gain based on amount saved from previous records
     * Calculation of points follow: (previous energy - current energy)*10
     *
     * Takes in energy amount and the instance of the equivalent Energy
     * @param energy
     * @param recordedEnergy
     *
     * Returns the calculated points as an integer
     * Returns 0 if calculated points is negative
     * @return int
     */
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
