package se.lernia.di.payment;

import java.math.BigDecimal;

public class InvoicePaymentProcessor implements PaymentProcessor {
    private int invoiceCounter;

    public InvoicePaymentProcessor() {
        System.out.println("  [new] InvoicePaymentProcessor");
    }

    @Override
    public String pay(String customer, BigDecimal amount) {
        invoiceCounter++;
        return "INVOICE-" + invoiceCounter + ": " + amount + " SEK invoiced to " + customer;
    }
}
