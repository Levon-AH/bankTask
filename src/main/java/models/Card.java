package models;

import Card_Annotations.*;
import Card_Reflect.CardReflect;
import Exceptions.InvalidCardException;
import enums.CardBrand;
import enums.Currency;
import enums.IssurBank;

import java.util.Date;

public final class Card {
    @ValidCardNumber
    private final String number;

    @ValidCardBrand
    private final CardBrand brand;

    @ValidCurrency
    private final Currency currency;

    @ValidFullName
    private final String fullName;

    @ValidExpireDate
    private final Date expireDate;

    @ValidIssureBank
    private final IssurBank issurBank;


    public Card(Builder builder){
        number = builder.number;
        brand = builder.brand;
        currency = builder.currency;
        fullName = builder.fullName;
        expireDate = builder.expireDate;
        issurBank = builder.issurBank;
    }

    public String getNumber() {
        return number;
    }

    public CardBrand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getExpireDate() {
        Date clone = (Date)expireDate.clone();
        return clone;
    }

    public IssurBank getIssurBank() {
        return issurBank;
    }

    public void validate() throws InvalidCardException {
        CardReflect cardReflect = new CardReflect(this);
            if (!cardReflect.validCard()) {
                throw new InvalidCardException("[ERROR]:invalid card " + this);
            }
    }
    @Override
    public String toString() {
        return "Card => {" +
                "number = '" + number + '\'' +
                ", brand = " + brand +
                ", currency = " + currency +
                ", fullName = '" + fullName + '\'' +
                ", expireDate = " + expireDate +
                ", issurBank = " + issurBank +
                '}';
    }


    public static class Builder{
        private String number;
        private CardBrand brand;
        private Currency currency;
        private String fullName;
        private Date expireDate;
        private IssurBank issurBank;

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setBrand(CardBrand brand) {
            this.brand = brand;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }


        public Builder setExpireDate(Date expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public Builder setIssurBank(IssurBank issurBank) {
            this.issurBank = issurBank;
            return this;
        }

        public Card build(){
            return new Card(this);
        }
    }

}
