import java.io.Serializable;
import java.time.LocalDate;

public class Energy implements Serializable {

    private final LocalDate date;
    private final double energy;
    private double energySaved;

    public Energy(LocalDate currDate, double inputEnergy, User user){
        date = currDate;
        energy = inputEnergy;

        double previousEnergy = user.getPreviousEnergy(currDate);
        if(previousEnergy==0){
            energySaved=0;
        } else {
            energySaved = previousEnergy-inputEnergy;
            if(energySaved<0){
                energySaved=0;
            }
        }

    }

    public double getEnergy(){
        return energy;
    }

    public LocalDate getDate(){
        return date;
    }

    public double getEnergySaved(){
        return energySaved;
    }
}
