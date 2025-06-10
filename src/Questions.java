import java.io.*;
import java.util.*;

public class Questions {

    private final ArrayList<String> statement;
    private final int ansIndex;
    private final int NO_OF_QUESTIONS = 3;

    public Questions() throws IOException {
        File questionsFile = new File("src/questions.txt");
        FileReader fileReader = new FileReader(questionsFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";

        int qNo = (int) (Math.random() * NO_OF_QUESTIONS);


        for(int i=0;i<=qNo;i++){
            line = reader.readLine().replaceAll("\"", "");
        }

        statement = new ArrayList<>(Arrays.asList(line.split(",")));
        String question = statement.get(0);
        ansIndex = (int) ((Math.random() * 4)+1);
        String ans = statement.get(1);

        statement.remove(0);
        statement.remove(0);

        Collections.shuffle(statement);

        statement.add(0, question);
        statement.add(ansIndex, ans);

        for(int j=1;j<statement.size();j++){
            String temp = statement.get(j);
            statement.remove(j);
            temp = j+". "+temp;
            statement.add(j, temp);
        }
    }

    public ArrayList<String> getStatement(){
        return statement;
    }

    public boolean checkAnswer(int ans){
        return ans==ansIndex;
    }
}
