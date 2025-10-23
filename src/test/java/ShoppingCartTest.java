import org.junit.jupiter.api.Test;

class ShoppingCartTest {
    ShoppingCart cart = new ShoppingCart();

    @Test
    void calcItemTotal() {
        assert(cart.calcItemTotal(10.0, 2) == 20.0);
        assert(cart.calcItemTotal(5.5, 4) == 22.0);
        assert(cart.calcItemTotal(0.0, 10) == 0.0);
    }

}
