package pl.krax;

import java.util.*;

public class Main {
    private static final List<Coin> coins = new ArrayList<>();

    static {
        coins.add(new Coin(500, 1));  // 5 zł x 1 szt
        coins.add(new Coin(200, 3));  // 2 zł x 3 szt
        coins.add(new Coin(100, 5));  // 1 zł x 5 szt
        coins.add(new Coin(50, 10));  // 50 gr x 10 szt
        coins.add(new Coin(20, 20));  // 20 gr x 20 szt
        coins.add(new Coin(10, 200)); // 10 gr x 200 szt
        coins.add(new Coin(5, 100));  // 5 gr x 100 szt
        coins.add(new Coin(2, 100));  // 2 gr x 100 szt
        coins.add(new Coin(1, 10000));  // 1 gr x 10000 szt
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
        giveChangeRecursive(groszRemainder);
    }

    private static void giveChangeRecursive(int groszRemainder) {
        if (groszRemainder == 0) {
            return;
        }

        for (Coin coin : coins) {
            int denomination = coin.getValue();
            int amount = coin.getQuantity();

            if (groszRemainder >= denomination && amount > 0) {
                int dispensed = Math.min(groszRemainder / denomination, amount);
                groszRemainder -= dispensed * denomination;
                coin.reduceQuantity(dispensed);

                if (denomination >= 100) {
                    System.out.println("Wydaj " + dispensed + " monet " + denomination / 100 + " zł");
                } else {
                    System.out.println("Wydaj " + dispensed + " monet " + denomination + " gr");
                }
                break;
            }
        }
        giveChangeRecursive(groszRemainder);
    }
}
