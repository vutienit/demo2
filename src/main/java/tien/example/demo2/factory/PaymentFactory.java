package tien.example.demo2.factory;

import tien.example.demo2.service.PaymentService;
import tien.example.demo2.service.impl.BankTransferPaymentServiceImpl;
import tien.example.demo2.service.impl.MomoPaymentServiceImpl;

public class PaymentFactory {
    public PaymentService paymentService(String method) {
        var token = method.toUpperCase();
        switch (token) {
            case "MONO": return new MomoPaymentServiceImpl();
            case "ATM": return new BankTransferPaymentServiceImpl();
            default: return new BankTransferPaymentServiceImpl();
        }
    }
}
