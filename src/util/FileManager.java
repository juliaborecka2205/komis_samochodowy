package util;

import model.Car;
import model.Client;
import model.Dealership;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager
{
    public static ArrayList<Car> flota = new ArrayList<>(Dealership.flota);
    public static ArrayList<Client> klienci = new ArrayList<>(Dealership.klienci);
    public static HashMap<Car, Client> sprzedane = new HashMap<>(Dealership.sprzedane);
    public static double utarg = Dealership.utarg;

    public static void zapiszDokumentacje() throws Exception
    {
        String basePath = System.getProperty("user.home") + File.separator + "Desktop";
        String folderName = "KOMIS_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String path = basePath + File.separator + folderName;

        File folder = new File(path);

        try
        {
            if (!folder.exists() && !folder.mkdirs()) throw new IOException("Nie udało się utworzyć folderu: " + path);

            File plik = new File(folder, "dokumentacja.txt");

            try (FileWriter writer = new FileWriter(plik))
            {
                writer.append("----- Spis floty -----\n");
                for (Car car : flota) writer.append(car.toString()).append("\n");

                writer.append("\n----- Spis klientów -----\n");
                for (Client client : klienci) writer.append(client.toString()).append("\n");

                writer.append("\n----- Spis sprzedanych samochodów -----\n");
                for (Car car : sprzedane.keySet()) writer.append(car.toString())
                        .append("Zakupiony przez: ")
                        .append(String.valueOf(sprzedane.get(car)))
                        .append("\n");

                writer.append("\n----- UTARG na stan ")
                        .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss")))
                        .append(": ")
                        .append(String.valueOf(utarg))
                        .append(" zł -----\n");
            }

            System.out.println("Dokumentacja zapisana w: " + plik.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisu dokumentacji:");
        }

        Utility.mainMenu();
    }
}
