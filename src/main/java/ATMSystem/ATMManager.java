package ATMSystem;

public interface ATMManager {
    boolean checkMoney(ATM atm, long amount);
    void reduceBalance(ATM atm, long amount) throws Exception;
}
