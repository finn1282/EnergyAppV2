import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private int householdSize;
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

}
