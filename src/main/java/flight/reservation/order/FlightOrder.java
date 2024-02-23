package flight.reservation.order;

import flight.reservation.flight.ScheduledFlight;

import java.util.ArrayList;
import java.util.List;

public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;

    private FlightOrder(Builder builder) {
        super(builder);
        this.flights = builder.flights;
    }

    // Existing methods...

    public static class Builder extends Order.Builder<Builder> {
        private List<ScheduledFlight> flights = new ArrayList<>();

        public Builder flights(List<ScheduledFlight> flights) {
            this.flights = flights;
            return this;
        }

        @Override
        public FlightOrder build() {
            return new FlightOrder(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
