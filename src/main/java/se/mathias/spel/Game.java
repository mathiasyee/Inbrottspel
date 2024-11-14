package se.mathias.spel;

import java.util.Scanner;

@SuppressWarnings("ALL")
public class Game {
    static Scanner scanner = new Scanner(System.in);
    private final String VARDAGSRUMMET = "Vardagsrummet";
    private final String KÖKET = "Köket";
    private final String HALLEN = "Hallen";
    private final String SOVRUMMET = "Sovrummet";
    private final String KONTORET = "Kontoret";
    Resident resident = new Resident("Resident", 12, 3);
    Burglar burglar = new Burglar("Burglar", 12, 4);
    private String currentLocation = VARDAGSRUMMET;
    private boolean running = true;
    private boolean fryingPanFound = false;
    private boolean fightResolved = false;

    public void welcomeMessage() {
        System.out.println("*<-------------------------------------------------------->*");
        System.out.println("                  Välkommen till spelet!        ");
        System.out.println
                        ("            ( •_•)                     (•_• )\n  " +
                        "          ( ง )ง                     ୧( ୧ )\n   " +
                        "          /︶\\                       /︶\\     ");
        System.out.println("*<-------------------------------------------------------->*");
        System.out.println("Du somnade på soffan i vardagsrummet och vaknar nu upp av ett ovanligt ljud!");
        System.out.println("Du hör rörelser i hallen, du måste försöka hitta ett vapen i köket.");
        System.out.println("Din telefon är i kontoret men rånaren måste stoppas först innan du kan ringa polisen.");
    }

    public void roomDirection() {
        welcomeMessage();

        while (running) {
            if (currentLocation.equals(VARDAGSRUMMET)) {
                System.out.println("\nDu är i vardagsrummet. Välj det rum du vill gå till:");
                System.out.println("1. Köket");
                System.out.println("2. Hallen");
                System.out.println("3. Sovrummet");
                System.out.println("4. Kontoret");
                System.out.println("5. Avsluta");

                String userInput = scanner.nextLine().toLowerCase();
                switch (userInput) {
                    case "1", "köket" -> köket();
                    case "2", "hallen" -> hallen();
                    case "3", "sovrummet" -> sovrummet();
                    case "4", "kontoret" -> kontoret();
                    case "5", "avsluta" -> running = false;
                    default -> System.out.println("Felaktig inmatning! Välj ett giltigt alternativ.");
                }
            } else {
                System.out.println("Du är i " + currentLocation + ". Skriv 'vardagsrummet' för att återvända.");
                String userInput = scanner.nextLine().toLowerCase();

                if (userInput.equals("vardagsrummet")) {
                    currentLocation = VARDAGSRUMMET;
                } else {
                    System.out.println("Felaktig inmatning!");
                }
            }
        }
    }


    private void köket() {
        currentLocation = KÖKET;
        System.out.println("Du går in i köket. Du ser en stekpanna, vill du använda den som vapen?");
        System.out.println("Tryck 1 för att plocka stekpannan eller annat för att lämna den.");

        String input = scanner.nextLine();
        if (input.equals("1") && !fryingPanFound) {
            pickUpFryingPan();
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣀⠠⣤⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⣠⣾⣿⡿⠋⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⢀⣴⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⣀⣀⣠⣤⣤⠤⠤⠤⠤⠤⣤⣤⣬⣉⣉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⣯⣍⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣩⡽⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠉⠉⠙⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            fryingPanFound = true;
        } else if (fryingPanFound) {
            System.out.println("Du har redan plockat upp stekpannan innan.");
        } else if (!input.equals("1")) {
            System.out.println("Du tog inte upp stekpannan");
        }
    }


    private void hallen() {
        if (fightResolved) {
            System.out.println("Rånaren ligger medsvetslös här, du kan inte gå tillbaka hit.");
            return;
        }
        currentLocation = HALLEN;
        System.out.println("Du ser en rånare mitt i hallen. Han har ett knogjärn och kan övermanna dig om du är obeväpnad.");
        System.out.println(
                        "⠀⠀⠀⠀⠀⠀⠀⣀⣴⣶⣾⣷⣶⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⣿⡏⠀⠀⠀⢙⡏⠀⠀⠀ ⢹⣿⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⣿⣿⣶⣶⣶⣿⣿⣶⣶⣶⣾⣿⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢸⣿⣿⣿⠟⠛⠛⠛⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⢀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⡀⠀⠀⠀\n" +
                        "⢀⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠆\n" +
                        "⠙⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠀\n" +
                        "⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀");
        System.out.println("Vill du attackera rånaren eller fly?");
        System.out.println("Skriv 'attackera' för att slåss, eller skriv 'fly' för att undvika att slåss.");
        String fightInput = scanner.nextLine().toLowerCase();
        while (true) {
            switch (fightInput.toLowerCase()) {
                case "attackera":
                    executeAttack(resident, burglar);
                    return;

                case "fly":
                    runAway();
                    return;

                default:

                    System.out.println("Felaktigt val! Du måste välja mellan 'attackera' eller 'fly'.");

                    fightInput = scanner.nextLine();
            }
        }
    }

    private void sovrummet() {
        System.out.println("Inget verkar vara ovanligt här inne. Bäst att kolla annanstans!");
        currentLocation = SOVRUMMET;
    }

    private void kontoret() {
        currentLocation = KONTORET;
        int callPolice = -1;

        if (burglar.isConscious()) {
            System.out.println("Du måste stoppa rånaren innan du kan ringa polisen.");
        } else {
            System.out.println("Telefonen ligger på bordet, vill du ringa polisen?");
            System.out.println("Tryck 112 för att ringa polisen eller annan siffra för att låta bli att ringa.");
            System.out.println("Varning, ifall du inte ringer polisen kan rånaren vakna och du riskerar att förlora spelet!");


            while (true) {
                try {
                    callPolice = scanner.nextInt();

                    if (callPolice == 112) {
                        System.out.println("Polisen är nu på väg och du har vunnit spelet, grattis!");
                        System.out.println("Du är nu säker och spelet avslutas. Tack för att du spelade!");
                        System.out.println("＼(^o^)／ \uD83C\uDFC6");
                        break;
                    } else {
                        System.out.println("Du lät bli att ringa polisen, efter en stund vaknar rånaren och knockar dig.");
                        System.out.println("Du förlorade för att du inte ringde polisen, Game over!");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Ogiltig inmatning. Vänligen skriv in ett nummer.");
                    scanner.nextLine();
                }
            }
            running = false;
        }
    }

    private void pickUpFryingPan() {
        System.out.println("Du plockar upp stekpannan. Din damage ökas med 3!");
        System.out.println("Du är nu redo att möta rånaren.");
        resident.addDamage(3);
    }

    private void runAway() {
        System.out.println("Du backar sakta från rånaren.");
        currentLocation = HALLEN;
    }

    private void executeAttack(Entity resident, Entity burglar) {
        System.out.println("Du och rånaren hamnar i en slagsmål!");

        while (resident.isConscious() && burglar.isConscious()) {
            resident.punch(burglar);
            if (!burglar.isConscious()) {
                System.out.println("Med din stekpanna lyckades du övermanna rånaren!");
                System.out.println("Nu när rånaren är nere kan du ringa polisen i kontoret");
                fightResolved = true;
                break;
            }
            burglar.punch(resident);
            if (!resident.isConscious()) {
                System.out.println("Rånaren lyckades övermanna dig.");
                System.out.println("Du har förlorat. Game over!");
                running = false;
                break;
            }
        }
    }

}



