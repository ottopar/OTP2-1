import org.junit.jupiter.api.Test;

class ShoppingCartTest {
    ShoppingCart cart = new ShoppingCart();

    @Test
    void calcItemTotal() {
        assert(cart.calcItemTotal(10.0, 2) == 20.0);
        assert(cart.calcItemTotal(5.5, 4) == 22.0);
        assert(cart.calcItemTotal(0.0, 10) == 0.0);
    }

    @Test
    void testEnglishBundle() {
        var rb = java.util.ResourceBundle.getBundle("MessageBundle", new java.util.Locale("en", "US"));
        assert(rb.getString("enter.num.items").equals("Enter number of items:"));
        assert(rb.getString("enter.price").equals("Enter price for item {0}:"));
        assert(rb.getString("enter.quantity").equals("Enter quantity for item {0}:"));
        assert(rb.getString("item.total").equals("Item {0} total: {1}"));
        assert(rb.getString("cart.total").equals("Total cost of shopping cart: {0}"));
        assert(rb.getString("invalid.number").equals("Invalid number, try again."));
        assert(rb.getString("goodbye").equals("Thank you. Goodbye!"));
    }

    @Test
    void testSwedishBundle() {
        var rb = java.util.ResourceBundle.getBundle("MessageBundle", new java.util.Locale("sv", "SE"));
        assert(rb.getString("enter.num.items").equals("Ange antalet varor:"));
        assert(rb.getString("enter.price").equals("Ange pris för vara {0}:"));
        assert(rb.getString("enter.quantity").equals("Ange antal för vara {0}:"));
        assert(rb.getString("item.total").equals("Vara {0} totalt: {1}"));
        assert(rb.getString("cart.total").equals("Totalkostnad för kundkorgen: {0}"));
        assert(rb.getString("invalid.number").equals("Ogiltigt nummer, försök igen."));
        assert(rb.getString("goodbye").equals("Tack. Hej då!"));
    }

    @Test
    void testFinnishBundle() {
        var rb = java.util.ResourceBundle.getBundle("MessageBundle", new java.util.Locale("fi", "FI"));
        assert(rb.getString("enter.num.items").equals("Anna ostettavien tuotteiden määrä:"));
        assert(rb.getString("enter.price").equals("Anna hinta tuotteelle {0}:"));
        assert(rb.getString("enter.quantity").equals("Anna määrä tuotteelle {0}:"));
        assert(rb.getString("item.total").equals("Tuote {0} yhteensä: {1}"));
        assert(rb.getString("cart.total").equals("Ostoskori yhteensä: {0}"));
        assert(rb.getString("invalid.number").equals("Virheellinen numero, yritä uudelleen."));
        assert(rb.getString("goodbye").equals("Kiitos. Näkemiin!"));
    }

    @Test
    void testJapaneseBundle() {
        var rb = java.util.ResourceBundle.getBundle("MessageBundle", new java.util.Locale("ja", "JP"));
        assert(rb.getString("enter.num.items").equals("購入する商品の数を入力してください:"));
        assert(rb.getString("enter.price").equals("商品 {0} の価格を入力してください:"));
        assert(rb.getString("enter.quantity").equals("商品 {0} の数量を入力してください:"));
        assert(rb.getString("item.total").equals("商品 {0} 合計: {1}"));
        assert(rb.getString("cart.total").equals("ショッピングカートの合計: {0}"));
        assert(rb.getString("invalid.number").equals("無効な数字です。もう一度入力してください。"));
        assert(rb.getString("goodbye").equals("ありがとうございます。さようなら!"));
    }

}
