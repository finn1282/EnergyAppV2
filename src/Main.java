import java.io.IOException;
import java.util.Scanner;

public class Main {

    static User currentUser;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //Login/signup
        AllUsers users = new AllUsers(input);
        users.loadUsers();
        users.test();

        if((currentUser = users.login())==null){
            currentUser=users.signup();
        }

        //Main menu
        System.out.printf("Welcome %s!\n", currentUser.getUsername());
        System.out.printf("You have %d points \nYour level is %d", currentUser.getPoints(), currentUser.getLevel());
        System.out.println("(1)Enter energy, (2)History, (3)Leaderboard, (4)Exit");


        //Testing
        users.leaderboard(currentUser);

        users.saveUsers();

    }
}