package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValCurs {
    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "Valute")
    private List<Valute> valutes;
}
