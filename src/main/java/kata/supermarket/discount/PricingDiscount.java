package kata.supermarket.discount;

import kata.supermarket.Item;

import java.util.List;

public interface PricingDiscount {
    List<Discount> on(List<Item> items);
}
