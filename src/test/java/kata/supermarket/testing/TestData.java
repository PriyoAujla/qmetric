package kata.supermarket.testing;

import com.google.common.collect.ImmutableList;
import kata.supermarket.Item;
import kata.supermarket.Product;
import kata.supermarket.WeighedProduct;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.addAll;
import static kata.supermarket.testing.ProductIdsForTesting.AmericanSweetsId;
import static kata.supermarket.testing.ProductIdsForTesting.CarrotsId;
import static kata.supermarket.testing.ProductIdsForTesting.PackOfDigestivesId;
import static kata.supermarket.testing.ProductIdsForTesting.PeasId;
import static kata.supermarket.testing.ProductIdsForTesting.PickAndMixId;
import static kata.supermarket.testing.ProductIdsForTesting.PintOfMilkId;
import static kata.supermarket.testing.ProductIdsForTesting.SomeProductId;


public class TestData {

    public static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), PintOfMilkId).oneOf();
    }

    public static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), PackOfDigestivesId).oneOf();
    }

    public static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), AmericanSweetsId);
    }

    public static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    public static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), PickAndMixId);
    }

    public static Item aHalfKiloOfCarrots() {
        BigDecimal halfKilo = new BigDecimal(".5");
        return weightOfCarrots(halfKilo);
    }

    public static Item weightOfCarrots(BigDecimal wightInKilos) {
        return new WeighedProduct(new BigDecimal("1.59"), CarrotsId).weighing(wightInKilos);
    }

    public static Item aHalfKiloOfPeas() {
        BigDecimal halfKilo = new BigDecimal(".5");
        return weightOfPeas(halfKilo);
    }

    public static Item weightOfPeas(BigDecimal halfKilo) {
        return new WeighedProduct(new BigDecimal("1.09"), PeasId).weighing(halfKilo);
    }

    public static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    public static Item someProduct() {
        return new Product(new BigDecimal("0.25"), SomeProductId).oneOf();
    }

    public static List<Item> multipleOf(Supplier<Item> itemSupplier, int howMany) {
        return ImmutableList.copyOf(Collections.nCopies(howMany, itemSupplier.get()));
    }
}

