package kata.supermarket;

import kata.supermarket.testing.PricingDiscountForTesting;
import kata.supermarket.testing.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static kata.supermarket.testing.TestData.aHalfKiloOfCarrots;
import static kata.supermarket.testing.TestData.aHalfKiloOfPeas;
import static kata.supermarket.testing.TestData.aPackOfDigestives;
import static kata.supermarket.testing.TestData.aPintOfMilk;
import static kata.supermarket.testing.TestData.multipleOf;
import static kata.supermarket.testing.TestData.twoFiftyGramsOfAmericanSweets;
import static kata.supermarket.testing.TestData.twoHundredGramsOfPickAndMix;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket(new PricingDiscountForTesting());
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                multipleItemsBuyOneGetOneFreeDiscount(),
                multipleItemsBuyOneKiloOfVegetablesForHalfPrice()
        );
    }

    private static Arguments multipleItemsBuyOneKiloOfVegetablesForHalfPrice() {
        return Arguments.of("a buy one kilo of vegetables for half price", "0.67",
                Arrays.asList(aHalfKiloOfCarrots(), aHalfKiloOfPeas())
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    static Arguments multipleItemsBuyOneGetOneFreeDiscount() {
        return Arguments.of("a buy one get one free discounted item", "0.49", multipleOf(TestData::aPintOfMilk, 2));
    }
}