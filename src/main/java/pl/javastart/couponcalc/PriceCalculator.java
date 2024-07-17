package pl.javastart.couponcalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        BigDecimal lowestTotalPrice;
        if (coupons != null && products != null) {
            lowestTotalPrice = calculateLowestPrice(products, coupons);
        } else {
            if (products == null) {
                return 0;
            }
            return calculateTotalPriceWithoutCoupons(products);
        }
        return lowestTotalPrice.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    private static double calculateTotalPriceWithoutCoupons(List<Product> products) {
        double totalSumOfProductPrices = 0;
        for (Product product : products) {
            totalSumOfProductPrices += product.getPrice();
        }
        return totalSumOfProductPrices;
    }

    private static BigDecimal calculateLowestPrice(List<Product> products, List<Coupon> coupons) {
        BigDecimal lowestTotalPrice = new BigDecimal(Integer.MAX_VALUE);
        for (Coupon coupon : coupons) {
            double totalSumOfProductPrices = 0;
            double discount = 1.0 - coupon.getDiscountValueInPercents() / 100.0;
            for (Product product : products) {
                if (coupon.getCategory() != null && coupon.getCategory() == product.getCategory()) {
                    totalSumOfProductPrices += product.getPrice() * discount;
                } else if (coupon.getCategory() == null) {
                    totalSumOfProductPrices += product.getPrice() * discount;
                } else {
                    totalSumOfProductPrices += product.getPrice();
                }
            }
            if (lowestTotalPrice.compareTo(BigDecimal.valueOf(totalSumOfProductPrices)) > 0) {
                lowestTotalPrice = BigDecimal.valueOf(totalSumOfProductPrices);
            }
        }
        return lowestTotalPrice;
    }
}
