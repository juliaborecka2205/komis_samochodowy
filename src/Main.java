import java.time.LocalDate;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        System.out.print("-----|KOMIS " + LocalDate.now().getDayOfMonth() + "." +
                LocalDate.now().getMonth().getValue() + "." + LocalDate.now().getYear() +
                "|-----\n");
        util.Utility.mainMenu();
    }
}
