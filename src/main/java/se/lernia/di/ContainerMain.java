package se.lernia.di;

import se.lernia.di.container.Container;
import se.lernia.di.notification.EmailNotifier;
import se.lernia.di.notification.MessageFormatter;
import se.lernia.di.notification.Notifier;
import se.lernia.di.notification.PlainTextFormatter;
import se.lernia.di.payment.CardPaymentProcessor;
import se.lernia.di.payment.PaymentProcessor;

import java.math.BigDecimal;

public class ContainerMain {

    public static void main(String[] args) {
        System.out.println("=== Part 2: my own DI container ===");

        Container container = new Container()
                .bind(PaymentProcessor.class, CardPaymentProcessor.class)
                .bind(Notifier.class, EmailNotifier.class)
                .bind(MessageFormatter.class, PlainTextFormatter.class);

        CheckoutService first = container.get(CheckoutService.class);
        CheckoutService second = container.get(CheckoutService.class);

        first.checkout("Anna", new BigDecimal("199.00"));
        second.checkout("Björn", new BigDecimal("49.50"));
        System.out.println("Same CheckoutService instance? " + (first == second));
    }
}
