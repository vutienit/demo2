package tien.example.demo2.service.impl;

import tien.example.demo2.factory.DiscountFactory;
import tien.example.demo2.factory.PaymentFactory;
import tien.example.demo2.service.OrderService;
import tien.example.demo2.service.PaymentService;
import tien.example.demo2.strategy.DiscountStrategy;
import tien.example.demo2.strategy.impl.WholesaleDiscountServiceImpl;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {
    final PaymentFactory paymentFactory;
    final DiscountFactory discountFactory;

    public OrderServiceImpl(PaymentFactory paymentFactory, DiscountFactory discountFactory) {
        this.paymentFactory = paymentFactory;
        this.discountFactory = discountFactory;
    }

    @Override
    public BigDecimal createOrder(BigDecimal amount, String method, String discountType) {
        // Cách gọi đơn giản từ Strategy
        DiscountStrategy discountStrategy = new WholesaleDiscountServiceImpl();
        discountStrategy.calculate(amount);

        // Cách gọi bằng factory, từ factory sẽ có logic để lựa chọn Strategy
        DiscountStrategy discountStrategy1 = discountFactory.getDiscountStrategy(discountType);
        discountStrategy1.calculate(amount);

        PaymentService paymentService = paymentFactory.paymentService(method);
        paymentService.pay(amount);
        return null;
    }
}
