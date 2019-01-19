import Persons.Person;
import helpers.Runner;

public class Main {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " thread");
        Runner.runApp();
        Person person = new Person(Runner.atm);
    }
}

