package se.lernia.di;

import se.lernia.di.notification.EmailNotifier;
import se.lernia.di.notification.MessageFormatter;
import se.lernia.di.notification.Notifier;
import se.lernia.di.notification.PlainTextFormatter;
import se.lernia.di.payment.CardPaymentProcessor;
import se.lernia.di.payment.PaymentProcessor;

import java.math.BigDecimal;

public class ManualWiringMain {

    public static void main(String[] args) {
        System.out.println("=== Part 1: manual constructor injection ===");

        MessageFormatter formatter = new PlainTextFormatter();
        Notifier notifier = new EmailNotifier(formatter);
        PaymentProcessor paymentProcessor = new CardPaymentProcessor();
        CheckoutService checkout = new CheckoutService(paymentProcessor, notifier);

        checkout.checkout("Anna", new BigDecimal("199.00"));
        checkout.checkout("Björn", new BigDecimal("49.50"));
    }
}
