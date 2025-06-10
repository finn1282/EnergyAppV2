import java.io.IOException;

public class Main {

    User currentUser;

    public static void main(String[] args) throws IOException {
//        User u1 = new User();

//        Quiz.startQuiz(currentUser);
        Questions q = new Questions();
        for(int i=0;i<5;i++){
            System.out.println(new Questions().getStatement());
        }
    }
}
