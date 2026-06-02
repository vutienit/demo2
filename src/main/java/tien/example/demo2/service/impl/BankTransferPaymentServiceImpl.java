package tien.example.demo2.service.impl;

import tien.example.demo2.service.PaymentService;

import java.math.BigDecimal;

public class BankTransferPaymentServiceImpl implements PaymentService {
    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Pay by Bank transfer");
    }
}
