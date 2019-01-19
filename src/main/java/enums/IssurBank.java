package enums;

import models.bank.ACBABank;
import models.bank.AmeriaBank;
import models.bank.Bank;
import models.bank.HSBCBank;

public enum IssurBank {
    AMERIA{
        @Override
        public Bank getBankInstance() {
            return AmeriaBank.getInstance();
        }
    },
    HSBC{
        @Override
        public Bank getBankInstance() {
            return HSBCBank.getInstance();
        }
    },
    ACBA{
        @Override
        public Bank getBankInstance() {
            return ACBABank.getInstance();
        }
    };

    public abstract Bank getBankInstance();
}
