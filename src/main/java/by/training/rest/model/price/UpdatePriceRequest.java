
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idShoes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="priceEu" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="priceRu" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "id",
    "idShoes",
    "priceEu",
    "priceRu"
})
@XmlRootElement(name = "updatePriceRequest", namespace = "http://peopleShoes.com/soap/price")
public class UpdatePriceRequest {

    @XmlElement(namespace = "http://peopleShoes.com/soap/price")
    protected int id;
    @XmlElement(namespace = "http://peopleShoes.com/soap/price")
    protected int idShoes;
    @XmlElement(namespace = "http://peopleShoes.com/soap/price")
    protected double priceEu;
    @XmlElement(namespace = "http://peopleShoes.com/soap/price")
    protected double priceRu;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

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

    /**
     * Gets the value of the priceEu property.
     * 
     */
    public double getPriceEu() {
        return priceEu;
    }

    /**
     * Sets the value of the priceEu property.
     * 
     */
    public void setPriceEu(double value) {
        this.priceEu = value;
    }

    /**
     * Gets the value of the priceRu property.
     * 
     */
    public double getPriceRu() {
        return priceRu;
    }

    /**
     * Sets the value of the priceRu property.
     * 
     */
    public void setPriceRu(double value) {
        this.priceRu = value;
    }

}
