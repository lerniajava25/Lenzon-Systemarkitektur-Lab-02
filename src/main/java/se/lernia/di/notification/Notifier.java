package se.lernia.di.notification;

public interface Notifier {
    String send(String recipient, String message);
}
