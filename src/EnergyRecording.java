import java.time.LocalDate;
import java.util.Scanner;

public class EnergyRecording {

    private final User currUser;
    private final Scanner input;

    public EnergyRecording(User user, Scanner in){
        currUser=user;
        input=in;
    }

    public void recordEnergy(){

        LocalDate date = null;
        double energy = 0;

        boolean dateSuccess=false;
        while (!dateSuccess){
            System.out.print("Enter date in the form YYYY-MM-DD: ");
            try {
                date = LocalDate.parse(input.next());
            } catch (Exception ignored){
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

        currUser.recordEnergy(date, energy);
        int points = calculatePoints(date, energy);
        currUser.addPoints(points);

        System.out.println("Energy for "+date+" successfully recorded. \nYou gained "+points+" points! \n");
    }

    private int calculatePoints(LocalDate currDate, Double energy){
        double prevEnergy = currUser.getPreviousEnergy(currDate);
        if(prevEnergy==0||prevEnergy<energy){
            return 0;
        }
        return (int) (prevEnergy-energy)*10;
    }

}
