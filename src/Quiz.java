import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {

    //Saves current user for method calls
    private final User currUser;
    //Saves scanner to take input for methods
    private final Scanner input;

    //Constructor, loads current user and scanner
    public Quiz(User user, Scanner in){
        currUser=user;
        input = in;
    }

    //starts the quiz to ask user the specified number of questions

    /**
     *
     * Main function to start quiz and ask users random questions for the specified amount of questions
     *
     *Takes in number of questions the quiz should ask
     * @param noOfQ
     */
    public void startQuiz(int noOfQ) throws IOException {
        //Initialize points gained while playing quiz
        int pointsGained = 0;

        //Displays instructions
        System.out.print("\n");
        for(int i=0;i<80;i++){
            System.out.print("-");
        }
        System.out.print("\nQuiz: Answer all 5 questions\n\n");

        //Displays questions for the specified number of times
        for(int i=1;i<noOfQ+1;i++){
            //Generating new instance of Questions
            Questions question = new Questions();
            //Gets the question statement from question instance
            ArrayList<String> statement =  question.getStatement();

            //Displaying question and options
            System.out.printf("Question %d: %s\n", i, statement.get(0));
            for(int j=1;j<statement.size();j++){
                System.out.println(statement.get(j));
            }

            //Initializing variables to check if user input is true or false
            boolean answerSuccess = false;
            int answer=0;

            //Takes user input, checks validity of input
            while(!answerSuccess){
                System.out.print("Enter the number that corresponds to your selection: ");

                try {
                    answer = Integer.parseInt(input.next());

                //exception ignored because invalid inputs caught in next if statement
                } catch (Exception ignored){}

                //Checks if input is not in acceptable range, also catches error if input is not int
                if (answer < 1 || answer > 4) {
                    System.out.println("Please enter a number within the range.");
                } else {
                    answerSuccess=true;
                }
            }

            //Checks if answer input is correct by calling method from Question object
            boolean result = question.checkAnswer(answer);
            if(result){
                //If answer is correct, display congratulation message and add to points counter
                System.out.println("Congratulations you entered the correct answer! \nPoints +1!");
                currUser.addPoints(1);
                pointsGained++;
            } else {
                System.out.println("Sorry, you entered the wrong answer.");
                System.out.println("The correct answer is "+question.getAnswer());
            }

            System.out.println();
        }

        //End quiz
        System.out.println("Thank you for playing the quiz!");
        System.out.println("You gained a total of "+pointsGained+" points.");
    }
}