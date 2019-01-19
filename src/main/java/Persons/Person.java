package Persons;

import ATMSystem.ATM;
import helpers.Runner;


public class Person implements Runnable {
    private ATM atm;

    public Person(ATM atm){
        this.atm = atm;
        new Thread(this).start();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " thread");
        synchronized (atm) {
            if (atm.isFlag()){
                Runner.runApp();
                System.out.println(Thread.currentThread().getState());
                    atm.setFlag(false);
                    notifyAll();

            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
