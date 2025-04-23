package model;
import spec.SpecCatalogue;
import java.time.LocalDate;

public class Car
{
    public String marka, model;
    public int carID, rokProd;
    public double przebieg, cena;
    private String vin;
    public boolean dostepny = true;
    public SpecCatalogue.Kolor kolor;
    public SpecCatalogue.Paliwo paliwo;
    public SpecCatalogue.Nadwozie nadwozie;

    private static final String VIN_PATTERN = "[A-HJ-NPR-Z0-9]{17}";

    public Car(String marka, String model, int rokProd, double przebieg,
               double cena, String VIN, boolean dostepny, String kolor,
               String paliwo, String nadwozie) throws Exception
    {
        setMarkaModel(marka, model);
        setrokProd(rokProd);
        setPrzebiegCena(przebieg, cena);
        setVIN(VIN);
        setDostepnosc(dostepny);
        setKPN(kolor, paliwo, nadwozie);
    }

    public void setMarkaModel(String ma, String mo)
    {
        if(ma.matches(".*\\d.*")) throw new IllegalArgumentException("Marka nie może zawierać cyfr");
        this.marka = ma;
        this.model = mo;
    }
    public void setrokProd(int r) throws Exception
    {
        if (r < 1866 || r > LocalDate.now().getYear()) throw new Exception("Nieprawidłowy rok");
        this.rokProd = r;
    }
    public void setPrzebiegCena(double p, double c) throws Exception
    {
        if (p < 0) throw new Exception("Przebieg nie może być liczbą ujemną");
        if (c < 0) throw new Exception("Cena nie może być liczbą ujemną");
        this.przebieg = p;
        this.cena = c;
    }
    public String getVIN() { return vin;}
    public void setVIN(String vin) throws IllegalArgumentException
    {
        if (vin == null || !vin.matches(VIN_PATTERN)) throw new IllegalArgumentException("Niepoprawny numer VIN!");
        this.vin = vin.toUpperCase();
    }
    public void setDostepnosc(boolean d)
    {
        this.dostepny = d;
    }
    public void setKPN(String k, String p, String n) throws Exception
    {
        switch (k.toLowerCase())
        {
            case "czarny" -> this.kolor = SpecCatalogue.Kolor.czarny;
            case "biały", "bialy" -> this.kolor = SpecCatalogue.Kolor.bialy;
            case "czerwony" -> this.kolor = SpecCatalogue.Kolor.czerwony;
            case "niebieski" -> this.kolor = SpecCatalogue.Kolor.niebieski;
            case "żółty", "zolty" -> this.kolor = SpecCatalogue.Kolor.zolty;
            case "zielony" -> this.kolor = SpecCatalogue.Kolor.zielony;
            case "fioletowy" -> this.kolor = SpecCatalogue.Kolor.fioletowy;
            case "pomarańczowy", "pomaranczowy" -> this.kolor = SpecCatalogue.Kolor.pomaranczowy;
            default -> throw new Exception("Nieznana komenda");
        }
        switch (p.toLowerCase())
        {
            case "benzyna"-> this.paliwo = SpecCatalogue.Paliwo.benzyna;
            case "gaz" -> this.paliwo = SpecCatalogue.Paliwo.gaz;
            case "elektryk" -> this.paliwo = SpecCatalogue.Paliwo.elektryk;
            case "hybryda" -> this.paliwo = SpecCatalogue.Paliwo.hybryda;
            case "wodor" -> this.paliwo = SpecCatalogue.Paliwo.wodor;
            default -> throw new Exception("Nieznana komenda");
        }
        switch (n.toLowerCase())
        {
            case "hatchback" -> this.nadwozie = SpecCatalogue.Nadwozie.hatchback;
            case "sedan" -> this.nadwozie = SpecCatalogue.Nadwozie.sedan;
            case "suv" -> this.nadwozie = SpecCatalogue.Nadwozie.suv;
            case "roadster" -> this.nadwozie = SpecCatalogue.Nadwozie.roadster;
            case "van" -> this.nadwozie = SpecCatalogue.Nadwozie.van;
            case "minivan" -> this.nadwozie = SpecCatalogue.Nadwozie.minivan;
            case "limuzyna" -> this.nadwozie = SpecCatalogue.Nadwozie.limuzyna;
            default -> throw new Exception("Nieznana komenda");
        }
    }

    @Override
    public String toString()
    {
        return "----Dane samochodu ID" + this.carID + "----\n"+
                "Marka: " + this.marka + "\n" +
                "Model: " + this.model + "\n" +
                "Rok produkcji: " + this.rokProd + "\n" +
                "Przebieg: " + this.przebieg + " km\n" +
                "Cena: " + this.cena + " zł\n" +
                "VIN: " + this.vin + "\n" +
                "Dostępność: " + (this.dostepny ? "Dostępny" : "Sprzedany") + "\n" +
                "Kolor: " + this.kolor + "\n" +
                "Paliwo: " + this.paliwo + "\n" +
                "Nadwozie: " + this.nadwozie + "\n";
    }
}

