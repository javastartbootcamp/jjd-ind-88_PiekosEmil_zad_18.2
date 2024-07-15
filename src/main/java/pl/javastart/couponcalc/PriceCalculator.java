package pl.javastart.couponcalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.DoubleStream;

public class PriceCalculator {
    double totalSumOfProductPrices;

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        BigDecimal sum = null;
        Map<Double, Coupon> couponsDoubleMap = new TreeMap<>();
        if (coupons != null && products != null) {
            for (Coupon coupon : coupons) {
                totalSumOfProductPrices = 0;
                double discount = 1.0 - coupon.getDiscountValueInPercents() / 100.0;
                for (Product product : products) {
                    if (coupon.getCategory() != null && coupon.getCategory().equals(product.getCategory())) {
                        totalSumOfProductPrices += product.getPrice() * discount;
                    } else if (coupon.getCategory() == null) {
                        totalSumOfProductPrices += product.getPrice() * discount;
                    } else {
                        totalSumOfProductPrices += product.getPrice();
                    }
                }
                couponsDoubleMap.put(totalSumOfProductPrices, coupon);
                sum = findSuitablePrice(couponsDoubleMap);
            }
        } else {
            if (products == null) {
                return 0;
            }
            for (Product product : products) {
                totalSumOfProductPrices += product.getPrice();
            }
            return totalSumOfProductPrices;
        }
        return sum.doubleValue();
    }

    private static BigDecimal findSuitablePrice(Map<Double, Coupon> couponsDoubleMap) {
        OptionalDouble min = couponsDoubleMap.keySet().stream().flatMapToDouble(DoubleStream::of).min();
        return BigDecimal.valueOf(min.getAsDouble()).setScale(2, RoundingMode.HALF_DOWN);
    }
}