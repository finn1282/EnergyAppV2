import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {

    public void startQuiz(int noOfQ, Scanner input, User user) throws IOException {
        for(int i=0;i<noOfQ;i++){
            Questions question = new Questions();
            ArrayList<String> statement =  question.getStatement();
            System.out.println(statement.get(0));
            for(int j=1;j<statement.size();j++){
                System.out.println(statement.get(j));
            }

            boolean answerAccepted = false;
            int answer=0;

            while(!answerAccepted){
                System.out.print("Enter the number that corresponds to your selection: ");

                try {
                    answer = Integer.parseInt(input.next());
                } catch (Exception ignored){
                }

                if (answer < 1 || answer > 4) {
                    System.out.println("Please enter a number within the range.");
                } else {
                    answerAccepted=true;
                }
            }
            boolean result = question.checkAnswer(answer);
            if(result){
                System.out.println("Congratulations you entered the correct answer! \nPoints +1!");
                user.addPoints(1);
            } else {
                System.out.println("Sorry, you entered the wrong answer.");
            }

            System.out.println();
        }
    }
}
