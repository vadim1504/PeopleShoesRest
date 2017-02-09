package by.training.rest.soap;

import by.training.rest.model.price.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.ArrayList;

public class PriceClient extends WebServiceGatewaySupport {

    public void createPrice(Price price) {
        CreatePriceRequest request = new CreatePriceRequest();
        request.setIdShoes(price.getIdShoes());
        request.setPriceEu(price.getPriceEu());
        request.setPriceRu(price.getPriceRu());
        getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8080/spring4soap-1/soap/createPriceResponse"));
    }

    public Price getPrice(int idShoes) {
        GetPriceRequest request = new GetPriceRequest();
        request.setIdShoes(idShoes);
        GetPriceResponse response = (GetPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8080/spring4soap-1/soap/getPriceResponse"));
        Price price = response.getPrice();
        return price;
    }

    public ArrayList<Price> getListPrice(){
        GetListPriceRequest request = new GetListPriceRequest();
        request.setMessage("ok");
        GetListPriceResponse response = (GetListPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request,new SoapActionCallback("http://localhost:8080/spring4soap-1/soap/getListPriceResponse"));
        ArrayList<Price> prices =  (ArrayList<Price>) response.getPrice();
        return prices;
    }

    public ArrayList<Price> getListPriceByPrice(int min,int max){
        GetListPriceByMinMaxRequest request = new GetListPriceByMinMaxRequest();
        request.setMin(min);
        request.setMax(max);
        GetListPriceResponse response = (GetListPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request,new SoapActionCallback("http://localhost:8080/spring4soap-1/soap/getListPriceByMinMaxRequest"));
        ArrayList<Price> prices =  (ArrayList<Price>) response.getPrice();
        return prices;
    }

    public void deletePrice(int id){
        DeletePriceRequest request = new DeletePriceRequest();
        request.setIdPrice(id);
        getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8080/spring4soap-1/soap/deletePriceResponse"));
    }

    public void updatePrice(Price price){
        UpdatePriceRequest request = new UpdatePriceRequest();
        request.setId(price.getId());
        request.setIdShoes(price.getIdShoes());
        request.setPriceEu(price.getPriceEu());
        request.setPriceRu(price.getPriceRu());
        getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8080/spring4soap-1/soap/updatePriceResponse"));
    }
}
