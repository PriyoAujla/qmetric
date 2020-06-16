package kata.supermarket.discount;

import com.google.common.collect.ImmutableSet;
import kata.supermarket.Item;
import kata.supermarket.testing.TestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static kata.supermarket.testing.ProductIdsForTesting.PintOfMilkId;
import static kata.supermarket.testing.TestData.multipleOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyOneGetOneFreeTest {

    @ParameterizedTest
    @MethodSource
    void itemHasExpectedDiscount(String expectedDiscountAmount, List<Item> items) {
        BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree(ImmutableSet.of(PintOfMilkId));

        Discount actualDiscount = buyOneGetOneFree.apply(items);
        Discount expectedDiscount = new Discount(BuyOneGetOneFree.name, new BigDecimal(expectedDiscountAmount));
        assertEquals(expectedDiscount.getName(), actualDiscount.getName());
        assertEquals(expectedDiscount.getAmount(), actualDiscount.getAmount());
    }

    static Stream<Arguments> itemHasExpectedDiscount() {
        return Stream.of(
                Arguments.of("0.00", multipleOf(TestData::aPackOfDigestives, 2)),
                Arguments.of("0.49", multipleOf(TestData::aPintOfMilk, 2)),
                Arguments.of("0.49", multipleOf(TestData::aPintOfMilk, 3)),
                Arguments.of("0.98", multipleOf(TestData::aPintOfMilk, 4))
        );
    }
}