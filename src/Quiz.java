import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {

    private final User currUser;
    private final Scanner input;

    public Quiz(User user, Scanner in){
        currUser=user;
        input = in;
    }

    public void startQuiz(int noOfQ) throws IOException {
        for(int i=0;i<noOfQ;i++){
            Questions question = new Questions();
            ArrayList<String> statement =  question.getStatement();
            System.out.println(statement.get(0));
            for(int j=1;j<statement.size();j++){
                System.out.println(statement.get(j));
            }

            boolean answerSuccess = false;
            int answer=0;

            while(!answerSuccess){
                System.out.print("Enter the number that corresponds to your selection: ");

                try {
                    answer = Integer.parseInt(input.next());
                } catch (Exception ignored){}

                if (answer < 1 || answer > 4) {
                    System.out.println("Please enter a number within the range.");
                } else {
                    answerSuccess=true;
                }
            }
            boolean result = question.checkAnswer(answer);
            if(result){
                System.out.println("Congratulations you entered the correct answer! \nPoints +1!");
                currUser.addPoints(1);
            } else {
                System.out.println("Sorry, you entered the wrong answer.");
            }

            System.out.println();
        }
    }
}
