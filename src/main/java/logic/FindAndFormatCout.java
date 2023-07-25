package logic;

import java.util.List;
import entities.Valute;

public class FindAndFormatCout {
    public void answerToUser(String code, List<Valute> val){
        for (Valute valute : val) {
            if (valute.getCharCode().equals(code)) {
                System.out.println(valute.getCharCode() + " (" + valute.getName() + "): " + valute.getValue());
                return;
            }
        }
        System.out.println("Курс не найден");
    }
}
