package se.lernia.di.payment;

import java.math.BigDecimal;

public interface PaymentProcessor {
    String pay(String customer, BigDecimal amount);
}
