import java.io.Serializable;
import java.time.LocalDate;

public class Energy implements Serializable {

    private final LocalDate date;
    private final double energy;
    //Saves the amount of energy saved from the previous record
    private double energySaved;
    //Saves the current user for method calls
    private final User currentUser;

    //Constructor, takes in date and energy to record
    public Energy(LocalDate currentDate, double inputEnergy, User user){
        date = currentDate;
        energy = inputEnergy;
        currentUser=user;

        //calls method to calculate energy saved
        energySaved = calculateEnergySaved(inputEnergy);
    }

    /**
     *
     * Finds the previous energy that the current user has saved and calculates difference from the previous energy amount
     *
     * Takes in current energy amount
     * @param currentEnergy
     *
     * Returns the difference in energy
     * @return double
     */
    public double calculateEnergySaved(double currentEnergy){
        //Calls method from current user to get the previous energy recorded
        double previousEnergy = currentUser.getPreviousEnergy(this);

        //If no energy previously recorded
        if(previousEnergy==0){
            energySaved = 0;
        } else {
            energySaved = previousEnergy-currentEnergy;
            //If energySaved negative means more energy used, so no energy saved
            if(energySaved<0) {
                energySaved=0;
                return 0;
            }
        }
        return energySaved;
    }

    public double getEnergy(){
        return energy;
    }

    public LocalDate getDate(){
        return date;
    }

    public double getEnergySaved(){
        calculateEnergySaved(energy);
        return energySaved;
    }
}
