import java.io.*;
import java.util.*;

public class Tips {

    private final ArrayList<String> tips = new ArrayList<>();

    //constructor, loads tips from file
    public Tips() throws IOException {
        //loading quiz file
        File questionsFile = new File("data/tips.txt");
        FileReader fileReader = new FileReader(questionsFile);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        //reading tips from file
        while ((line = reader.readLine()) != null){
            tips.add(line);
        }
    }

    //outputs random tip
    public String getTips() {
        int tNo = (int)(Math.random() * tips.size());
        return tips.get(tNo);
    }
}