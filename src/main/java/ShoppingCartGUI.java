import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCartGUI extends Application {
    private Locale locale;
    private ResourceBundle rb;
    private NumberFormat currency;
    private double cartTotal = 0.0;
    private int itemNumber = 1;

    @Override
    public void start(Stage primaryStage) {
        loadFonts();
        showLanguageSelection(primaryStage);
    }

    private void showLanguageSelection(Stage stage) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-font-family: 'Noto Sans CJK JP', 'MS Gothic', 'Yu Gothic', 'Hiragino Sans', sans-serif;");

        Label title = new Label("Select Language / Valitse kieli / Välj språk / 言語を選択");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button btnEnglish = new Button("English");
        Button btnFinnish = new Button("Suomi");
        Button btnSwedish = new Button("Svenska");
        Button btnJapanese = new Button("日本語");

        btnJapanese.setStyle("-fx-font-size: 12px;");

        btnEnglish.setOnAction(e -> startShopping(stage, new Locale("en", "US")));
        btnFinnish.setOnAction(e -> startShopping(stage, new Locale("fi", "FI")));
        btnSwedish.setOnAction(e -> startShopping(stage, new Locale("sv", "SE")));
        btnJapanese.setOnAction(e -> startShopping(stage, new Locale("ja", "JP")));

        layout.getChildren().addAll(title, btnEnglish, btnFinnish, btnSwedish, btnJapanese);

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.show();
    }

    private void startShopping(Stage stage, Locale selectedLocale) {
        this.locale = selectedLocale;
        this.rb = ResourceBundle.getBundle("MessageBundle", locale);
        this.currency = NumberFormat.getCurrencyInstance(locale);
        this.cartTotal = 0.0;
        this.itemNumber = 1;

        showShoppingInterface(stage);
    }

    private void showShoppingInterface(Stage stage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        if (locale.getLanguage().equals("ja")) {
            layout.setStyle("-fx-font-family: 'Noto Sans CJK JP', 'MS Gothic', 'Yu Gothic', sans-serif;");
        }

        Label lblPrice = new Label(MessageFormat.format(rb.getString("enter.price"), itemNumber));
        TextField txtPrice = new TextField();

        Label lblQuantity = new Label(MessageFormat.format(rb.getString("enter.quantity"), itemNumber));
        TextField txtQuantity = new TextField();

        Label lblTotal = new Label(MessageFormat.format(rb.getString("cart.total"), currency.format(cartTotal)));
        lblTotal.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Button btnAddItem = new Button(rb.getString("button.add"));
        Button btnFinish = new Button(rb.getString("goodbye"));

        btnAddItem.setOnAction(e -> {
            try {
                double price = parseDouble(txtPrice.getText());
                int quantity = Integer.parseInt(txtQuantity.getText().trim());

                double itemTotal = ShoppingCart.calcItemTotal(price, quantity);
                cartTotal += itemTotal;

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(MessageFormat.format(rb.getString("item.total"), itemNumber, currency.format(itemTotal)));
                applyFontToDialog(alert);
                alert.showAndWait();

                itemNumber++;
                lblPrice.setText(MessageFormat.format(rb.getString("enter.price"), itemNumber));
                lblQuantity.setText(MessageFormat.format(rb.getString("enter.quantity"), itemNumber));
                lblTotal.setText(MessageFormat.format(rb.getString("cart.total"), currency.format(cartTotal)));
                txtPrice.clear();
                txtQuantity.clear();
            } catch (NumberFormatException ex) {
                showError(rb.getString("invalid.number"));
            }
        });

        btnFinish.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(MessageFormat.format(rb.getString("cart.total"), currency.format(cartTotal)));
            alert.setContentText(rb.getString("goodbye"));
            applyFontToDialog(alert);
            alert.showAndWait();
            stage.close();
        });

        HBox buttons = new HBox(10, btnAddItem, btnFinish);
        buttons.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(lblPrice, txtPrice, lblQuantity, txtQuantity, buttons, new Separator(), lblTotal);

        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
    }

    private void loadFonts() {
        String[] japaneseFonts = {
                "MS Gothic", "MS Mincho", "Yu Gothic", "Meiryo",
                "Hiragino Sans", "Hiragino Kaku Gothic Pro",
                "Noto Sans CJK JP", "IPAGothic", "TakaoGothic"
        };

        for (String fontName : japaneseFonts) {
            if (Font.getFamilies().contains(fontName)) {
                Font.loadFont(fontName, 12);
                break;
            }
        }
    }

    private double parseDouble(String text) throws NumberFormatException {
        return Double.parseDouble(text.trim().replace(',', '.'));
    }

    private void applyFontToDialog(Alert alert) {
        if (locale.getLanguage().equals("ja")) {
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle("-fx-font-family: 'Noto Sans CJK JP', 'MS Gothic', 'Yu Gothic', sans-serif;");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        applyFontToDialog(alert);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
