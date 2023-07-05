package pl.krax;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Integer, Integer> denominations = new HashMap<>();

    static {
        denominations.put(500, 1);  // 5 zł x 1szt
        denominations.put(200, 3);  // 2 zł x 3szt
        denominations.put(100, 5);  // 1 zł x 5szt
        denominations.put(50, 10);  // 50 gr x 10szt
        denominations.put(20, 20);  // 20 gr x 20szt
        denominations.put(10, 200); // 10 gr x 200szt
        denominations.put(5, 100);  // 5 gr x 100szt
        denominations.put(2, 100);  // 2 gr x 100szt
        denominations.put(1, 10000);  // 1 gr x 10000szt
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Podaj resztę (np. 1.30 zł), wpisz 'exit', aby zamknąć program: ");
            String changeToGive = scanner.nextLine();
            if (changeToGive.equals("exit")) {
                break;
            }
            if (!isValidAmount(changeToGive)) {
                System.out.println("Nieprawidłowa kwota. Podaj poprawną kwotę w formacie X.YY, np. 1.30");
                continue;
            }
            double change = Double.parseDouble(changeToGive);

            giveChange(change);
        }
    }
    private static boolean isValidAmount(String amount) {
        return amount.matches("\\d{1,3}(\\.\\d{2})?");
    }

    private static void giveChange(double change) {
        System.out.println("Dla reszty " + change + " zł:");

        int groszRemainder = (int) (change * 100);

        int[] coins = {500, 200, 100, 50, 20, 10, 5, 2, 1};

        for (int denomination : coins) {
            int amount = denominations.get(denomination);

            if (groszRemainder >= denomination) {
                int dispended = groszRemainder / denomination;
                if (dispended > amount) {
                    dispended = amount;
                }
                groszRemainder -= dispended * denomination;
                denominations.put(denomination, amount - dispended);

                if (denomination >= 100) {
                    System.out.println("Wydaj " + dispended + " monet " + denomination / 100.0 + " zł");
                } else {
                    System.out.println("Wydaj " + dispended + " monet " + denomination + " gr");
                }

                if (groszRemainder == 0) {
                    break;
                }
            }
        }
        if (groszRemainder > 0) {
            System.out.println("Nie ma wystarczającej ilości drobnych monet do wydania reszty. Program zostaje zamknięty!");
            System.exit(0);
        }
    }
}
