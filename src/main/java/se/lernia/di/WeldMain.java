package se.lernia.di;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.math.BigDecimal;

public class WeldMain {

    public static void main(String[] args) {
        System.out.println("=== Part 3: Weld (CDI) ===");

        try (WeldContainer container = new Weld().initialize()) {
            CheckoutService first = container.select(CheckoutService.class).get();
            CheckoutService second = container.select(CheckoutService.class).get();

            first.checkout("Anna", new BigDecimal("199.00"));
            second.checkout("Björn", new BigDecimal("49.50"));
            System.out.println("Same CheckoutService instance? " + (first == second));
        }
    }
}
