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

}
