package models;

public class Account {
    private String cardNumber;
    private long amount;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (amount != account.amount) return false;
        return cardNumber.equals(account.cardNumber);
    }

    @Override
    public int hashCode() {
        int result = cardNumber.hashCode();
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account => {" +
                "cardNumber = '" + cardNumber + '\'' +
                ", amount = " + amount +
                '}';
    }
}
