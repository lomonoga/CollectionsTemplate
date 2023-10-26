package ru.naumen.collection.task2;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private static long countId = 0;
    private long id;
    private String client;

    public Ticket(String client) {
        this.client = client;
        this.id = Ticket.countId++;
    }

    public long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }
}
