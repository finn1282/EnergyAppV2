import java.io.*;
import java.util.*;

public class Questions {

    //Question statement saved as ArrayList to be able to call shuffle method
    private final ArrayList<String> statement;
    //Position of correct answer saved
    private final int ansIndex;
    //Specifies total number of questions in questions.txt file
    private final int NO_OF_QUESTIONS = 20;

    /**
     * Constructor that generates question statement and shuffles options
     */
    public Questions() throws IOException {
        //Loading questions file
        InputStream questionsFile = getClass().getResourceAsStream("questions.txt");
        BufferedReader questionsReader = new BufferedReader(new InputStreamReader(questionsFile));

        String line = "";

        //Choosing a random question to ask
        int qNo = (int) (Math.random() * NO_OF_QUESTIONS);

        //Reading questions from file upto line based on random question number generated
        for(int i=0;i<=qNo;i++){
            //Removes quotations from question statement
            line = questionsReader.readLine().replaceAll("\"", "");
        }

        //Turning question statement into array of question and answers
        statement = new ArrayList<>(Arrays.asList(line.split(",")));
        String question = statement.get(0);

        //Generating position to place answer when options shuffled
        ansIndex = (int) ((Math.random() * 4)+1);
        String ans = statement.get(1);

        //Taking answer and question out of statement array to shuffle options
        statement.remove(0);
        statement.remove(0);

        //Shuffling options
        Collections.shuffle(statement);

        //Placing question and answer back into statement array
        statement.add(0, question);
        statement.add(ansIndex, ans);

        //Adding numbering to options
        for(int j=1;j<statement.size();j++){
            String temp = statement.get(j);
            statement.remove(j);
            temp = j+". "+temp;
            statement.add(j, temp);
        }
    }

    //Checks if user input is equal to answer
    public boolean checkAnswer(int ans){
        return ans==ansIndex;
    }

    public ArrayList<String> getStatement(){
        return statement;
    }

    public String getAnswer(){
        return statement.get(ansIndex);
    }
}
