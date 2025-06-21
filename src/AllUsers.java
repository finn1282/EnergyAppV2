import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class AllUsers implements Serializable{

    private static final long serialVersionUID = 1L;
    private transient Scanner input;
    ArrayList<User> users = new ArrayList<>();

    //sets the main scanner for input in the class
    public void setInput(Scanner in){
        input=in;
    }

    //outputs the leaderboard and current ranking of the user
    public void leaderboard(User user){
        //variable to find rank of user
        int rank = 0;

        //sorts users
        sortUsers();

        //output formatting
        System.out.println();
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nLeaderboard\n\n");
        for(int i=0;i<27;i++){
            System.out.print("-");
        }
        System.out.printf("\n|%3s|%10s|%10s|\n", "", "User", "Points");
        for(int i=0;i<27;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        for(int i=1;i<users.size()+1;i++) {
            User u = users.get(i-1);
            System.out.printf("|%1$3d|%2$10s|%3$10d|\n", i, u.getUsername(), u.getPoints());
            //finds the ranking of the current user
            if(u==user){
                rank = i;
            }
        }
        for(int i=0;i<27;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        System.out.println("Your ranking is "+rank+" place.");
        System.out.println();
    }

    //sorts user by descending points
    public void sortUsers(){
        users.sort((i,j)->j.getPoints()-i.getPoints());
    }

    //handles user login
    public User login(){
        User currentUser = null;

        //checks if user is existing
        boolean accountExists = false;
        while (!accountExists){
            System.out.print("Enter your username to sign in, or enter 0 to sign up: ");
            String username = input.next();
            //option if user wants to create account
            if (username.equals("0")) {
                return null;
            }
            //finds the user object that user is trying to login to
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

        //input and checking of user password
        boolean passwordCorrect = false;
        while (!passwordCorrect){
            System.out.print("Enter your password: ");
            String password = input.next();
            if(!password.equals(currentUser.getPassword())){
                System.out.println("You have entered the wrong password!");
            } else {
                passwordCorrect=true;
                System.out.println("Successfully logged in");
                System.out.println();
            }
        }

        return currentUser;
    }

    //handles creating new user
    public User signup() {

        boolean usernameAvailable = false;
        String uname=null;

        //takes username input to create new user
        while(!usernameAvailable) {
            System.out.print("Enter username: ");
            uname = input.next();
            if(users.isEmpty()){
                usernameAvailable=true;
                continue;
            }
            //checks if username in use
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

        //takes user password
        System.out.print("Enter password: ");
        String pwd = input.next();

        //creating new user instance
        User newUser = new User(uname,pwd);

        //adds user to list of existing users
        users.add(newUser);
        return newUser;
    }
}
