import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {

    private final String username;
    private final String password;
    private int points;
    //ArrayList to hold all records for energy input
    private final ArrayList<Energy> energyHistory = new ArrayList<>() ;

    //Constructor, takes username and password to create new user, sets initial points to be 0
    public User(String uname, String pwd){
        username = uname;
        password = pwd;
        points = 0;
    }

    /**
     * Finds the previously recorded energy from the given date
     *
     * Inputs energy record reference to find the previous record
     * @param e
     *
     * Returns the double value of the previous energy
     * @return double
     */
    public double getPreviousEnergy(Energy e) {
        //Sorts energyHistory by descending date
        sortHistory();
        //If no previous energy recorded, returns 0
        if(energyHistory.isEmpty()){
            return 0;
        } else {
            //Finds position of reference energy
            int pos = energyHistory.indexOf(e);
            //Checks if no energy recorded before current entry
            if(pos==energyHistory.size()-1){
                return 0;
            }
            //Returns energy from the next record of energyHistory array
            //Because energyHistory is sorted by descending date, next record is from previous date
            return energyHistory.get(pos+1).getEnergy();
        }
    }

    /**
     * Checks if energy at the given date has been recorded before
     *
     * Takes in date to be checked
     * @param currDate
     *
     * Returns boolean value of existing or not
     * @return boolean
     */
    public boolean checkExisting(LocalDate currDate){
        //If no records in energyHistory, date is not existing
        if(energyHistory.isEmpty()){
            return false;
        }else {
            //Loops through energyHistory to find if any date matches input date
            for (Energy i : energyHistory) {
                if (currDate.isEqual(i.getDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates a new Energy instance and saves it
     *
     * Takes in date and energy to initialize Energy instance
     * @param currDate
     * @param energy
     *
     * Returns energy, but can be ignored
     * @return
     */
    public Energy recordEnergy(LocalDate currDate, double energy){
        //Calls Energy constructor
        Energy recordedEnergy = new Energy(currDate, energy, this);
        //Saves Energy to energyHistory array
        energyHistory.add(recordedEnergy);
        return recordedEnergy;
    }

    /**
     * Handles display of all the previously saved energy
     */
    public void displayHistory(){
        //Sorts recorded energy by descending date
        sortHistory();

        //Outputs formatting
        //Displays border and page heading
        System.out.println();
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nHistory\n\n");
        //History table headings
        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.printf("\n|%15s|%15s|%15s|\n", "Date", "Energy Used", "Energy Saved");
        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        //History table
        //Loops through energyHistory array
        for(int i=0;i<energyHistory.size();i++){
            Energy e = energyHistory.get(i);
            //Displays date, energy and energy saved
            System.out.printf("|%1$15tF|%2$15.1f|%3$15.1f|\n", e.getDate(), e.getEnergy(), e.getEnergySaved());
        }
        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.print("\n\n");
    }

    /**
     * Sorts recorded energy array by descending order
     */
    private void sortHistory(){
        energyHistory.sort((i,j) -> j.getDate().compareTo(i.getDate()));
    }

    /**
     * Method to add points to points variable
     *
     * Takes in int value of points to add
     * @param addedPoints
     */
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
