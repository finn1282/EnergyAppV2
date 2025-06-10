import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    private final String username;
    private final String password;
    private final int householdSize;
    private int points;
    private int level;
    private ArrayList<Energy> energyHistory;

    public User(String uname, String pwd, int hSize){
        username = uname;
        password = pwd;
        householdSize = hSize;
        points = 0;
        level = 0;
        energyHistory = new ArrayList<Energy>();
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
}
