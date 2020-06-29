package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.ItemByWeight;
import kata.supermarket.ProductId;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

public class OneKiloOfVegForHalfPrice implements DiscountScheme {

    static final DiscountSchemeName name = new DiscountSchemeName("buy-one-kilo-of-vegetables-for-half-price");

    private final Set<ProductId> vegToDiscount;

    public OneKiloOfVegForHalfPrice(Set<ProductId> vegProducts) {
        this.vegToDiscount = vegProducts;

    }

    @Override
    public Discount apply(List<Item> items) {
        final Map<ProductId, List<ItemByWeight>> itemsByProduct = items
                .stream()
                .filter(ItemByWeight.class::isInstance)
                .map(ItemByWeight.class::cast)
                .collect(groupingBy(Item::productId));

        final BigDecimal combinedWeightInKilos = itemsByProduct
                .entrySet()
                .stream()
                .filter((entry) -> vegToDiscount.contains(entry.getKey()))
                .map((entry) -> entry.getValue().get(0).weightInKilos())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        final BigDecimal combinedPriceOfDiscountedVeg = itemsByProduct
                .entrySet()
                .stream()
                .filter((entry) -> vegToDiscount.contains(entry.getKey()))
                .map((entry) -> entry.getValue().get(0).price())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        BigDecimal discountApplied = BigDecimal.ZERO;

        if(combinedWeightInKilos.compareTo(BigDecimal.ONE) >= 0) {
            discountApplied = combinedPriceOfDiscountedVeg.divide(new BigDecimal("2"));
        }

        return new Discount(name, discountApplied.setScale(2, RoundingMode.HALF_UP));

    }
}
