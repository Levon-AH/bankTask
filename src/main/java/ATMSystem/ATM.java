package ATMSystem;

import BankSystem.BankManager;
import BankSystem.BankManagerImpl;
import Exceptions.ATMBalanceException;
import Exceptions.GreatorAmountException;
import Exceptions.InvalidAccountException;
import Exceptions.InvalidCardException;
import models.Account;
import models.Card;

import java.util.Random;
import java.util.logging.Logger;

public class ATM {
    private Logger logger = Logger.getLogger("ATM Logger");

    private long balance;
    private static ATM instance = null;
    private BankManager bankManager;
    private ATMManager atmManager;

    private ATM(){
        balance = new Random().nextInt(1000000);
        bankManager = new BankManagerImpl();
        atmManager = new ATMManagerImpl();
    }

    public synchronized static ATM getInstance(){
        if (instance == null){
            instance = new ATM();
        }
        return instance;
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

        Account account = bankManager.getAccount(card, card.getIssurBank().getBankInstance());
        logger.info(Thread.currentThread().getName());
            logger.info("Your request are preparing on this card... " + card);
                try {
                    logger.info("Your request are success on your account " + account + " ====== " + "and reduce amount " + amount);
                    bankManager.getMoney(card, card.getIssurBank().getBankInstance(), amount);

                    logger.info("After your card " + account);
                    logger.info("YOUR REQUEST ARE PREPARING ON THIS ATM... " + this);

                    atmManager.reduceBalance(this, amount);

                    logger.info("after reducing ATM " + this);

                } catch (GreatorAmountException e) {
                    logger.warning(e + " Error on get money on this bank and amount ------> " + amount + "\t" + account);
                } catch (ATMBalanceException e) {
                    logger.warning(e + " Error on get money on this ATM and amount ------> " + amount + "\t" + this);
                    System.exit(0);
                } catch (InvalidAccountException e) {
                    logger.warning(e + " Error invalid account");
                } catch (InvalidCardException e) {
                    logger.warning(e.getMessage());
                }catch (Exception e){
                    logger.warning(e.getMessage());
                }
    }

    @Override
    public String toString() {
        return "ATM => {" +
                "balance = " + balance +
                '}';
    }
}
