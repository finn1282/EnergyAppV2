import java.io.*;
import java.util.Scanner;

public class Main {

    static final String save = "data/save.ser";
    static User currentUser;
    static Scanner input = new Scanner(System.in);
    static AllUsers users = new AllUsers();
    static EnergyRecording energyRecording;
    static Quiz quiz;
    static final int NO_OF_QUESTIONS = 5;
    static Tips tip;

    public static void main(String[] args) throws IOException {
        //user data initialization
        loadUsers();
        users.setInput(input);

        //login/signup
        if((currentUser = users.login())==null){
            currentUser=users.signup();
        }

        //initializing classes
        energyRecording = new EnergyRecording(currentUser,input);
        quiz = new Quiz(currentUser, input);
        tip = new Tips();

        //main menu
        mainMenu();

        //exit
        saveUsers();
    }

    public static void mainMenu() throws IOException {
        //display
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nMain Menu\n\n");
        System.out.printf("Welcome %s!\n", currentUser.getUsername());
        System.out.printf("You have %d points \nYour level is %d\n", currentUser.getPoints(), currentUser.getLevel());
        System.out.println("Tip of the day:");
        System.out.println(tip.getTips());
        System.out.println();

        //user input
        boolean accepted = false;
        int choice = 0;
        do {
            System.out.print("(1)Enter energy, (2)Quiz, (3)History, (4)Leaderboard, (5)Exit: ");
            try {
                choice = Integer.parseInt(input.next());
                if(choice>0&&choice<6) {
                    accepted = true;
                } else {
                    throw new IOException();
                }
            } catch (Exception e) {
                System.out.println("Please enter one of the options.");
                //accepted=false;
            }
        } while (!accepted);

        //calling next functions from input
        switch (choice){
            case 1: energyRecording.recordEnergy();mainMenu();break;
            case 2: quiz.startQuiz(NO_OF_QUESTIONS);mainMenu();break;
            case 3: currentUser.displayHistory();mainMenu();break;
            case 4: users.leaderboard(currentUser);mainMenu();break;
            case 5: System.out.println();System.out.println("Thank you for using the app.");break;
        }
    }

    public static void saveUsers(){
        try {
            FileOutputStream file = new FileOutputStream(save);
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(users);
            outfile.close();
            file.close();
            System.out.println("User data saved.");
        } catch (IOException e) {
            System.out.println("Failed to save.");
        }
    }

    public static void loadUsers(){
        try {
            FileInputStream file = new FileInputStream(save);
            ObjectInputStream infile = new ObjectInputStream(file);
            users = (AllUsers) infile.readObject();
            infile.close();
            file.close();
            //System.out.println("Users successfully loaded.");

        //ignored because EOFExcpetion means file is empty, no previous save data
        } catch (EOFException ignored){
        } catch (Exception e){
            System.out.println("Users failed to load, please restart program.");
        }
    }
}