package entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Valute")
public class Valute {

    @XmlAttribute(name = "ID", required = true)
    private String id;

    @XmlElement(name = "NumCode", required = true)
    private int numCode;

    @XmlElement(name = "CharCode", required = true)
    private String charCode;

    @XmlElement(name = "Nominal", required = true)
    private int nominal;

    @XmlElement(name = "Name", required = true)
    private String name;

    @XmlElement(name = "Value", required = true)
    private String value;
}