import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class AllUsers implements Serializable{

    //Set serial version for object serialization to avoid loading object error
    private static final long serialVersionUID = 1L;
    //Scanner to take input, transient to skip serialization as scanner needs to be inputted by local machine
    private transient Scanner input;
    //Store of all users ever created
    ArrayList<User> users = new ArrayList<>();

    //Sets the main scanner for input within the class
    public void setInput(Scanner in){
        input=in;
    }

    /**
     * Outputs the leaderboard and current ranking of the user
     *
     * Takes in the user to calculate ranking of that user, used for current user
     * @param user
     */
    public void leaderboard(User user){
        //Saves the rank of the user to be displayed
        int rank = 0;

        //Sorts users by descending points
        sortUsers();

        //Output formatting
        //Displays borders and page heading
        System.out.println();
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nLeaderboard\n\n");
        for(int i=0;i<27;i++){
            System.out.print("-");
        }
        //Leaderboard table headings
        System.out.printf("\n|%3s|%10s|%10s|\n", "", "User", "Points");
        for(int i=0;i<27;i++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Leaderboard table
        //Loops through every user, starts at 1 for display purposes
        for(int i=1;i<users.size()+1;i++) {
            User u = users.get(i-1);
            //Displays username and points
            System.out.printf("|%1$3d|%2$10s|%3$10d|\n", i, u.getUsername(), u.getPoints());
            //Finds the ranking of the current user
            if(u==user){
                rank = i;
            }
        }
        for(int i=0;i<27;i++){
            System.out.print("-");
        }
        System.out.print("\n");

        //Display rank of user from input
        System.out.println("Your ranking is "+rank+" place.");
        System.out.println();
    }

    /**
     *Sorts users by descending points
     */
    public void sortUsers(){
        //Custom sorting with comparator that compares points from 2 users to sort by descending order
        users.sort((i,j)->j.getPoints()-i.getPoints());
    }

    /**
     * Handles user login, takes username from input and verifies user exists, takes password from user and verifies password matches
     *
     * Takes in username and password from scanner input
     *
     * Returns the user object logged in
     * @return User
     */
    public User login(){
        //Initializes User variable
        User currentUser = null;

        //Checks if user is existing, asks for username input until user can be found
        //If 0 is entered, returns null to call signup method
        boolean accountExists = false;
        while (!accountExists){
            System.out.print("Enter your username to sign in, or enter 0 to sign up: ");
            String username = input.next();
            //Option if user wants to signup
            if (username.equals("0")) {
                return null;
            }
            //Loops through every user to find the user object that user is trying to log in to by matching username
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    accountExists = true;
                    currentUser = user;
                }
            }
            //If user is not found, display error message and ask for input again
            if(currentUser==null) {
                System.out.println("Account does not exist, or you entered the wrong username, please try again.");
            }
        }

        //Checks if password for user is correct
        //Loops until correct password is entered
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

    /**
     * Handles new user creation, asks for username and password from input
     * Makes sure that new username has not been used before
     *
     * Takes in username and password from scanner input
     *
     * Returns User object after created
     * @return User
     */
    public User signup() {
        boolean usernameAvailable = false;
        String uname=null;

        //Takes username input to create new user
        while(!usernameAvailable) {
            System.out.print("Enter username: ");
            uname = input.next();
            //If no other users, directly save user
            //Skips looping through users, as users array is empty
            if(users.isEmpty()){
                usernameAvailable=true;
                continue;
            }
            //Checks if username in use
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

        //Takes user password from input
        System.out.print("Enter password: ");
        String pwd = input.next();

        //Creating new user instance
        User newUser = new User(uname,pwd);

        //Adds user to list of existing users
        users.add(newUser);
        return newUser;
    }
}
