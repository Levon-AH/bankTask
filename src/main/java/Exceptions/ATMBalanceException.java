package Exceptions;

public class ATMBalanceException extends Exception {
    public ATMBalanceException() {
    }

    public ATMBalanceException(String message) {
        super(message);
    }
}
