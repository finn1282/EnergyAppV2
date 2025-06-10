import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static User currentUser;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        currentUser = new User("abc", "123", 3);
//
//        currentUser.recordEnergy(LocalDate.parse("2025-11-05"), 20);
//        currentUser.recordEnergy(LocalDate.parse("2025-11-06"), 10);
//        currentUser.recordEnergy(LocalDate.parse("2025-11-07"), 30);
//
//        currentUser.displayHistory();
        AllUsers users = new AllUsers();
        users.loadUsers();
        users.test();
        users.leaderboard(currentUser);

    }
}