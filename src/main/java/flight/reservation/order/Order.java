package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private double price;
    private boolean isClosed = false;
    private Customer customer;
    private List<Passenger> passengers;

    protected Order(Builder<?> builder) {
        this.id = UUID.randomUUID(); // This remains auto-generated
        this.price = builder.price;
        this.customer = builder.customer;
        this.passengers = builder.passengers;
        this.isClosed = builder.isClosed;
    }

    // Existing getters and setters...

    public static class Builder<T extends Builder<T>> {
        private double price;
        private boolean isClosed = false;
        private Customer customer;
        private List<Passenger> passengers = new ArrayList<>();

        public T price(double price) {
            this.price = price;
            return self();
        }

        public T customer(Customer customer) {
            this.customer = customer;
            return self();
        }

        public T passengers(List<Passenger> passengers) {
            this.passengers = passengers;
            return self();
        }

        public T isClosed(boolean isClosed) {
            this.isClosed = isClosed;
            return self();
        }

        protected T self() {
            return (T) this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
