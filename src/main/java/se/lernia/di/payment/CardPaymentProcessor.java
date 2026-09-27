package se.lernia.di.payment;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
public class CardPaymentProcessor implements PaymentProcessor {
    private int receiptCounter;

    public CardPaymentProcessor() {
        System.out.println("  [new] CardPaymentProcessor");
    }

    @Override
    public String pay(String customer, BigDecimal amount) {
        receiptCounter++;
        return "CARD-" + receiptCounter + ": " + amount + " SEK charged to " + customer;
    }
}
