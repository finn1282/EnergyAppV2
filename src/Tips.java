import java.io.*;
import java.util.*;

public class Tips {

    private final ArrayList<String> tips = new ArrayList<>();

    //constructor, loads tips from file
    public Tips() throws IOException {
        //loading quiz file
        InputStream tipsFile = getClass().getClassLoader().getResourceAsStream("tips.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(tipsFile));

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