package kata.supermarket;

import java.math.BigDecimal;

public class Product extends AbstractProduct {

    private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit, final ProductId productId) {
        super(productId);
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
