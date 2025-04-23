package model;

public class Client {
    public int clientID;
    public String imie;
    public String nazwisko;
    public String numerTelefonu;
    public String email;

    private static final String EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
    private static final String PHONE_PATTERN = "^(\\+48\\s?)?(\\d{3}[\\s-]?\\d{3}[\\s-]?\\d{3})$";

    public Client(String imie, String nazwisko, String numerTelefonu, String email) {
        setImieNazwisko(imie, nazwisko);
        setNumerTelefonu(numerTelefonu);
        setEmail(email);
    }

    public void setImieNazwisko(String imie, String nazwisko)
    {
        if(imie.matches(".*\\d.*")) throw new IllegalArgumentException("Imię nie może zawierać cyfr");
        if(nazwisko.matches(".*\\d.*")) throw new IllegalArgumentException("Nazwisko nie może zawierać cyfr");
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    public void setNumerTelefonu(String numerTelefonu)
    {
        if (!numerTelefonu.matches(PHONE_PATTERN)) throw new IllegalArgumentException("Niepoprawny numer telefonu");
        this.numerTelefonu = numerTelefonu;
    }

    public void setEmail(String email)
    {
        if (!email.matches(EMAIL_PATTERN)) throw new IllegalArgumentException("Niepoprawny adres email");
        this.email = email;
    }

    @Override
    public String toString() {
        return "Imię: " + imie + "\n" +
                "Nazwisko: " + nazwisko + "\n" +
                "Telefon: " + numerTelefonu + "\n" +
                "Email: " + email + "\n";
    }
}
