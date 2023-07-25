package logic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import entities.ValCurs;
import entities.Valute;
import java.io.File;
import java.util.List;

public class XMLParser {

    public List<Valute> xmlToJavaObject(String xmlFilePath){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File(xmlFilePath);
            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(xmlFile);           
            List<Valute> valutes = valCurs.getValutes();
            return valutes;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
