import java.io.*;
import java.util.Scanner;

public class Main {

    //Scanner to take in user input
    static Scanner input = new Scanner(System.in);
    //Sets the number of questions for quiz module
    static final int NO_OF_QUESTIONS = 5;
    //file name of save file
    static final String save = "save.ser";
    //AllUsers object to load all users, including current user
    static AllUsers users = new AllUsers();
    //Saves the instance of the current user logged in
    static User currentUser;
    //Initializes EnergyRecording, Quiz and Tips objects that interface with user
    static EnergyRecording energyRecording;
    static Quiz quiz;
    static Tips tip;


    public static void main(String[] args) throws IOException {
        //Loads user data from file
        loadUsers();
        //Passes scanner so users can take in inputs
        users.setInput(input);

        //Calls login method from users and saves the current user
        //If no user is found, calls signup method to create new user and saves that
        if((currentUser = users.login())==null){
            currentUser=users.signup();
        }

        //Initialising objects that handle user interaction with the current user and scanner input
        energyRecording = new EnergyRecording(currentUser,input);
        quiz = new Quiz(currentUser, input);
        //Tips may throw IOException, main method throws IOException
        tip = new Tips();

        //displays main menu, takes user action and handles further actions
        mainMenu();

        //saves user data on exit
        saveUsers();
    }

    /**
     * Method for displaying welcome message, points and level of the current user and randomly a generated tip
     * Also lets user choose next action between: 1)Enter energy, 2)Quiz, 3)History, 4)Leaderboard, 5)Exit
     * @throws IOException
     */
    public static void mainMenu() throws IOException {
        //Displays border
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        //Displays main menu information
        System.out.print("\nMain Menu\n\n");
        System.out.printf("Welcome %s!\n", currentUser.getUsername());
        System.out.printf("You have %d points \nYour level is %d\n", currentUser.getPoints(), currentUser.getLevel());
        System.out.println("Tip of the day:");
        //Gets random tip from tip object to display
        System.out.println(tip.getTips());
        System.out.println();

        //User input
        //Checks if user entered selection within the range
        boolean accepted = false;
        //Save which option the user input
        int choice = 0;
        //Do while to output selections once, then repeats until user input can be accepted
        do {
            //Outputs selections
            System.out.print("(1)Enter energy, (2)Quiz, (3)History, (4)Leaderboard, (5)Exit: ");
            try {
                choice = Integer.parseInt(input.next());
                //Checks if user input is within the accepted options
                if(choice>0&&choice<6) {
                    accepted = true;
                } else {
                    throw new IOException();
                }
            } catch (Exception e) {
                //Exception if user input isn't integer, or is thrown if user input is not acceptable
                System.out.println("Please enter one of the options.");
            }
        } while (!accepted);

        //Calls next action from input
        //Switch reduces code compared to if else as there are quite a few options
        switch (choice){
            //All the function calls return to main menu to ensure that user exits program through case 5(exit selection)
            case 1: energyRecording.recordEnergy();mainMenu();break;
            case 2: quiz.startQuiz(NO_OF_QUESTIONS);mainMenu();break;
            case 3: currentUser.displayHistory();mainMenu();break;
            case 4: users.leaderboard(currentUser);mainMenu();break;
            //exits mainMenu() loop
            case 5: System.out.println();System.out.println("Thank you for using the app.");break;
        }
    }

    /**
     * Serializes all user data from users object into save file, called on exit
     */
    public static void saveUsers(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(save));
            out.writeObject(users);
            System.out.println("User data saved.");
        } catch (IOException e) {
            System.out.println("Failed to save.");
        }
    }

    /**
     * Loads all user data from save file and saves as users object, called at start of program
     */
    public static void loadUsers(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(save));
            users = (AllUsers) in.readObject();
        } catch (EOFException ignored){
            //ignored because EOFException means file is empty, no previous save data
        } catch (Exception e){
            System.out.println("Users failed to load, please restart program.");
        }
    }
}