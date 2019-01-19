package models.bank;

import Exceptions.GreatorAmountException;
import Exceptions.InvalidAccountException;
import models.Account;
import models.Card;

import java.util.Map;
import java.util.Set;

public abstract class Bank {
    protected Map<String, Set<Account>> store;

    public Bank(Map<String, Set<Account>> store) {
        this.store = store;
    }

    public Bank(){

    }

    public Map<String, Set<Account>> getStore() {
        return store;
    }

    public void setStore(Map<String, Set<Account>> store) {
        this.store = store;
    }

    public boolean checkValidAccount(Card card, Bank bank) throws InvalidAccountException {
        boolean result = false;
        if(!bank.getStore().containsKey(card.getFullName())){
            throw new InvalidAccountException("invalid account");
        } else {
            for (Account current: bank.getStore().get(card.getFullName())){
                if (card.getNumber().equals(current.getCardNumber()))
                    result = true;
            }
        }
        return result;
    }

    public boolean checkAccountBalance(Card card, Bank bank, long amount) throws Exception{
        if (!checkValidAccount(card, bank)){
            throw new InvalidAccountException("invalid account");
        }
        Account account = getAccount(card, bank);

        if (account != null){
            return amount <= account.getAmount();
        }
        else throw new GreatorAmountException("you  can't get money for this amount => " + amount);
    }

    public void getMoney(Card card, Bank bank, long amount) throws Exception {
        if (!checkAccountBalance(card, bank, amount)){
            throw new GreatorAmountException("you  can't get money for this amount => " + amount);
        }
        Map<String, Set<Account>> store = bank.getStore();
        Account account = getAccount(card, bank);
        account.setAmount(account.getAmount() - amount);
    }

    public Account getAccount(Card card, Bank bank){
        Map<String, Set<Account>> store = bank.getStore();
        Account account = null;
        for (Account current: store.get(card.getFullName())){
            if (current.getCardNumber().equals(card.getNumber())){
                account = current;
            }
        }
        return account;
    }
    @Override
    public String toString() {
        return "Bank => {" +
                "store = " + store +
                '}';
    }
}
