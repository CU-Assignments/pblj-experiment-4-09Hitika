import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Card {
    private String symbol;
    private int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return symbol + " " + number;
    }
}

public class CardManager {
    public static void main(String[] args) {
        List<Card> cards = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Collecting cards
        while (true) {
            System.out.print("Enter symbol (or 'stop' to finish): ");
            String symbol = scanner.nextLine().trim();

            if (symbol.equalsIgnoreCase("stop")) {
                break;
            }

            System.out.print("Enter number: ");
            int number = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            cards.add(new Card(symbol, number));
        }

        // Searching for cards by symbol
        System.out.print("Enter symbol to search: ");
        String searchSymbol = scanner.nextLine().trim();

        List<Card> matchingCards = cards.stream()
                .filter(card -> card.getSymbol().equalsIgnoreCase(searchSymbol))
                .collect(Collectors.toList());

        // Displaying results
        if (matchingCards.isEmpty()) {
            System.out.println("No cards found with symbol '" + searchSymbol + "'.");
        } else {
            System.out.println("Cards with symbol '" + searchSymbol + "':");
            matchingCards.forEach(System.out::println);
        }

        scanner.close();
    }
}