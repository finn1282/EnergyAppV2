import java.time.LocalDate;

public class Energy {
    LocalDate date;
    double energy;
    double energySaved;

    public Energy(LocalDate currDate, double inputEnergy, User user){
        date = currDate;
        energy = inputEnergy;

        double previousEnergy = user.getPreviousEnergy(currDate);
        if(previousEnergy==0){
            energySaved=0;
        } else {
            energySaved = inputEnergy-previousEnergy;
        }

    }

    public double getEnergy(){
        return energy;
    }

    public LocalDate getDate(){
        return date;
    }
}
