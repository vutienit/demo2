package tien.example.demo2.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculate(BigDecimal amount);
}
