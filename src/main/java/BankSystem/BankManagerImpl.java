package BankSystem;

import Exceptions.InvalidAccountException;
import models.Account;
import models.Card;
import models.bank.Bank;


public class BankManagerImpl implements BankManager {

    @Override
    public boolean checkValidAccount(Card card, Bank bank) throws InvalidAccountException {
        return bank.checkValidAccount(card, bank);
    }

    @Override
    public boolean checkAccountBalance(Card card, Bank bank, long amount) throws Exception {
       return bank.checkAccountBalance(card, bank, amount);
    }

    @Override
    public void getMoney(Card card, Bank bank, long amount) throws Exception {
        bank.getMoney(card, bank, amount);
    }

    @Override
    public Account getAccount(Card card, Bank bank) {
       return bank.getAccount(card, bank);
    }

}
