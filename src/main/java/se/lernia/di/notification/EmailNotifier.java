package se.lernia.di.notification;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class EmailNotifier implements Notifier {
    private final MessageFormatter formatter;

    @Inject
    public EmailNotifier(MessageFormatter formatter) {
        this.formatter = formatter;
        System.out.println("  [new] EmailNotifier");
    }

    @Override
    public String send(String recipient, String message) {
        String email = "Email to " + recipient + ": " + formatter.format(message);
        System.out.println(email);
        return email;
    }
}
