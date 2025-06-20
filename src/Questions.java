import java.io.*;
import java.util.*;

public class Questions {

    //question statement saved as ArrayList to be able to call shuffle method
    private final ArrayList<String> statement;
    private final int ansIndex;
    private final int NO_OF_QUESTIONS = 20;

    public Questions() throws IOException {
        //loading questions file
        InputStream questionsFile = getClass().getClassLoader().getResourceAsStream("questions.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(questionsFile));
        String line = "";

        //chooses a random question to ask
        int qNo = (int) (Math.random() * NO_OF_QUESTIONS);

        //reading questions from file based on random question number generated
        for(int i=0;i<=qNo;i++){
            line = reader.readLine().replaceAll("\"", "");
        }

        //turning question statement into array of question and answers
        statement = new ArrayList<>(Arrays.asList(line.split(",")));
        String question = statement.get(0);

        //generating position to place answer when options shuffled
        ansIndex = (int) ((Math.random() * 4)+1);
        String ans = statement.get(1);

        //taking answer and question out of statement array to shuffle options
        statement.remove(0);
        statement.remove(0);

        //shuffling options
        Collections.shuffle(statement);

        //placing question and answer back into statement array
        statement.add(0, question);
        statement.add(ansIndex, ans);

        //adding numbering to options
        for(int j=1;j<statement.size();j++){
            String temp = statement.get(j);
            statement.remove(j);
            temp = j+". "+temp;
            statement.add(j, temp);
        }
    }

    //checks if user input is equal to answer
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
