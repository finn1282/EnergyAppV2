import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private int points;
    private final ArrayList<Energy> energyHistory = new ArrayList<>() ;

    //constructor, takes username and password to create new user, sets initial points to be 0
    public User(String uname, String pwd){
        username = uname;
        password = pwd;
        points = 0;
    }

    //finds the previously recorded energy from the given date
    public double getPreviousEnergy(Energy e) {
        sortHistory();
        //double previousEnergy = 0;
        if(energyHistory.isEmpty()){
            return 0;
        } else {
            int pos = energyHistory.indexOf(e);
            if(pos==energyHistory.size()-1){
                return 0;
            }
            return energyHistory.get(pos+1).getEnergy();
        }
    }

    //checks if energy at the given date has been recorded before
    public boolean checkExisting(LocalDate currDate){
        if(energyHistory.isEmpty()){
            return false;
        }else {
            for (Energy i : energyHistory) {
                if (currDate.isEqual(i.getDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    //creates a new Energy instance and saves it
    //returns energy, but can be ignored
    public Energy recordEnergy(LocalDate currDate, double energy){
        Energy recordedEnergy = new Energy(currDate, energy, this);
        energyHistory.add(recordedEnergy);
        return recordedEnergy;
    }

    //handles display of all the previously saved energy
    public void displayHistory(){
        //sorts recorded energy
        sortHistory();

        //outputs table of previous recorded energy
        System.out.println();
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nHistory\n\n");

        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.printf("\n|%15s|%15s|%15s|\n", "Date", "Energy Used", "Energy Saved");

        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.print("\n");

        //have to manually loop through array or concurrent modification exception will occur
        for(int i=0;i<energyHistory.size();i++){
            Energy e = energyHistory.get(i);
            System.out.printf("|%1$15tF|%2$15.1f|%3$15.1f|\n", e.getDate(), e.getEnergy(), e.getEnergySaved());
        }
        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.print("\n\n");
    }

    //sorts recorded energy array by descending order
    private void sortHistory(){
        energyHistory.sort((i,j) -> j.getDate().compareTo(i.getDate()));
    }

    //method to add points to points variable
    public void addPoints(int addedPoints){
        points+=addedPoints;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints(){
        return points;
    }

    public String getPassword() {
        return password;
    }

    //returns level after calculation
    public int getLevel() {
        return 1+(points / 100);
    }
}
