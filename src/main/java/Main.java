import Persons.Person;
import helpers.Runner;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter persons count ---> ");
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            new Person(Runner.getAtm(), "Person " + (i + 1));
        }
    }
}

