package kata.supermarket.discount;

import kata.supermarket.Item;

import java.util.List;

public interface DiscountScheme {
    Discount apply(List<Item> items);
}