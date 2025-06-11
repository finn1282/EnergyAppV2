import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class AllUsers {

    private final String filename = "src/save.txt";
    private final Scanner input;
    ArrayList<User> users = new ArrayList<>();

    public AllUsers(Scanner in){
        input=in;
    }

    public void saveUsers(){
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            for(User user:users) {
                outfile.writeObject(user);
            }
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

        User currentUser = null;

        boolean accountExists = false;
        while (!accountExists){
            System.out.print("Enter your username to sign in, or enter 0 to sign up: ");
            String username = input.next();
            if (username.equals("0")) {
                return null;
            }
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    accountExists = true;
                    currentUser = user;
                }
            }
            if(currentUser==null) {
                System.out.println("Account does not exist, or you entered the wrong username, please try again.");
            }
        }

        boolean passwordCorrect = false;
        while (!passwordCorrect){
            System.out.print("Enter your password: ");
            String password = input.next();
            if(!password.equals(currentUser.getPassword())){
                System.out.println("You have entered the wrong password!");
            } else {
                passwordCorrect=true;
                System.out.println("Successfully logged in");
            }
        }

        return currentUser;
    }

    public User signup() {

        boolean usernameAvailable = false;
        String uname=null;
        while(!usernameAvailable) {
            System.out.print("Enter username: ");
            uname = input.next();
            for(User user:users){
                if(uname.equals(user.getUsername())){
                    System.out.println("Username in use!");
                    usernameAvailable=false;
                    break;
                } else{
                    usernameAvailable=true;
                }
            }
        }

        System.out.print("Enter password: ");
        String pwd = input.next();
        int size=0;
        while (true) {
            System.out.print("Enter number of people in your household: ");
            try {
                size = Integer.parseInt(input.next());
            } catch (Exception e) {
                System.out.println("Please enter an integer value.");
            }
            if(size>0){
                break;
            }
        }
        User newUser = new User(uname,pwd,size);
        users.add(newUser);
        return newUser;
    }

    public void test(){
        for(User u:users){
            System.out.println(u.getUsername());
            System.out.println(u.getEnergyHistory().toString());
        }
    }
}
