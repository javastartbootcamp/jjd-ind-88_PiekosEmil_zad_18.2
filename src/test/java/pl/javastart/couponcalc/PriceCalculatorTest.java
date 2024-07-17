package pl.javastart.couponcalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();

        // when
        double result = priceCalculator.calculatePrice(null, null);

        // then
        assertThat(result).isEqualTo(0.);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result).isEqualTo(5.99);
    }

    @Test
    public void shouldReturnPriceForCoupleProductsAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Jajka", 9.99, Category.FOOD));
        products.add(new Product("Mleko", 3.99, Category.FOOD));
        products.add(new Product("Żel do golenia", 7.99, Category.HOME));
        products.add(new Product("Piwo", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result).isEqualTo(33.95);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(4.79);
    }

    @Test
    public void shouldReturnPriceForCoupleProductsAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Mleko", 3.99, Category.FOOD));
        products.add(new Product("Chleb", 6.99, Category.FOOD));
        products.add(new Product("Banany", 7.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(19.97);
    }

    @Test
    public void shouldReturnLowestPriceForCoupleProductsAndCoupleCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Mleko", 3.99, Category.FOOD));
        products.add(new Product("Chleb", 6.99, Category.FOOD));
        products.add(new Product("Banany", 7.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 50));
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(12.48);
    }

    @Test
    public void shouldReturnLowestPriceForCoupleProductsAndCoupleCouponV2() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Mleko", 3.99, Category.FOOD));
        products.add(new Product("Chleb", 6.99, Category.FOOD));
        products.add(new Product("Banany", 7.99, Category.FOOD));
        products.add(new Product("Chusteczki", 9.99, Category.HOME));
        products.add(new Product("Żel do kąpieli", 14.99, Category.HOME));
        products.add(new Product("Ręcznik", 49.99, Category.HOME));
        products.add(new Product("Odświeżacz powietrza", 19.99, Category.HOME));
        products.add(new Product("Bańki mydlane", 2.99, Category.ENTERTAINMENT));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 50));
        coupons.add(new Coupon(Category.FOOD, 20));
        coupons.add(new Coupon(Category.HOME, 10));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(61.45);
    }

    @Test
    public void shouldReturnLowestPriceForCoupleProductsAndCoupleCouponV3() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Mleko", 3.99, Category.FOOD));
        products.add(new Product("Chleb", 6.99, Category.FOOD));
        products.add(new Product("Banany", 7.99, Category.FOOD));
        products.add(new Product("Chusteczki", 9.99, Category.HOME));
        products.add(new Product("Żel do kąpieli", 14.99, Category.HOME));
        products.add(new Product("Ręcznik", 49.99, Category.HOME));
        products.add(new Product("Odświeżacz powietrza", 19.99, Category.HOME));
        products.add(new Product("Bańki mydlane", 2.99, Category.ENTERTAINMENT));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 10));
        coupons.add(new Coupon(Category.FOOD, 20));
        coupons.add(new Coupon(Category.HOME, 50));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(75.43);
    }
}