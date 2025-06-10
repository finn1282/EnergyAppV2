import java.io.IOException;
import java.util.Scanner;

public class Main {

    static User currentUser;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        currentUser = new User("abc", "123", 3);

        System.out.println(currentUser.getPoints());

        Quiz q = new Quiz();
        q.startQuiz(2, input, currentUser);

        System.out.println(currentUser.getPoints());
    }
}