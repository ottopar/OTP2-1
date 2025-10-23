import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Select language: 1) English  2) Finnish  3) Swedish  4) Japanese");
        int langChoice = readInt(scanner, "1");
        Locale locale;
        switch (langChoice) {
            case 2 -> locale = new Locale("fi", "FI");
            case 3 -> locale = new Locale("sv", "SE");
            case 4 -> locale = new Locale("ja", "JP");
            default -> locale = new Locale("en", "US");
        }

        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);

        System.out.println(rb.getString("enter.num.items"));
        int itemCount = readInt(scanner, rb.getString("invalid.number"));

        double cartTotal = 0.0;
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(MessageFormat.format(rb.getString("enter.price"), i));
            double price = readDouble(scanner, rb.getString("invalid.number"));

            System.out.println(MessageFormat.format(rb.getString("enter.quantity"), i));
            int qty = readInt(scanner, rb.getString("invalid.number"));

            double itemTotal = calcItemTotal(price, qty);
            cartTotal += itemTotal;
            System.out.println(MessageFormat.format(rb.getString("item.total"), i, currency.format(itemTotal)));
        }

        System.out.println(MessageFormat.format(rb.getString("cart.total"), currency.format(cartTotal)));
        System.out.println(rb.getString("goodbye"));
        scanner.close();
    }

    public static double calcItemTotal(double price, int quantity) {
        return price * quantity;
    }

    public static int readInt(Scanner scanner, String errorMsg) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double readDouble(Scanner scanner, String errorMsg) {
        while (true) {
            String line = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }
}
