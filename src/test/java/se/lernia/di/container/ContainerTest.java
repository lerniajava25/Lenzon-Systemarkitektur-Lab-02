package se.lernia.di.container;

import org.junit.jupiter.api.Test;
import se.lernia.di.CheckoutService;
import se.lernia.di.notification.EmailNotifier;
import se.lernia.di.notification.MessageFormatter;
import se.lernia.di.notification.Notifier;
import se.lernia.di.notification.PlainTextFormatter;
import se.lernia.di.payment.CardPaymentProcessor;
import se.lernia.di.payment.PaymentProcessor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {

    @Test
    void buildsTheWholeGraphFromTheTopLevelClass() {
        Container container = new Container()
                .bind(PaymentProcessor.class, CardPaymentProcessor.class)
                .bind(Notifier.class, EmailNotifier.class)
                .bind(MessageFormatter.class, PlainTextFormatter.class);

        CheckoutService checkout = container.get(CheckoutService.class);

        assertEquals("CARD-1: 199.00 SEK charged to Anna", checkout.checkout("Anna", new BigDecimal("199.00")));
    }
}
