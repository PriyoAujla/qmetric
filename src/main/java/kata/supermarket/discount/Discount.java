package kata.supermarket.discount;

import java.math.BigDecimal;

public class Discount {
    private final DiscountSchemeName name;
    private final BigDecimal amount;

    public Discount(DiscountSchemeName name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public DiscountSchemeName getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}