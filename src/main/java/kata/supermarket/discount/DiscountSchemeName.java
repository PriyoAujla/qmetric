package kata.supermarket.discount;

import kata.supermarket.common.HasEqualsAndHashCode;

public class DiscountSchemeName extends HasEqualsAndHashCode {
    private final String value;

    public DiscountSchemeName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}