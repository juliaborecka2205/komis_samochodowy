package model;

import util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Dealership
{
    public static ArrayList<Car> flota = new ArrayList<>();
    public static ArrayList<Client> klienci = new ArrayList<>();
    public static HashMap<Car, Client> sprzedane = new HashMap<>();
    public static double utarg;
    public static Scanner sc = new Scanner(System.in);

    public static void DodajSamochod() throws Exception
    {
        System.out.print("---Dodawanie samochodu---\nPodaj markę: ");
        String ma = sc.nextLine();
        System.out.print("Podaj model: ");
        String mo = sc.nextLine();
        System.out.print("Podaj rok produkcji: ");
        int r = sc.nextInt();
        System.out.print("Podaj przebieg: ");
        double pr = sc.nextDouble();
        sc.nextLine();
        System.out.print("Podaj cenę: ");
        double c = sc.nextDouble();
        sc.nextLine();
        System.out.print("Podaj VIN: ");
        String VIN = sc.nextLine();
        System.out.print("Czy samochód jest dostępny do sprzedaży? [T]/[N]: ");
        boolean d = sc.nextLine().equalsIgnoreCase("t");
        System.out.print("Podaj kolor: ");
        String k = sc.nextLine();
        System.out.print("Podaj paliwo: ");
        String pa = sc.nextLine();
        System.out.print("Podaj nadwozie: ");
        String n = sc.nextLine();

        Car car = new Car(ma, mo, r, pr, c, VIN, d, k, pa, n);
        try {flota.add(car);}
        catch (Exception e) { throw new Exception("Dodawanie samochodu zakończyło się niepowodzeniem");}

        car.carID = flota.indexOf(car);

        System.out.print("Dodawanie samochodu zakończono pomyślnie\n");
        Utility.mainMenu();
    }
    public static void UsunSamochod(int carID) throws Exception
    {
        if (!flota.contains(flota.get(carID))) throw new IllegalArgumentException("Nie ma takiego samochodu we flocie");
        flota.remove(carID);
        System.out.print("Usunięto samochód z floty pomyślnie\n");
        Utility.mainMenu();
    }
    public static void SprzedajSamochod(int carID, int clientID) throws Exception
    {
        if (!flota.contains(flota.get(carID))) throw new Exception("Nie ma takiego samochodu we flocie");
        if (!klienci.contains(klienci.get(clientID))) throw new Exception("Nie ma takiego klienta w bazie");
        sprzedane.put(flota.get(carID), klienci.get(clientID));
        utarg += flota.get(carID).cena;
        flota.remove(carID);
        System.out.print("Sprzedano samochód pomyślnie\n");
        Utility.mainMenu();
    }
    public static void EdytujSamochod(int carID) throws Exception
    {
        System.out.print("---Edytowanie samochodu ID " + carID + "---\nPodaj nową markę: ");
        String ma = sc.nextLine();
        System.out.print("Podaj nowy model: ");
        String mo = sc.nextLine();
        System.out.print("Podaj nowy rok produkcji: ");
        int r = sc.nextInt();
        System.out.print("Podaj nowy przebieg: ");
        double pr = sc.nextDouble();
        System.out.print("Podaj nową cenę: ");
        double c = sc.nextDouble();
        sc.nextLine();
        System.out.print("Podaj nowy VIN: ");
        String VIN = sc.nextLine();
        System.out.print("Czy samochód jest dostępny do sprzedaży? [T]/[N]: ");
        boolean d = sc.nextLine().equalsIgnoreCase("t");
        System.out.print("Podaj nowy kolor: ");
        String k = sc.nextLine();
        System.out.print("Podaj nowe paliwo: ");
        String pa = sc.nextLine();
        System.out.print("Podaj nowe nadwozie: ");
        String n = sc.nextLine();

        Car car = new Car(ma, mo, r, pr, c, VIN, d, k, pa, n);
        try {
            flota.remove(carID);
            flota.add(carID, car);
        }
        catch (Exception e) { throw new Exception("Dodawanie samochodu zakończyło się niepowodzeniem");}

        car.carID = flota.indexOf(car);

        System.out.print("Dodawanie samochodu zakończono pomyślnie\n");
        Utility.mainMenu();
    }

    public static void DodajKlienta() throws Exception
    {
        System.out.print("---Dodawanie klienta---\nPodaj imię: ");
        String i = sc.nextLine();
        System.out.print("Podaj nazwisko: ");
        String n = sc.nextLine();
        System.out.print("Podaj e-mail: ");
        String e = sc.nextLine();
        System.out.print("Podaj numer telefonu: ");
        String nt = sc.nextLine();

        Client client = new Client(i, n, e, nt);
        try {klienci.add(client);}
        catch (Exception ex) { throw new Exception("Dodawanie klienta zakończyło się niepowodzeniem");}

        client.clientID = klienci.indexOf(client);

        System.out.print("Dodawanie klienta zakończono pomyślnie\n");
        Utility.mainMenu();

    }
    public static void UsunKlienta(int clientID) throws Exception
    {
        if (!klienci.contains(klienci.get(clientID)))
            throw new IllegalArgumentException("Nie ma takiego klienta w bazie danych");
        klienci.remove(clientID);
        System.out.print("Usunięto samochód z floty pomyślnie\n");
        Utility.mainMenu();
    }
    public static void EdytujKlienta(int clientID) throws Exception
    {
        System.out.print("---Edytowanie danych klienta---\nPodaj nowe imię: ");
        String i = sc.nextLine();
        System.out.print("Podaj nowe nazwisko: ");
        String n = sc.nextLine();
        System.out.print("Podaj nowy e-mail: ");
        String e = sc.nextLine();
        System.out.print("Podaj nowy numer telefonu: ");
        String nt = sc.nextLine();

        Client client = new Client(i, n, e, nt);
        try {
            klienci.remove(clientID);
            klienci.add(clientID, client);
        }
        catch (Exception ex) { throw new Exception("Edytowanie danych klienta zakończyło się niepowodzeniem");}

        client.clientID = klienci.indexOf(client);

        System.out.print("Dodawanie samochodu zakończono pomyślnie\n");
        Utility.mainMenu();
    }

    @Override
    public String toString()
    {
        return "----Informacje o komisie----\n* Liczba aut we flocie: " + flota.size() + "\n* Liczba klientów w bazie: "
        + klienci.size() + "\n* Liczba sprzedanych samochodów: " + sprzedane.keySet().size() + "\n* Utarg: " + utarg +
                "\n";
    }
}
