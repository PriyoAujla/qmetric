package kata.supermarket.discount;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import kata.supermarket.Item;
import kata.supermarket.testing.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static kata.supermarket.testing.ProductIdsForTesting.CarrotsId;
import static kata.supermarket.testing.ProductIdsForTesting.PeasId;
import static kata.supermarket.testing.ProductIdsForTesting.PintOfMilkId;
import static kata.supermarket.testing.ProductIdsForTesting.SomeProductId;
import static kata.supermarket.testing.TestData.multipleOf;
import static kata.supermarket.testing.TestData.weightOfCarrots;
import static kata.supermarket.testing.TestData.weightOfPeas;
import static org.junit.jupiter.api.Assertions.*;

class OneKiloOfVegForHalfPriceTest {

    @DisplayName("discount provides it's total discount amount when items are...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void itemsHaveExpectedDiscount(String description, String expectedDiscountAmount, List<Item> items) {
        OneKiloOfVegForHalfPrice oneKiloOfVegForHalfPrice = new OneKiloOfVegForHalfPrice(ImmutableSet.of(
                CarrotsId,
                PeasId
        ));

        Discount actualDiscount = oneKiloOfVegForHalfPrice.apply(items);
        Discount expectedDiscount = new Discount(OneKiloOfVegForHalfPrice.name, new BigDecimal(expectedDiscountAmount));
        assertEquals(expectedDiscount.getName(), actualDiscount.getName());
        assertEquals(expectedDiscount.getAmount(), actualDiscount.getAmount());
    }

    static Stream<Arguments> itemsHaveExpectedDiscount() {
        return Stream.of(
                Arguments.of("Empty list of items has no discount applied", "0.00", Collections.EMPTY_LIST),
                Arguments.of("Items that are not of type weight have no discount applied", "0.00", multipleOf(TestData::aPackOfDigestives, 2)),
                Arguments.of("Multiple veg items with weight over one kilo have discount applied", "1.07", ImmutableList.of(
                        weightOfCarrots(new BigDecimal("1.00")),
                        weightOfPeas(new BigDecimal("0.5"))
                )),
                Arguments.of("More than one kilo of veg has discount applied", "0.80", ImmutableList.of(
                        weightOfCarrots(new BigDecimal("1.00"))
                ))

        );
    }
}