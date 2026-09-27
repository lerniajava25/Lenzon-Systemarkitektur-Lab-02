package se.lernia.di.notification;

import jakarta.enterprise.context.Dependent;

@Dependent
public class PlainTextFormatter implements MessageFormatter {

    public PlainTextFormatter() {
        System.out.println("  [new] PlainTextFormatter");
    }

    @Override
    public String format(String message) {
        return "[Webshop] " + message;
    }
}
