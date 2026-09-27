package se.lernia.di.notification;

import jakarta.inject.Inject;

public class SmsNotifier implements Notifier {
    private final MessageFormatter formatter;

    @Inject
    public SmsNotifier(MessageFormatter formatter) {
        this.formatter = formatter;
        System.out.println("  [new] SmsNotifier");
    }

    @Override
    public String send(String recipient, String message) {
        String sms = "SMS to " + recipient + ": " + formatter.format(message);
        System.out.println(sms);
        return sms;
    }
}
