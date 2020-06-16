package kata.supermarket.testing;

import com.google.common.collect.ImmutableSet;
import kata.supermarket.Item;
import kata.supermarket.discount.BuyOneGetOneFree;
import kata.supermarket.discount.Discount;
import kata.supermarket.discount.DiscountScheme;
import kata.supermarket.discount.InMemoryPricingDiscount;
import kata.supermarket.discount.PricingDiscount;

import java.util.Collections;
import java.util.List;

import static kata.supermarket.testing.ProductIdsForTesting.PintOfMilkId;

public class PricingDiscountForTesting implements PricingDiscount {

    private DiscountScheme buyOneGetOneFree = new BuyOneGetOneFree(ImmutableSet.of(
            PintOfMilkId
    ));

    private final InMemoryPricingDiscount delegate = new InMemoryPricingDiscount(
            Collections.singletonList(buyOneGetOneFree)
    );

    @Override
    public List<Discount> on(List<Item> items) {
        return delegate.on(items);
    }
}
