package models.bank;

import java.util.HashMap;

public class ACBABank extends Bank {
    private static ACBABank instance;

    private ACBABank(){store = new HashMap<>();
    }

    public static ACBABank getInstance(){
        if (instance == null){
            instance = new ACBABank();
        }

        return instance;
    }
}
