package BankSystem;

import models.Account;
import models.Card;
import models.bank.Bank;

public interface BankManager {
    boolean checkValidAccount(Card card, Bank bank) throws Exception;
    boolean checkAccountBalance(Card card, Bank bank, long amount) throws Exception;
    void getMoney(Card card, Bank bank, long amount) throws Exception;
    Account getAccount(Card card, Bank bank);
}
