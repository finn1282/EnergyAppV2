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

    //starts the quiz to ask user the specified number of questions
    public void startQuiz(int noOfQ) throws IOException {
        int pointsGained = 0;

        //displays instructions
        System.out.print("\n");
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nQuiz: Answer all 5 questions\n\n");

        //displays questions
        for(int i=1;i<noOfQ+1;i++){
            //generating new question
            Questions question = new Questions();
            ArrayList<String> statement =  question.getStatement();

            //displaying question and options
            System.out.printf("Question %d: %s\n", i, statement.get(0));
            for(int j=1;j<statement.size();j++){
                System.out.println(statement.get(j));
            }

            boolean answerSuccess = false;
            int answer=0;

            //takes user input, checks validity of input
            while(!answerSuccess){
                System.out.print("Enter the number that corresponds to your selection: ");

                try {
                    answer = Integer.parseInt(input.next());

                //exception ignored because invalid inputs caught in next if statement
                } catch (Exception ignored){}

                if (answer < 1 || answer > 4) {
                    System.out.println("Please enter a number within the range.");
                } else {
                    answerSuccess=true;
                }
            }

            //checks if answer input is correct
            boolean result = question.checkAnswer(answer);
            if(result){
                System.out.println("Congratulations you entered the correct answer! \nPoints +1!");
                currUser.addPoints(1);
                pointsGained++;
            } else {
                System.out.println("Sorry, you entered the wrong answer.");
                System.out.println("The correct answer is "+question.getAnswer());
            }

            System.out.println();
        }

        //end quiz
        System.out.println("Thank you for playing the quiz!");
        System.out.println("You gained a total of "+pointsGained+" points.");
    }
}
