import java.util.ArrayList;
import java.io.*;

public class AllUsers {

    private final String filename = "src/save.txt";
    ArrayList<User> users = new ArrayList<>();

    public void saveUser(User user){
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(user);
            outfile.close();
            file.close();
            System.out.println("User has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save.");
        }
    }

    public void loadUsers(){
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream infile = new ObjectInputStream(file);
            User user;
            while((user  = (User) infile.readObject())!=null){
                users.add(user);
            }
            infile.close();
            file.close();
            System.out.println("Users successfully loaded.");
        } catch (Exception ignored){}
    }

    public void leaderboard(User user){
        int rank = 0;
        
        sortUsers();
        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        for(int i=1;i<users.size()+1;i++) {
            User u = users.get(i-1);
            System.out.printf("|%1$15d|%2$15s|%3$15d|\n", i, u.getUsername(), u.getPoints());
            if(u==user){
                rank = i;
            }
        }
        for(int i=0;i<49;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        System.out.println("Your ranking is "+rank+" place.");

    }

    public void sortUsers(){
        users.sort((i,j)->i.getPoints()-j.getPoints());
    }

    public User login(){

        return null;
    }

    public void test(){
        for(User u:users){
            System.out.println(u.getUsername());
            System.out.println(u.getEnergyHistory().toString());
        }
    }
}
