package kata.supermarket.discount;

import kata.supermarket.Item;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPricingDiscount implements PricingDiscount {

    private final List<DiscountScheme> discountSchemes;

    public InMemoryPricingDiscount(List<DiscountScheme> discountSchemes) {
        this.discountSchemes = discountSchemes;
    }

    @Override
    public List<Discount> on(List<Item> items) {
        return discountSchemes
                .stream()
                .map(discountScheme -> discountScheme.apply(items))
                .collect(Collectors.toList());
    }
}
