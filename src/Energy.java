import java.io.Serializable;
import java.time.LocalDate;

public class Energy implements Serializable {

    private final LocalDate date;
    private final double energy;
    private double energySaved;
    private final User currentUser;

    //constructor, takes in date to record and energy
    public Energy(LocalDate currentDate, double inputEnergy, User user){
        date = currentDate;
        energy = inputEnergy;
        currentUser=user;

        //calculates energy saved
        energySaved = calculateEnergySaved(inputEnergy);
    }

    //calculates energy saved based on previous energy records
    public double calculateEnergySaved(double currentEnergy){
        //gets the previous energy recorded
        double previousEnergy = currentUser.getPreviousEnergy(this);

        if(previousEnergy==0){
            energySaved = 0;
        } else {
            energySaved = previousEnergy-currentEnergy;
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
