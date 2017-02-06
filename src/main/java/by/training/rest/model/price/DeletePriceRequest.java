
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
 *         &lt;element name="idPrice" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "idPrice"
})
@XmlRootElement(name = "deletePriceRequest", namespace = "http://peopleShoes.com/soap/price")
public class DeletePriceRequest {

    @XmlElement(namespace = "http://peopleShoes.com/soap/price")
    protected int idPrice;

    /**
     * Gets the value of the idPrice property.
     * 
     */
    public int getIdPrice() {
        return idPrice;
    }

    /**
     * Sets the value of the idPrice property.
     * 
     */
    public void setIdPrice(int value) {
        this.idPrice = value;
    }

}
