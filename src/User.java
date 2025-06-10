import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final int householdSize;
    private int points;
    private int level;
    private ArrayList<Energy> energyHistory = new ArrayList<>() ;

    public User(String uname, String pwd, int hSize){
        username = uname;
        password = pwd;
        householdSize = hSize;
        points = 0;
        level = 0;
    }

    public void addPoints(int addedPoints){
        points+=addedPoints;
    }

    public int getPoints(){
        return points;
    }

    public double getPreviousEnergy(LocalDate currDate) {
        double previousEnergy = 0;
        if(energyHistory.isEmpty()){
            return 0;
        } else {
            int compare = Integer.MAX_VALUE;
            for(Energy i:energyHistory){
                if(i.getDate().isBefore(currDate) && i.getDate().compareTo(currDate)<compare) {
                    compare = i.getDate().compareTo(currDate);
                    previousEnergy = i.getEnergy();
                }
            }
            return previousEnergy;
        }
    }

    public boolean checkExisting(LocalDate currDate){
        for(Energy i:energyHistory){
            if(currDate==i.getDate()){
                return true;
            }
        }
        return false;
    }

    public void recordEnergy(LocalDate currDate, double energy){
        energyHistory.add(new Energy(currDate, energy, this));
    }

    public void displayHistory(){
        sortHistory();
        for(int i=0;i<39;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        for(Energy e:energyHistory){
            System.out.printf("|%1$15tF|%2$10.1f|%3$10.1f|\n", e.getDate(), e.getEnergy(), e.getEnergySaved());
        }
        for(int i=0;i<39;i++){
            System.out.print("-");
        }
        System.out.print("\n");
    }

    private void sortHistory(){
        energyHistory.sort((i,j) -> j.getDate().compareTo(i.getDate()));
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Energy> getEnergyHistory() {
        return energyHistory;
    }
}
