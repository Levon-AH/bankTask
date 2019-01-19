package ATMSystem;

import Exceptions.ATMBalanceException;

public class ATMManagerImpl implements ATMManager {
    @Override
    public boolean checkMoney(ATM atm, long amount){
        return atm.checkMoney(amount);
    }

    @Override
    public void reduceBalance(ATM atm, long amount) throws ATMBalanceException {
        atm.reduceBalance(amount);
    }
}
