package flight.reservation.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Observer interface
interface CreditCardObserver {
    void update(CreditCard creditCard);
}

// CreditCard class acting as the subject
public class CreditCard {
    private double amount;
    private String number;
    private Date date;
    private String cvv;
    private boolean valid;
    private List<CreditCardObserver> observers = new ArrayList<>();

    public CreditCard(String number, Date date, String cvv) {
        this.amount = 100000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
        this.setValid();
    }

    // Methods to manage observers
    public void addObserver(CreditCardObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CreditCardObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (CreditCardObserver observer : observers) {
            observer.update(this);
        }
    }

    // Setters and getters
    public void setAmount(double amount) {
        this.amount = amount;
        notifyObservers(); // Notify observers about the change
    }

    // Other methods
    public void setValid() {
        // Dummy validation
        boolean oldValid = this.valid;
        this.valid = number.length() > 0 && date.getTime() > System.currentTimeMillis() && !cvv.equals("000");
        if (oldValid != this.valid) {
            notifyObservers(); // Notify observers if validity changes
        }
    }

    // Add getters for other fields as needed
}

// Example observer
class PaymentGateway implements CreditCardObserver {
    @Override
    public void update(CreditCard creditCard) {
        if (!creditCard.isValid()) {
            System.out.println("Credit card is invalid. Please update payment information.");
        }
        // Handle other changes as necessary
    }
}
