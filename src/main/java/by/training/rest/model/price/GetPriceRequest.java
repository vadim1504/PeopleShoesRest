
package by.training.rest.model.price;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idShoes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idShoes"
})
@XmlRootElement(name = "getPriceRequest", namespace = "http://peopleShoes.com/soap/price")
public class GetPriceRequest {

    @XmlElement(namespace = "http://peopleShoes.com/soap/price")
    protected int idShoes;

    /**
     * Gets the value of the idShoes property.
     * 
     */
    public int getIdShoes() {
        return idShoes;
    }

    /**
     * Sets the value of the idShoes property.
     * 
     */
    public void setIdShoes(int value) {
        this.idShoes = value;
    }

}
