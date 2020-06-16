package kata.supermarket.discount;

import kata.supermarket.common.HasEqualsAndHashCode;

import java.math.BigDecimal;

public class Discount extends HasEqualsAndHashCode {
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