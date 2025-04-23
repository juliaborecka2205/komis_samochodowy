package util;

import model.Car;
import model.Client;
import model.Dealership;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utility
{
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Car> flota = new ArrayList<>(Dealership.flota);
    public static ArrayList<Client> klienci = new ArrayList<>(Dealership.klienci);

    public static void mainMenu() throws Exception
    {
        System.out.print("""
                -----Menu główne-----
                Jaką akcję chcesz wykonać?
                [1] Dodać samochód
                [2] Usunąć samochód
                [3] Sprzedać samochód
                [4] Edytować samochód
                [5] Dodać klienta
                [6] Usunąć klienta
                [7] Edytować klienta
                [8] Wyświetlić flotę
                [9] Wyświetlić bazę klientów
                [10] Przefiltrować flotę
                [11] Zapisać dane z sesji do pliku
                [X] Wyjść z programu
                """);
        switch(sc.nextLine().toLowerCase())
        {
            case "1" -> Dealership.DodajSamochod();
            case "2" ->
            {
                System.out.print("Podaj ID samochodu, który chcesz usunąć: ");
                int tmp = sc.nextInt();
                Dealership.UsunSamochod(tmp);
            }
            case "3" ->
            {
                System.out.print("Podaj ID samochodu, który chcesz sprzedać: ");
                int cartmp = sc.nextInt();
                System.out.print("Podaj ID klienta: ");
                int clienttmp = sc.nextInt();
                Dealership.SprzedajSamochod(cartmp, clienttmp);
            }
            case "4" ->
            {
                System.out.print("Podaj ID samochodu, który chcesz edytować: ");
                int tmp = sc.nextInt();
                Dealership.EdytujSamochod(tmp);
            }
            case "5" -> Dealership.DodajKlienta();
            case "6" ->
            {
                System.out.print("Podaj ID klienta, którego chcesz usunąć: ");
                int tmp = sc.nextInt();
                Dealership.UsunKlienta(tmp);
            }
            case "7" ->
            {
                System.out.print("Podaj ID klienta, którego dane chcesz edytować: ");
                int tmp = sc.nextInt();
                Dealership.EdytujKlienta(tmp);
            }
            case "8" ->
            {
                System.out.print("----Flota komisu----");
                for (Car car : flota) System.out.print(car.toString());
                System.out.println();
            }
            case "9" ->
            {
                System.out.print("----Baza klientów komisu----");
                for (Client client : klienci) System.out.print(client.toString());
                System.out.println();
            }
            case "10" -> Filtrowanie(flota);
            case "11" -> FileManager.zapiszDokumentacje();
            case "x" -> System.exit(0);
        }
    }
    public static void Filtrowanie(ArrayList<Car> carsArr) throws Exception
    {
        System.out.print("""
        ----Filtrowanie----
        Podaj kategorię, według której chcesz filtrować flotę:
        [1] marka
        [2] model
        [3] rok produkcji
        [4] kolor
        [5] paliwo
        [6] nadwozie
        [X] Powrót do menu głównego
        """);
        String kat = sc.nextLine();
        if (kat.equalsIgnoreCase("x")) Utility.mainMenu();

        System.out.print("Podaj wartość, według której chcesz filtrować flotę: ");
        String wart = sc.nextLine();

        ArrayList<Car> filtered = carsArr.stream()
                .filter(car -> switch (Integer.parseInt(kat)) {
                    case 1 -> car.marka.equalsIgnoreCase(wart);
                    case 2 -> car.model.equalsIgnoreCase(wart);
                    case 3 -> Integer.toString(car.rokProd).equals(wart);
                    case 4 -> car.kolor.toString().equalsIgnoreCase(wart);
                    case 5 -> car.paliwo.toString().equalsIgnoreCase(wart);
                    case 6 -> car.nadwozie.toString().equalsIgnoreCase(wart);
                    default -> false;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.print("Oto lista samochodów odpowiadających Twoim kryteriom: ");
        for (Car car : filtered) System.out.print(car.toString());

        System.out.print("Czy chcesz przefiltrować wyniki? [T]/[N]: ");
        if (sc.nextLine().equalsIgnoreCase("t")) Filtrowanie(filtered);
        else if (sc.nextLine().equalsIgnoreCase("n")) Utility.mainMenu();
        else throw new Exception("Nieznana komenda");

    }
}
