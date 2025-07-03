import java.io.*;
import java.util.*;

public class Tips {

    //Stores an ArrayList of all the tips
    private final ArrayList<String> tips = new ArrayList<>();

    //Constructor, loads tips from file
    public Tips() throws IOException {
        //Loading tips file
        InputStream tipsFile = getClass().getResourceAsStream("tips.txt");
        BufferedReader tipsReader = new BufferedReader(new InputStreamReader(tipsFile));

        String line;

        //Reading tips from file
        while ((line = tipsReader.readLine()) != null){
            tips.add(line);
        }
    }

    /**
     *
     * Used to generate random tip
     * Generates a random number to get index of a tip from tips ArrayList
     *
     * Outputs the tip as a string
     * @return
     */
    public String getTips() {
        int tNo = (int)(Math.random() * tips.size());
        return tips.get(tNo);
    }
}