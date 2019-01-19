package Card_Reflect;

import models.Card;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CardReflect {
    public void validCardNumber(Card card){
        Class cardClass = card.getClass();
        Field[] fields = cardClass.getFields();
        for (Field field: fields){
            field.setAccessible(true);
        }
        Annotation[] annotations = cardClass.getAnnotations();
        System.out.println("length " + annotations.length);
    }
}
