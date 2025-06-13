import java.io.IOException;
import java.util.Scanner;

public class Main {

    static User currentUser;
    static Scanner input = new Scanner(System.in);
    static AllUsers users = new AllUsers(input);
    static EnergyRecording energyRecording;
    static Quiz quiz;
    static final int NO_OF_QUESTIONS = 10;

    public static void main(String[] args) throws IOException {
        //Initialization
        users.loadUsers();

        //testing
        //users.test();
        //currentUser = users.test2();

        //login/signup
        if((currentUser = users.login())==null){
            currentUser=users.signup();
        }

        //initializing classes
        energyRecording = new EnergyRecording(currentUser,input);
        quiz = new Quiz(currentUser, input);


        //main menu
        mainMenu();

        //exit
        users.saveUsers();
    }

    public static void mainMenu() throws IOException {
        System.out.printf("Welcome %s!\n", currentUser.getUsername());
        System.out.printf("You have %d points \nYour level is %d\n", currentUser.getPoints(), currentUser.getLevel());

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
        switch (choice){
            case 1: energyRecording.recordEnergy();mainMenu();break;
            case 2: quiz.startQuiz(NO_OF_QUESTIONS);mainMenu();break;
            case 3: currentUser.displayHistory();mainMenu();break;
            case 4: users.leaderboard(currentUser);mainMenu();break;
            case 5: System.out.println("Thank you for using the app.");break;
        }
    }
}