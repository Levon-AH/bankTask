package models.bank;

import java.util.HashMap;

public class HSBCBank extends Bank {
    private static HSBCBank instance;

    private HSBCBank(){store = new HashMap<>();
    }

    public static HSBCBank getInstance(){
        if (instance == null){
            instance = new HSBCBank();
        }
        return instance;
    }
}
