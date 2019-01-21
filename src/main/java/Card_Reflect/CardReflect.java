package Card_Reflect;

import Card_Annotations.*;
import com.sun.istack.internal.NotNull;
import enums.CardBrand;
import enums.Currency;
import enums.IssurBank;
import models.Card;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardReflect {

    private Card card;
    private Class<Card> cardClass;

    public CardReflect(@NotNull Card card) {
        this.card = card;
        cardClass = (Class<Card>) card.getClass();
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }



    public boolean validCardNumber() throws NoSuchFieldException, IllegalAccessException {
        boolean result = false;
        String number;
        Field cardNumber = cardClass.getDeclaredField("number");
        cardNumber.setAccessible(true);
        ValidCardNumber declaredAnnotation = cardNumber.getDeclaredAnnotation(ValidCardNumber.class);

        Pattern pattern = Pattern.compile("\\d");
        number = (String) cardNumber.get(card);
        Matcher matcher = pattern.matcher(number);
        int numberCount = 0;

        while (matcher.find())
            numberCount++;

        if (declaredAnnotation != null) {

            if (number.length() < 8) {
                result = false;
            } else if (numberCount != number.length())
                result = false;
            else
                result = true;
        }
        return result;
    }

    public boolean validCardBrand() throws NoSuchFieldException, IllegalAccessException {
        boolean result = false;
        Field cardBrand = cardClass.getDeclaredField("brand");
        cardBrand.setAccessible(true);
        ValidCardBrand annotation = cardBrand.getDeclaredAnnotation(ValidCardBrand.class);
        if (annotation != null){
            CardBrand brand = (CardBrand) cardBrand.get(card);
            result = (brand instanceof CardBrand);
        }
        return result;
    }

    public boolean validExpireDate() throws NoSuchFieldException, IllegalAccessException {
        boolean result = true;
        Field cardDate = cardClass.getDeclaredField("expireDate");
        cardDate.setAccessible(true);
        ValidExpireDate annotation = cardDate.getDeclaredAnnotation(ValidExpireDate.class);
        if (annotation != null){
            Date date = (Date) cardDate.get(card);
            result = (new Date().before(date));
        }
        return result;
    }

    public boolean validCurrency() throws NoSuchFieldException, IllegalAccessException {
        boolean result = false;
        Field cardCurrency = cardClass.getDeclaredField("currency");
        cardCurrency.setAccessible(true);
        ValidCurrency annotation = cardCurrency.getDeclaredAnnotation(ValidCurrency.class);
        if (annotation != null){
            Currency currency = (Currency) cardCurrency.get(card);
            result = (currency instanceof Currency);
        }

        return result;
    }

    public boolean validIssureBank() throws NoSuchFieldException, IllegalAccessException {
        boolean result = false;
        Field issureBank = cardClass.getDeclaredField("issurBank");
        issureBank.setAccessible(true);
        ValidIssureBank annotation = issureBank.getDeclaredAnnotation(ValidIssureBank.class);
        if (annotation != null){
            IssurBank issure = (IssurBank) issureBank.get(card);
            result = (issure instanceof IssurBank);
        }
        return result;
    }

    public boolean validFullName() throws NoSuchFieldException, IllegalAccessException {
        boolean result = false;
        Field fullName = cardClass.getDeclaredField("fullName");
        fullName.setAccessible(true);
        ValidFullName annotation = fullName.getDeclaredAnnotation(ValidFullName.class);
        if (annotation != null){
            String fName = (String) fullName.get(card);
            result = (fName.split(" ").length == 2);
        }
        return result;
    }

    public boolean validCard()  {
        try {
            return validCardNumber() && validCardBrand() &&
                    validCurrency() && validIssureBank() &&
                    validExpireDate() && validFullName();
        } catch (NoSuchFieldException e) {
            System.err.println(e.getMessage());
        } catch (IllegalAccessException e) {
            e.getMessage();
        }
        return false;
    }
}
