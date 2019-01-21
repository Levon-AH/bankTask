package helpers;

import ATMSystem.ATM;
import Exceptions.InvalidCardException;
import Parser.ParseXML;
import models.Card;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Runner {
    private Logger logger = Logger.getLogger("Runner");
    private static ATM atm;
    private volatile boolean finished = false;
    private static List<Card> list = new ParseXML().initBanks();

    static {
        atm = ATM.getInstance();
    }

    public static ATM getAtm() {
        return atm;
    }

    public  void runApp() {
        int randomCardIndex = new Random().nextInt(list.size());
        Card randomCard = list.get(randomCardIndex);
        int randomAmount = new Random().nextInt(100000);
        System.err.println();
        logger.info(randomCard.getIssurBank().getBankInstance().toString());
        atm.withDraw(randomAmount, randomCard);
        System.err.println();
    }

    public void giveMoney() throws InterruptedException {
        synchronized (atm){
            if (finished){
               atm.wait();
            }

            finished = true;
            runApp();
            atm.notifyAll();
        }
    }

    public void nextGive() throws InterruptedException {
        synchronized (atm){
            if (!finished){
                atm.wait();
            }

            finished = false;
            atm.notifyAll();
        }
    }
}
