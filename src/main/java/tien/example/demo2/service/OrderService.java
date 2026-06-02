package tien.example.demo2.service;

import java.math.BigDecimal;

public interface OrderService {
    BigDecimal createOrder(BigDecimal amount, String method, String discountType);
}
