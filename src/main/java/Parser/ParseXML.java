package Parser;

import enums.CardBrand;
import enums.IssurBank;
import enums.Currency;
import models.Account;
import models.Card;
import models.bank.Bank;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParseXML {
   public  List<Card> getCardList() throws ParserConfigurationException, IOException, SAXException, ParseException, URISyntaxException {
       List<Card> result = new ArrayList<>();

       File file = new File(this.getClass().getResource("/xml/cards.xml").toURI());  //TODO new File("cards.xml");
       DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
       Document document = builder.parse(file);
       document.normalize();
       NodeList nodeList = document.getElementsByTagName("card");
       for (int i = 0; i < nodeList.getLength(); i++){

           String cardNumber = document.getElementsByTagName("cardnumber").item(i).getTextContent();
           String cardBrand = document.getElementsByTagName("cardbrand").item(i).getTextContent();
           String currency = document.getElementsByTagName("currency").item(i).getTextContent();
           String issure = document.getElementsByTagName("issurebank").item(i).getTextContent();
           String cardHolder = document.getElementsByTagName("cardholder").item(i).getTextContent();
           String date = document.getElementsByTagName("expiredate").item(i).getTextContent();

           IssurBank issurBank = IssurBank.valueOf(issure);

           String name = cardHolder.split(" ")[0];
           String surname = cardHolder.split(" ")[1];
           String fullName = name.charAt(0) + name.substring(1).toLowerCase() + " " + surname.charAt(0) + surname.substring(1).toLowerCase();

           SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
           Date expireDate = dateFormat.parse(date);

           Card card = new Card.Builder()
                   .setNumber(cardNumber)
                   .setBrand(CardBrand.valueOf(cardBrand))
                   .setCurrency(Currency.valueOf(currency))
                   .setFullName(fullName)
                   .setExpireDate(expireDate)
                   .setIssurBank(issurBank)
                   .build();

           result.add(card);

       }

       return result;
   }

   public List<Card> initBanks(){
       List<Card> list = null;
       try {
           list = getCardList();
       } catch (ParserConfigurationException e) {
           System.err.println(e);
       } catch (IOException e) {
           System.err.println(e);
       } catch (SAXException e) {
           System.err.println(e);
       } catch (ParseException e) {
           System.err.println(e);
       } catch (URISyntaxException e) {
           System.err.println(e);
       }
       for (Card card: list){
           Account account = new Account();
           account.setCardNumber(card.getNumber());
           account.setAmount(new Random().nextInt(100000));

           Set<Account> accounts = new HashSet<>();
           accounts.add(account);
           Bank bank = card.getIssurBank().getBankInstance();//put(card.getFullName(), accounts);
           Map<String, Set<Account>> store = bank.getStore();

           if (store.containsKey(card.getFullName())){
              Set<Account> value = store.get(card.getFullName());
              value.add(account);
              store.put(card.getFullName(), value);
           }else
              store.put(card.getFullName(), accounts);
       }

       return list;
   }
}