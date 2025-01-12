import java.util.Random;  // Importujemy klasę Random do losowania słów
import java.util.Scanner; // Importujemy klasę Scanner do odczytywania danych wejściowych od użytkownika

 public class Wisielec {

    // Tablica z przykładami słów, które będą wykorzystywane w grze
    static String[] slowa = {"java", "programowanie", "komputer", "algorytm", "grafika", "sieci", "klepsydra"};

    // Metoda, która losuje jedno słowo z powyższej tablicy
    public static String losujSlowo() {
        Random rand = new Random(); // Tworzymy obiekt do losowania
        // Losujemy indeks w tablicy słów i zwracamy odpowiednie słowo
        return slowa[rand.nextInt(slowa.length)];
    }

    // Metoda rysująca wisielca w zależności od liczby błędów, które popełnił gracz
    public static void rysujWisielca(int bledy) {
        // W zależności od liczby błędów, rysujemy kolejne etapy wisielca
        switch (bledy) {
            case 0:
                System.out.println("  -----"); break;  // Brak błędów - tylko linia bazowa
            case 1:
                System.out.println("  -----\n  |   |"); break;  // 1 błąd - głowa
            case 2:
                System.out.println("  -----\n  |   |\n  O"); break;  // 2 błędy - dodajemy głowę
            case 3:
                System.out.println("  -----\n  |   |\n  O\n  |"); break;  // 3 błędy - rysujemy ciało
            case 4:
                System.out.println("  -----\n  |   |\n  O\n /|"); break;  // 4 błędy - rysujemy jedną rękę
            case 5:
                System.out.println("  -----\n  |   |\n  O\n /|\\" ); break;  // 5 błędów - rysujemy drugą rękę
            case 6:
                System.out.println("  -----\n  |   |\n  O\n /|\\\n / \\"); break;  // 6 błędów - rysujemy nogi, koniec gry
        }
    }

    // Główna metoda odpowiedzialna za rozpoczęcie gry
    public static void rozpocznijGre() {
        Scanner scanner = new Scanner(System.in); // Tworzymy obiekt scanner do wczytywania danych wejściowych

        // Losowanie słowa, które użytkownik będzie musiał odgadnąć
        String slowo = losujSlowo(); // Losujemy jedno słowo
        char[] zgadnieteSlowo = new char[slowo.length()];  // Tablica do przechowywania odgadniętych liter

        // Ustawiamy wszystkie litery w zgadywanym słowie na '_', które będzie widoczne dla gracza
        for (int i = 0; i < slowo.length(); i++) {
            zgadnieteSlowo[i] = '_'; // Na początku wszystkie litery są nieodgadnięte, więc ustawiamy '_'
        }

        int bledy = 0; // Zmienna do liczenia błędów użytkownika
        boolean graTrwa = true;  // Flaga, która kontroluje, czy gra nadal trwa
        System.out.println("Sebastian Ormański, Łódź, Grupa 5, Wisielec, nr albumu 123888 ");
        System.out.println("Spróbuj odgadnąć słowo!");  // Instrukcja dla gracza
        System.out.println("Możesz zgadywać literę lub całe słowo słowa są podane małymi literami.");  // Dalsze instrukcje dla gracza

        // Główna pętla gry, która trwa dopóki graTrwa jest prawdą i użytkownik nie popełni 6 błędów
        while (graTrwa && bledy < 6) {
            // Wyświetlamy aktualny stan zgadywanego słowa z literami i niewiadomymi
            System.out.println("\nSłowo do zgadnięcia: " + new String(zgadnieteSlowo));
            System.out.print("Podaj literę lub całe słowo: ");
            String input = scanner.next();  // Wczytujemy dane wejściowe od użytkownika

            // Sprawdzamy, czy użytkownik podał całe słowo
            if (input.length() > 1) {
                // Jeśli długość wpisanego słowa zgadza się z długością ukrytego słowa
                if (input.length() == slowo.length()) {
                    // Jeśli użytkownik odgadł całe słowo, porównujemy je z rzeczywistym słowem
                    if (input.equalsIgnoreCase(slowo)) {
                        System.out.println("\nGratulacje! Odgadłeś słowo: " + slowo);
                        graTrwa = false;  // Kończymy grę, bo użytkownik odgadł słowo
                    } else {
                        System.out.println("Niestety, to nie jest poprawne słowo.");
                        bledy++;  // Zwiększamy liczbę błędów, ponieważ odgadnięte słowo było niepoprawne
                        rysujWisielca(bledy);  // Rysujemy etap wisielca zależnie od liczby błędów
                    }
                } else {
                    // Jeśli długość wpisanego słowa nie zgadza się z długością ukrytego słowa
                    System.out.println("Słowo musi mieć długość " + slowo.length() + " liter.");
                }
            } else if (input.length() == 1) {
                // Jeśli użytkownik podał tylko jedną literę
                char litera = input.charAt(0);  // Pobieramy pierwszą literę z wprowadzonego ciągu
                boolean trafiona = false;  // Flaga, która oznacza, czy litera została znaleziona w słowie

                // Sprawdzamy, czy litera występuje w ukrytym słowie
                for (int i = 0; i < slowo.length(); i++) {
                    if (slowo.charAt(i) == litera) {  // Jeśli litera występuje na danej pozycji
                        zgadnieteSlowo[i] = litera;  // Zaktualizuj zgadywane słowo w tej pozycji
                        trafiona = true;  // Zmieniamy flagę na true, bo litera była trafiona
                    }
                }

                // Jeśli litera nie została trafiona
                if (!trafiona) {
                    bledy++;  // Zwiększamy liczbę błędów
                    rysujWisielca(bledy);  // Rysujemy kolejny etap wisielca
                }
            } else {
                // Jeśli użytkownik podał coś, co nie jest ani literą, ani słowem
                System.out.println("Proszę wpisać tylko jedną literę lub całe słowo.");
            }

            // Sprawdzamy, czy użytkownik odgadł całe słowo
            if (new String(zgadnieteSlowo).equals(slowo)) {
                System.out.println("\nGratulacje! Odgadłeś słowo: " + slowo);
                graTrwa = false;  // Kończymy grę, ponieważ użytkownik odgadł całe słowo
            }
        }

        // Jeśli użytkownik popełnił 6 błędów, kończymy grę
        if (bledy == 6) {
            System.out.println("\nPrzegrałeś! Słowo to było: " + slowo);
        }

        // Po zakończeniu gry pytamy, czy użytkownik chce zagrać ponownie
        System.out.println("\nNaciśnij Enter, aby zagrać ponownie, lub wpisz 'exit', aby zakończyć grę.");
        Scanner scanner2 = new Scanner(System.in);  // Tworzymy drugi scanner, aby wczytać odpowiedź
        String odp = scanner2.nextLine();  // Wczytujemy odpowiedź użytkownika

        if (odp.equalsIgnoreCase("exit")) {  // Jeśli użytkownik chce zakończyć
            System.out.println("Dziękujemy za grę!");  // Pożegnanie
        } else {
            // Jeśli użytkownik chce zagrać ponownie, uruchamiamy nową grę
            rozpocznijGre();
        }

    }

    // Metoda główna, która uruchamia grę
    public static void main(String[] args) {
        rozpocznijGre();  // Rozpoczynamy pierwszą grę
    }
}
