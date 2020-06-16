package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;
import kata.supermarket.ProductId;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class BuyOneGetOneFree implements DiscountScheme {

    static final DiscountSchemeName name = new DiscountSchemeName("buy-one-get-one-free");

    @Override
    public Discount apply(List<Item> items) {

        final Map<ProductId, List<ItemByUnit>> itemsByProduct = items
                .stream()
                .filter(ItemByUnit.class::isInstance)
                .map(ItemByUnit.class::cast)
                .collect(groupingBy(Item::productId));

        BigDecimal amount = itemsByProduct
                .entrySet()
                .stream()
                .map((entrySet) ->
                        calculate(entrySet.getValue())
                )
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return new Discount(name, amount);
    }

    private BigDecimal calculate(List<ItemByUnit> sameProductItems) {
        if (sameProductItems.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal price = sameProductItems.get(0).price();
            BigDecimal multiplier = new BigDecimal(sameProductItems.size() / 2);
            return price.multiply(multiplier);
        }
    }
}