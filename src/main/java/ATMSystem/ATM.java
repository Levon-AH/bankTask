package ATMSystem;

import BankSystem.BankManager;
import BankSystem.BankManagerImpl;
import Exceptions.ATMBalanceException;
import Exceptions.GreatorAmountException;
import Exceptions.InvalidAccountException;
import models.Account;
import models.Card;

import java.util.Random;
import java.util.logging.Logger;

public class ATM {
    private Logger logger = Logger.getLogger("ATM Logger");
    private long balance;
    private boolean flag = true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private BankManager bankManager;
    private ATMManager atmManager;

    public ATM(){
        balance = new Random().nextInt(1000000);
        bankManager = new BankManagerImpl();
        atmManager = new ATMManagerImpl();
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean checkMoney(long amount){
        return amount <= balance;
    }

    public void reduceBalance(long amount) throws ATMBalanceException {
        if (!checkMoney(amount))
            throw new ATMBalanceException("no money for reduce");
        setBalance(balance - amount);
    }


    public void withDraw(long amount, Card card){
        synchronized (this) {
            Account account = bankManager.getAccount(card, card.getIssurBank().getBankInstance());
            logger.info("Your request are preparing on this card... " + card);
            if (flag) {
                try {
                    logger.info("Your request are success on your account " + account + " ====== " + "and reduce amount " + amount);
                    bankManager.getMoney(card, card.getIssurBank().getBankInstance(), amount);
                    logger.info("After your card " + account);
                    atmManager.reduceBalance(this, amount);
                    logger.info("YOUR REQUEST ARE SUCCESS ON THIS ATM... " + this);
                    logger.info("after reducing ATM " + this);
                    System.out.println(Thread.currentThread().getState());
                    wait();
                } catch (GreatorAmountException e) {
                    logger.warning(e + " Error on get money on this bank and amount ------> " + amount + "\t" + account);
                } catch (ATMBalanceException e) {
                    logger.warning(e + " Error on get money on this ATM and amount ------> " + amount + "\t" + this);
                } catch (InvalidAccountException e) {
                    logger.warning(e + " Error invalid account");
                } catch (Exception e) {
                    System.out.println(e + " Exception");
                }
            }
            flag = true;
            notifyAll();
        }
    }

    @Override
    public String toString() {
        return "ATM => {" +
                "balance = " + balance +
                '}';
    }
}
