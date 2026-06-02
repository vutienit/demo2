package tien.example.demo2.strategy.impl;

import tien.example.demo2.strategy.DiscountStrategy;

import java.math.BigDecimal;

public class FlashsaleDiscountServiceImpl implements DiscountStrategy {
    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.9));
    }
}
