import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserCreation extends AllUsers{

    static Scanner scanner= new Scanner(System.in);
    static AllUsers users = new AllUsers();
    static ArrayList<User> userList;


    public static void main(String[] args) throws IOException {

        User jan = new User("jan","123");
        User feb = new User("feb","123");
        User mar = new User("mar","123");
        User apr = new User("apr","123");
        User may = new User("may","123");
        User jun = new User("jun","123");
        User jul = new User("jul","123");
        User aug = new User("aug","123");
        User sep = new User("sep","123");
        User oct = new User("oct","123");
        User nov = new User("nov","123");

        File questionsFile = new File("data/EnergyDataset.csv");
        FileReader fileReader = new FileReader(questionsFile);
        BufferedReader readerjan = new BufferedReader(fileReader);
        BufferedReader readerfeb = new BufferedReader(fileReader);
        BufferedReader readermar = new BufferedReader(fileReader);
        BufferedReader readerapr = new BufferedReader(fileReader);
        BufferedReader readermay = new BufferedReader(fileReader);
        BufferedReader readerjun = new BufferedReader(fileReader);
        BufferedReader readerjul = new BufferedReader(fileReader);
        BufferedReader readeraug = new BufferedReader(fileReader);
        BufferedReader readersep = new BufferedReader(fileReader);
        BufferedReader readeroct = new BufferedReader(fileReader);
        BufferedReader readernov = new BufferedReader(fileReader);

        for(int i=1;i<325;i++){
            String line=readerjan.readLine();
            if(line.matches(".*-01-.*")){
                EnergyRecording e = new EnergyRecording(jan,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-02-.*")){
                EnergyRecording e = new EnergyRecording(feb,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            } else if(line.matches(".*-03-.*")){
                EnergyRecording e = new EnergyRecording(mar,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-04-.*")){
                EnergyRecording e = new EnergyRecording(apr,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-05-.*")){
                EnergyRecording e = new EnergyRecording(may,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-06-.*")){
                EnergyRecording e = new EnergyRecording(jun,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-07-.*")){
                EnergyRecording e = new EnergyRecording(jul,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-08-.*")){
                EnergyRecording e = new EnergyRecording(aug,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-09-.*")){
                EnergyRecording e = new EnergyRecording(sep,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-10-.*")){
                EnergyRecording e = new EnergyRecording(oct,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }else if(line.matches(".*-11-.*")){
                EnergyRecording e = new EnergyRecording(nov,scanner);
                String[] lines = line.split(",");
                e.recordEnergy(LocalDate.parse(lines[0]),Double.parseDouble(lines[2]));
            }
        }
        users.addUser(jan);
        users.addUser(feb);
        users.addUser(mar);
        users.addUser(apr);
        users.addUser(may);
        users.addUser(jun);
        users.addUser(jul);
        users.addUser(aug);
        users.addUser(sep);
        users.addUser(oct);
        users.addUser(nov);

        try {
            FileOutputStream file = new FileOutputStream("save.ser");
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(users);
            outfile.close();
            file.close();
            System.out.println("User data saved.");
        } catch (IOException e) {
            System.out.println("Failed to save.");
        }

    }
}
