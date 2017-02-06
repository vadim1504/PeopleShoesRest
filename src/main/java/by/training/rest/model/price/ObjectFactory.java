
package by.training.rest.model.price;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.price package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.price
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdatePriceRequest }
     * 
     */
    public UpdatePriceRequest createUpdatePriceRequest() {
        return new UpdatePriceRequest();
    }

    /**
     * Create an instance of {@link GetListPriceResponse }
     * 
     */
    public GetListPriceResponse createGetListPriceResponse() {
        return new GetListPriceResponse();
    }

    /**
     * Create an instance of {@link Price }
     * 
     */
    public Price createPrice() {
        return new Price();
    }

    /**
     * Create an instance of {@link GetPriceResponse }
     * 
     */
    public GetPriceResponse createGetPriceResponse() {
        return new GetPriceResponse();
    }

    /**
     * Create an instance of {@link GetListPriceRequest }
     * 
     */
    public GetListPriceRequest createGetListPriceRequest() {
        return new GetListPriceRequest();
    }

    /**
     * Create an instance of {@link GetPriceRequest }
     * 
     */
    public GetPriceRequest createGetPriceRequest() {
        return new GetPriceRequest();
    }

    /**
     * Create an instance of {@link DeletePriceRequest }
     * 
     */
    public DeletePriceRequest createDeletePriceRequest() {
        return new DeletePriceRequest();
    }

    /**
     * Create an instance of {@link CreatePriceRequest }
     * 
     */
    public CreatePriceRequest createCreatePriceRequest() {
        return new CreatePriceRequest();
    }

    /**
     * Create an instance of {@link GetListPriceByMinMaxRequest }
     * 
     */
    public GetListPriceByMinMaxRequest createGetListPriceByMinMaxRequest() {
        return new GetListPriceByMinMaxRequest();
    }

}
