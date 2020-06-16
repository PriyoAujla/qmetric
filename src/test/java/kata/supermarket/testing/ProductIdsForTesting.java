package kata.supermarket.testing;

import kata.supermarket.Product;
import kata.supermarket.ProductId;

public enum ProductIdsForTesting implements ProductId {
    PintOfMilkId("pint-of-milk"),
    PackOfDigestivesId("pack-of-digestives"),
    AmericanSweetsId("american-sweets"),
    PickAndMixId("pick-and-mix"),
    SomeProductId("some-product-id");

    private final String id;

    ProductIdsForTesting(String id) {
        this.id = id;
    }

    @Override
    public String value() {
        return id;
    }
}
