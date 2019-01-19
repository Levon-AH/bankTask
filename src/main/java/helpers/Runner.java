package helpers;

import ATMSystem.ATM;
import Parser.ParseXML;
import models.Card;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Runner {
    public static ATM atm = new ATM();

    public static void runApp(){
        List<Card> list = new ParseXML().initBanks();
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            System.out.println("============ For exit enter '$' =============");
            long amount = new Random().nextInt(100000);
            int randomCardIndex = new Random().nextInt(list.size());
            Card randomCard = list.get(randomCardIndex);
            System.out.println(randomCard.getIssurBank().getBankInstance());
            atm.withDraw(amount, randomCard);
            input = scanner.next();
        } while (!input.equals("$"));
    }
}
