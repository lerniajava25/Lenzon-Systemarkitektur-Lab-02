package se.lernia.di;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import se.lernia.di.notification.Notifier;
import se.lernia.di.payment.PaymentProcessor;

import java.math.BigDecimal;

@Dependent
public class CheckoutService {
    private final PaymentProcessor paymentProcessor;
    private final Notifier notifier;

    @Inject
    public CheckoutService(PaymentProcessor paymentProcessor, Notifier notifier) {
        this.paymentProcessor = paymentProcessor;
        this.notifier = notifier;
        System.out.println("  [new] CheckoutService");
    }

    public String checkout(String customer, BigDecimal amount) {
        String receipt = paymentProcessor.pay(customer, amount);
        notifier.send(customer, "Thanks for your order! " + receipt);
        return receipt;
    }
}
