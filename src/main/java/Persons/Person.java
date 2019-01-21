package Persons;

import ATMSystem.ATM;
import helpers.Runner;


public class Person implements Runnable {
    private ATM atm;
    private String name;
    private volatile boolean flag = false;

    public Person(ATM atm, String name){
        this.name = name;
        this.atm = atm;
        new Thread(this, name).start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void run() {
                Runner runner = new Runner();
                try {
                    runner.giveMoney();
                    runner.nextGive();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
    }
}
