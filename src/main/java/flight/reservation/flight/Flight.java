package flight.reservation.flight;

import flight.reservation.Airport;
import flight.reservation.plane.Helicopter;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.PassengerPlane;

import java.util.Arrays;

public class Flight {

    private int number;
    private Airport departure;
    private Airport arrival;
    protected Object aircraft;

    private Flight(Builder builder) {
        this.number = builder.number;
        this.departure = builder.departure;
        this.arrival = builder.arrival;
        this.aircraft = builder.aircraft;
        checkValidity();
    }

    private void checkValidity() throws IllegalArgumentException {
        if (!isAircraftValid(departure) || !isAircraftValid(arrival)) {
            throw new IllegalArgumentException("Selected aircraft is not valid for the selected route.");
        }
    }

    private boolean isAircraftValid(Airport airport) {
        return Arrays.stream(airport.getAllowedAircrafts()).anyMatch(x -> {
            String model;
            if (this.aircraft instanceof PassengerPlane) {
                model = ((PassengerPlane) this.aircraft).model;
            } else if (this.aircraft instanceof Helicopter) {
                model = ((Helicopter) this.aircraft).getModel();
            } else if (this.aircraft instanceof PassengerDrone) {
                model = "HypaHype";
            } else {
                throw new IllegalArgumentException(String.format("Aircraft is not recognized"));
            }
            return x.equals(model);
        });
    }

    public Object getAircraft() {
        return aircraft;
    }

    public int getNumber() {
        return number;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return aircraft.toString() + "-" + number + "-" + departure.getCode() + "/" + arrival.getCode();
    }

    public static class Builder {
        private int number;
        private Airport departure;
        private Airport arrival;
        protected Object aircraft;

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder departure(Airport departure) {
            this.departure = departure;
            return this;
        }

        public Builder arrival(Airport arrival) {
            this.arrival = arrival;
            return this;
        }

        public Builder aircraft(Object aircraft) {
            this.aircraft = aircraft;
            return this;
        }

        public Flight build() throws IllegalArgumentException {
            return new Flight(this);
        }
    }
}
