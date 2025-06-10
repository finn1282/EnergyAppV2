import java.time.LocalDate;
import java.util.Scanner;

public class EnergyRecording {

    private LocalDate date;

    public void recordEnergy(double energy, User user, Scanner input){

        boolean dateSuccess=false;
        while (!dateSuccess){
            System.out.print("Enter date: ");
            try {
                date = LocalDate.parse(input.next());
            } catch (Exception ignored){
                System.out.println("no");
                continue;
            }
            dateSuccess=true;
        }


        //Energy e = new Energy(date, energy, user);
    }

}
